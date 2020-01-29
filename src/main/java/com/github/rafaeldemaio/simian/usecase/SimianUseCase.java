package com.github.rafaeldemaio.simian.usecase;

import com.github.rafaeldemaio.simian.exception.SimianException;
import com.github.rafaeldemaio.simian.usecase.data.output.SimianStatusOutput;

import java.util.List;

public interface SimianUseCase {
    Boolean isSimian(List<String> dna) throws SimianException;

    SimianStatusOutput simianStatus();
}
