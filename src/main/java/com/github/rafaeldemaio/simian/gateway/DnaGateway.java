package com.github.rafaeldemaio.simian.gateway;

import com.github.rafaeldemaio.simian.gateway.data.request.DnaGatewayRequest;
import com.github.rafaeldemaio.simian.gateway.data.response.DnaGatewayResponse;

import java.util.List;

public interface DnaGateway {
    void saveDna(DnaGatewayRequest request);

    DnaGatewayResponse getDna(String dnaRequest);

    List<DnaGatewayResponse> getAll();
}
