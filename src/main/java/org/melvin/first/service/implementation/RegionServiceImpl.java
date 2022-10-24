package org.melvin.first.service.implementation;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.melvin.first.dto.RestResponse;
import org.melvin.first.dto.region.RegionDto;
import org.melvin.first.dto.region.UpsertRegionDto;
import org.melvin.first.entity.Region;
import org.melvin.first.service.abstraction.RegionService;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

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
    public RestResponse<RegionDto> findById(Long id) {
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
    public RestResponse<Object> saveOne(UpsertRegionDto dto) {
        return null;
    }

    @Override
    public RestResponse<Object> deleteOne(Long id) {
        return null;
    }

}
