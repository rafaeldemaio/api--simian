package com.github.rafaeldemaio.simian.http.converter.impl;

import com.github.rafaeldemaio.simian.http.data.response.SimianStatusResponse;
import com.github.rafaeldemaio.simian.http.converter.SimianControllerConverter;
import com.github.rafaeldemaio.simian.usecase.data.output.SimianStatusOutput;
import org.springframework.stereotype.Component;

@Component
public class SimianControllerConverterImpl implements SimianControllerConverter {

    @Override
    public SimianStatusResponse fromUsecase(final SimianStatusOutput simianStatusOutput) {
        if (simianStatusOutput == null) {
            return null;
        }
        return SimianStatusResponse.builder()
                .humans(simianStatusOutput.getHuman())
                .simians(simianStatusOutput.getSimians())
                .ratio(simianStatusOutput.getRatio())
                .build();
    }
}
