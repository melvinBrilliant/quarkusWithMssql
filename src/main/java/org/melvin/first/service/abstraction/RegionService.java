package org.melvin.first.service.abstraction;

import org.melvin.first.dto.RestResponse;
import org.melvin.first.dto.region.RegionDto;
import org.melvin.first.dto.region.UpsertRegionDto;

import java.util.List;

public interface RegionService {
    RestResponse<List<RegionDto>> findAll(Integer page, Integer pageSize);
    RestResponse<RegionDto> findById(Long id);
    RestResponse<Object> saveOne(UpsertRegionDto dto);
    RestResponse<Object> deleteOne(Long id);
}
