package org.melvin.first.dto.region;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpsertRegionDto {

    @JsonProperty("id")
    @NotNull(message = "how tf this id could be null")
    private Integer id;

    @JsonProperty("region_description")
    @NotBlank(message = "blank region name is no bueno")
    private String regionDescription;
}
