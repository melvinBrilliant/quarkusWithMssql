package org.melvin.first.service.implementation;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.melvin.first.dto.RestResponse;
import org.melvin.first.dto.region.RegionDto;
import org.melvin.first.dto.region.UpsertRegionDto;
import org.melvin.first.entity.Region;
import org.melvin.first.entity.Territory;
import org.melvin.first.service.abstraction.RegionService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RegionServiceImpl implements RegionService {

    public List<RegionDto> findAllRaw() {
        return RegionDto.toRegionDtoList(Region.findAll().list());
    }

    @Override
    public RestResponse<List<RegionDto>> findAll(Integer page, Integer pageSize) {
        var findAllRegions = Region.findAll(Sort.by("id"));
        List<Region> regions = findAllRegions.page(Page.of(page - 1, pageSize)).list();
        List<RegionDto> data = RegionDto.toRegionDtoList(regions);
        Integer totalPages = findAllRegions.pageCount();
        Long totalItems = findAllRegions.count();

        return new RestResponse<>(
                data,
                page,
                pageSize,
                totalPages,
                totalItems,
                200,
                "SUCCESS"
        );
    }

    @Override
    public RestResponse<RegionDto> findById(Integer id) {
        RegionDto data = RegionDto.toRegionDto(Region.findById(id));
        return new RestResponse<>(
                data,
                null,
                null,
                null,
                null,
                200,
                "SUCCESS"
        );
    }

    @Override
    @Transactional
    public RestResponse<Object> saveOne(@Valid UpsertRegionDto dto) {
        Region region = new Region();
        if (dto.getId() == 0) {
            region.setRegionDescription(dto.getRegionDescription());
        } else {
            Optional<Region> optRegion = Region.findByIdOptional(dto.getId());
            region = optRegion.orElseThrow(() -> new NotFoundException("region not found"));
            region.setRegionDescription(dto.getRegionDescription());
        }
        region.persist();
        return new RestResponse<>(
                null,
                null,
                null,
                null,
                null,
                202, "SUCCESS"
        );
    }

    @Override
    @Transactional
    public RestResponse<Object> deleteOne(Integer id) {
        Region.findByIdOptional(id).orElseThrow(
                () -> new NotFoundException("region not found")
        );
        Long totalDependentTerriotry = Territory.countTerritoryByRegion(id);
        if (totalDependentTerriotry != 0) {
            throw new IllegalArgumentException(
                    "can not delete region with dependent territory");
        }
        Region.deleteById(id);
        return new RestResponse<>(
                null,
                null,
                null,
                null,
                null,
                202, "SUCCESS"
        );
    }

}
