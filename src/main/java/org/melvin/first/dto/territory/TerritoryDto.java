package org.melvin.first.dto.territory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.melvin.first.entity.Territory;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TerritoryDto {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("territory_description")
    private final String territoryDescription;

    @JsonProperty("region_id")
    private final Integer regionId;

    public static TerritoryDto toTerritoryDto(Territory territory) {
        return new TerritoryDto(
                territory.getId(),
                territory.getTerritoryDescription(),
                territory.getRegionID().getId()
        );
    }

    public static List<TerritoryDto> toTerritoryDtoList(List<Territory> territories) {
        return territories.stream()
                .map(TerritoryDto::toTerritoryDto)
                .collect(Collectors.toList());
    }

}
