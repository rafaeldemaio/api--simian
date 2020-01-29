package com.github.rafaeldemaio.simian.gateway.data.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DnaGatewayRequest {
    private String dna;
    private Boolean isSimian;
}
