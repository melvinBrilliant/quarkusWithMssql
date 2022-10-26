package org.melvin.first.service.abstraction;

import org.melvin.first.dto.RestResponse;
import org.melvin.first.dto.territory.TerritoryDto;
import org.melvin.first.dto.territory.UpsertTerritoryDto;

import javax.validation.Valid;
import java.util.List;

public interface TerritoryService {
    RestResponse<List<TerritoryDto>> findAll(Integer page, Integer pageSize);
    RestResponse<TerritoryDto> findById(String id);
    RestResponse<Object> saveOne(@Valid UpsertTerritoryDto dto, String httpMethod);
    RestResponse<Object> deleteOne(String id);
    List<TerritoryDto> findAllWithDto();
}
