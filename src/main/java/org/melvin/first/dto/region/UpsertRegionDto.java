package org.melvin.first.dto.region;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpsertRegionDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("region_description")
    private String regionDescription;
}
