package com.github.rafaeldemaio.simian.validation.impl;

import com.github.rafaeldemaio.simian.validation.DnaValidation;
import com.github.rafaeldemaio.simian.entity.enumerator.MessageMapping;
import com.github.rafaeldemaio.simian.exception.Erro;
import com.github.rafaeldemaio.simian.exception.InvalidDNAException;
import com.github.rafaeldemaio.simian.exception.SimianException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DnaValidadionImpl implements DnaValidation {

    private final List<String> dnaType = Arrays.asList(new String[] {"A", "T", "C", "G"});

    @Override
    public void valid(final List<String> dna) throws SimianException {
        if (dna == null || dna.isEmpty()) {
            throw new InvalidDNAException(new Erro(MessageMapping.EMPTY_MATRIZ));
        }
        final Integer weight = dna.get(0).length();
        final Integer height = dna.size();

        if (!weight.equals(height)) {
            throw new InvalidDNAException(new Erro(MessageMapping.INVALID_MATRIZ_FORMAT));
        }
        for (final String line : dna) {
            if (line == null || !weight.equals(line.length())) {
                throw new InvalidDNAException(new Erro(MessageMapping.INVALID_MATRIZ_FORMAT));
            }
            final String[] dnaType = line.split("");
            for (final String sDna
                    : dnaType) {
                if (!this.dnaType.contains(sDna.toUpperCase())) {
                        throw new InvalidDNAException(new Erro(MessageMapping.INVALID_CHAR));
                }
            }
        }
    }
}
