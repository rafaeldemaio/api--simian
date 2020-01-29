package com.github.rafaeldemaio.simian.validation;

import com.github.rafaeldemaio.simian.exception.SimianException;

import java.util.List;

public interface DnaValidation {
    void valid(List<String> dna) throws SimianException;
}
