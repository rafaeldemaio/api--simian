package com.github.rafaeldemaio.simian.usecase.impl;

import com.github.rafaeldemaio.simian.exception.SimianException;
import com.github.rafaeldemaio.simian.usecase.SimianUseCase;
import com.github.rafaeldemaio.simian.usecase.converter.SimianConverter;
import com.github.rafaeldemaio.simian.usecase.data.output.SimianStatusOutput;
import com.github.rafaeldemaio.simian.validation.DnaValidation;
import com.github.rafaeldemaio.simian.entity.DNA;
import com.github.rafaeldemaio.simian.gateway.DnaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SimianUseCaseImpl implements SimianUseCase {
    private final DnaValidation dnaValidation;
    private final SimianConverter converter;
    private final DnaGateway gateway;

    @Override
    public Boolean isSimian(final List<String> input) throws SimianException {
        dnaValidation.valid(input);

        final String dnaString = converter.fromInputToString(input);

        DNA dna = converter.fromGateway(gateway.getDna(dnaString));
        if (dna != null) {
            return dna.getIsSimian();
        }

        Integer dnaTot = 0;

        final List<List<String>> dnaMatriz = converter.fromInputToMatriz(input);

        for (int j = 0; j < dnaMatriz.size(); j++) {
            for (int i = 0; i < dnaMatriz.get(j).size(); i++) {
                //Verifica dna na horizontal
                if ((i + 3) < dnaMatriz.get(j).size()) {
                    final String s = dnaMatriz.get(j).get(i) + dnaMatriz.get(j).get(i + 1) + dnaMatriz.get(j).get(i + 2)
                            + dnaMatriz.get(j).get(i + 3);
                    if (isSimian(s)) {
                        dnaTot++;
                    }
                }
                //Verifica dna na vertical
                if ((j + 3) < dnaMatriz.size()) {
                    final String s = dnaMatriz.get(j).get(i) + dnaMatriz.get(j + 1).get(i) + dnaMatriz.get(j + 2).get(i)
                            + dnaMatriz.get(j + 3).get(i);
                    if (isSimian(s)) {
                        dnaTot++;
                    }
                }
                //Verifica dna na diagonal
                if ((i + 3) < dnaMatriz.get(j).size() && (j + 3) < dnaMatriz.size()) {
                    final String s = dnaMatriz.get(j).get(i) + dnaMatriz.get(j + 1).get(i + 1) + dnaMatriz.get(j + 2).get(i + 2)
                            + dnaMatriz.get(j + 3).get(i + 3);
                    if (isSimian(s)) {
                        dnaTot++;
                    }
                }
                //Verifica dna na diagonal oposta
                if ((i + 3) < dnaMatriz.get(j).size() && (j - 3) >= 0) {
                    final String s = dnaMatriz.get(j).get(i) + dnaMatriz.get(j - 1).get(i + 1) + dnaMatriz.get(j - 2).get(i + 2)
                            + dnaMatriz.get(j - 3).get(i + 3);
                    if (isSimian(s)) {
                        dnaTot++;
                    }
                }
            }
        }

        dna = new DNA(dnaString, dnaTot > 1);
        gateway.saveDna(converter.toGateway(dna));

        return dna.getIsSimian();
    }

    @Override
    public SimianStatusOutput simianStatus() {
        final List<DNA> dnas = converter.fromGatewayList(gateway.getAll());
        if (dnas == null) {
            return new SimianStatusOutput(0, 0, new BigDecimal("0.0"));
        }
        final List<DNA> simians = dnas.stream().filter(dna -> dna.getIsSimian()).collect(Collectors.toList());
        final List<DNA> humans = dnas.stream().filter(dna -> !dna.getIsSimian()).collect(Collectors.toList());
        BigDecimal ratioHumanSimian = null;
        if (humans.isEmpty()) {
            ratioHumanSimian = new BigDecimal("0.0");
        } else {
            ratioHumanSimian = new BigDecimal(Double.valueOf(simians.size()) / Double.valueOf(humans.size()))
                    .setScale(2, RoundingMode.FLOOR);
        }
        return new SimianStatusOutput(simians.size(), humans.size(), ratioHumanSimian);
    }

    private Boolean isSimian(final String s) {
        final char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++){
            if (c != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
