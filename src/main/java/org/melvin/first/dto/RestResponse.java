package org.melvin.first.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RestResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data")
    private final T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page")
    private final Integer page;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page_size")
    private final Integer pageSize;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("total_pages")
    private final Integer totalPages;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("total_items")
    private final Long totalItems;

    @JsonProperty("status")
    private final Integer status;

    @JsonProperty("message")
    private final String message;

}
