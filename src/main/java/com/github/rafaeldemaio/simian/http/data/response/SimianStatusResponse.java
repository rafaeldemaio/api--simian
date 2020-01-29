package com.github.rafaeldemaio.simian.http.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class SimianStatusResponse {

    @JsonProperty("count_simian_dna")
    private Integer simians;
    @JsonProperty("count_human_dna")
    private Integer humans;
    @JsonProperty("ratio")
    private BigDecimal ratio;
}
