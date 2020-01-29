package com.github.rafaeldemaio.simian.usecase.converter.impl;

import com.github.rafaeldemaio.simian.entity.DNA;
import com.github.rafaeldemaio.simian.gateway.data.request.DnaGatewayRequest;
import com.github.rafaeldemaio.simian.gateway.data.response.DnaGatewayResponse;
import com.github.rafaeldemaio.simian.usecase.converter.SimianConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SimianConverterImpl implements SimianConverter {

    @Override
    public List<List<String>> fromInputToMatriz(final List<String> dna) {
        if (dna == null) {
            return null;
        }
        final List retorno = new ArrayList();
        for (final String sDna : dna) {
            retorno.add(Arrays.asList(sDna.toUpperCase().split("")));
        }
        return retorno;
    }

    @Override
    public String fromInputToString(final List<String> dna) {
        if (dna == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String sDna : dna) {
            sb.append(sDna.toUpperCase());
        }
        return sb.toString();
    }

    @Override
    public DnaGatewayRequest toGateway(final DNA dna) {
        if (dna == null) {
            return null;
        }
        return DnaGatewayRequest.builder()
                .dna(dna.getDnaString())
                .isSimian(dna.getIsSimian())
                .build();
    }

    @Override
    public DNA fromGateway(final DnaGatewayResponse dna) {
        if (dna == null) {
            return null;
        }
        return DNA.builder()
                .dnaString(dna.getDna())
                .isSimian(dna.getIsSimian())
                .build();
    }

    @Override
    public List<DNA> fromGatewayList(final List<DnaGatewayResponse> all) {
        if (all == null) {
            return null;
        }
        final List<DNA> response = new ArrayList<>();
        all.forEach(r -> response.add(fromGateway(r)));
        return response;
    }
}
