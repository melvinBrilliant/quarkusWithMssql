package org.melvin.first.dto.region;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.melvin.first.entity.Region;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RegionDto {

    @JsonProperty("id")
    private final Long id;

    @JsonProperty("region_description")
    private final String regionDescription;

    public static RegionDto toRegionDto(Region region) {
        return new RegionDto(
                region.id,
                region.getRegionDescription()
        );
    }

    public static List<RegionDto> toRegionDtoList(List<Region> regions) {
         return regions.stream()
                .map(RegionDto::toRegionDto)
                .collect(Collectors.toList());
    }

}
