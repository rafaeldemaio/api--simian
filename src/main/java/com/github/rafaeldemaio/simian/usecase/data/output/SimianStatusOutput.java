package com.github.rafaeldemaio.simian.usecase.data.output;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SimianStatusOutput {
    private Integer simians;
    private Integer human;
    private BigDecimal ratio;
}
