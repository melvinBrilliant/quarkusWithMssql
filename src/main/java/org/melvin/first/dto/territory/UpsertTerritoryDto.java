package org.melvin.first.dto.territory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertTerritoryDto {

    @JsonProperty("id")
    @NotBlank(message = "blank id no bueno")
    private String id;

    @JsonProperty("territory_description")
    @NotBlank(message = "hehe")
    private String territoryDescription;

    @JsonProperty("region_id")
    @NotNull(message = "hoho")
    private Integer regionId;

}
