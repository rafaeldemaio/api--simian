package com.github.rafaeldemaio.simian.http.converter;

import com.github.rafaeldemaio.simian.http.data.response.SimianStatusResponse;
import com.github.rafaeldemaio.simian.usecase.data.output.SimianStatusOutput;

public interface SimianControllerConverter {
    SimianStatusResponse fromUsecase(SimianStatusOutput simianStatusOutput);
}
