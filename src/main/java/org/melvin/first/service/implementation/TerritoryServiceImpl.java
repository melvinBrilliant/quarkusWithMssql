package org.melvin.first.service.implementation;

import io.quarkus.panache.common.Page;
import org.apache.http.HttpStatus;
import org.melvin.first.dto.RestResponse;
import org.melvin.first.dto.territory.TerritoryDto;
import org.melvin.first.dto.territory.UpsertTerritoryDto;
import org.melvin.first.entity.Region;
import org.melvin.first.entity.Territory;
import org.melvin.first.service.abstraction.TerritoryService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TerritoryServiceImpl implements TerritoryService {

    @Override
    public RestResponse<List<TerritoryDto>> findAll(Integer page, Integer pageSize) {
        var findAllTerritories = Territory.findAll();
        List<Territory> territories = findAllTerritories.page(Page.of(page - 1, pageSize)).list();
        Integer totalPages = findAllTerritories.pageCount();
        Long totalItems = findAllTerritories.count();
        List<TerritoryDto> data = TerritoryDto.toTerritoryDtoList(territories);
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
    public RestResponse<TerritoryDto> findById(String id) {
        TerritoryDto data = TerritoryDto.toTerritoryDto(Territory.findById(id));
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
    public RestResponse<Object> saveOne(@Valid UpsertTerritoryDto dto,
                                        String httpMethod) {
        Optional<Region> regionOptional = Region.findByIdOptional(dto.getRegionId());
        if (httpMethod.equals("PUT")) {
            Territory.findByIdOptional(dto.getId())
                    .orElseThrow(() -> new NotFoundException("territory not found")
            );
        }
        Region region = regionOptional.orElseThrow(() ->
                new NotFoundException("region not found")
        );
        Territory territory = Territory.builder()
                .id(dto.getId())
                .territoryDescription(dto.getTerritoryDescription())
                .regionID(region)
                .build();
        territory.persist();
        return new RestResponse<>(
                null,
                null,
                null,
                null,
                null,
                200, "SUCCESS"
        );
    }

    @Override
    @Transactional
    public RestResponse<Object> deleteOne(String id) throws NotFoundException {
        Territory.findByIdOptional(id).orElseThrow(() -> new NotFoundException("no such territory exists"));
        Territory.deleteById(id);
        return new RestResponse<>(
                null,
                null,
                null,
                null,
                null,
                HttpStatus.SC_ACCEPTED,
                "SUCCESS"
        );
    }

}
