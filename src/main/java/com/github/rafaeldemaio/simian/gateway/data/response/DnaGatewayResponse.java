package com.github.rafaeldemaio.simian.gateway.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DnaGatewayResponse {
    private String dna;
    private Boolean isSimian;
}
