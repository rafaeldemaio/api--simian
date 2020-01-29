package com.github.rafaeldemaio.simian.usecase.converter;

import com.github.rafaeldemaio.simian.entity.DNA;
import com.github.rafaeldemaio.simian.gateway.data.request.DnaGatewayRequest;
import com.github.rafaeldemaio.simian.gateway.data.response.DnaGatewayResponse;

import java.util.List;

public interface SimianConverter {
    List<List<String>> fromInputToMatriz(List<String> dna);

    String fromInputToString(List<String> dna);

    DnaGatewayRequest toGateway(DNA dna);

    DNA fromGateway(DnaGatewayResponse dna);

    List<DNA> fromGatewayList(List<DnaGatewayResponse> all);
}
