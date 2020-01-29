package com.github.rafaeldemaio.simian;

import com.github.rafaeldemaio.simian.exception.InvalidDNAException;
import com.github.rafaeldemaio.simian.exception.SimianException;
import com.github.rafaeldemaio.simian.gateway.DnaGateway;
import com.github.rafaeldemaio.simian.gateway.data.request.DnaGatewayRequest;
import com.github.rafaeldemaio.simian.gateway.data.response.DnaGatewayResponse;
import com.github.rafaeldemaio.simian.usecase.SimianUseCase;
import com.github.rafaeldemaio.simian.usecase.converter.SimianConverter;
import com.github.rafaeldemaio.simian.usecase.data.output.SimianStatusOutput;
import com.github.rafaeldemaio.simian.usecase.impl.SimianUseCaseImpl;
import com.github.rafaeldemaio.simian.validation.DnaValidation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class SimianUseCaseTest {

    @Mock
    private DnaGateway dnaGateway;


    @Autowired
    private SimianConverter converter;
    @Autowired
    private DnaValidation dnaValidation;

    @Test
    public void isSimianValidMatriz() throws SimianException {
        final List<String> dna = Arrays.asList("AAAC", "GAAC", "ACAC", "CTAC");
        final String stringDna = converter.fromInputToString(dna);
        when(dnaGateway.getDna(stringDna)).thenReturn(null);

        final SimianUseCase simianUseCase = new SimianUseCaseImpl(dnaValidation, converter, dnaGateway);

        assertTrue("isSiman", simianUseCase.isSimian(dna));
        verify(dnaGateway, times(1)).saveDna(DnaGatewayRequest.builder()
                .isSimian(Boolean.TRUE)
                .dna(stringDna)
                .build());
    }

    @Test
    public void isHumanValidMatriz() throws SimianException {
        final List<String> dna = Arrays.asList("AAAC", "GAAC", "ACAC", "CTAT");
        final String stringDna = converter.fromInputToString(dna);
        when(dnaGateway.getDna(stringDna)).thenReturn(null);

        final SimianUseCase simianUseCase = new SimianUseCaseImpl(dnaValidation, converter, dnaGateway);

        assertFalse("isHuman", simianUseCase.isSimian(dna));
        verify(dnaGateway, times(1)).saveDna(DnaGatewayRequest.builder()
                .isSimian(Boolean.FALSE)
                .dna(stringDna)
                .build());
    }

    @Test
    public void invalidDnaMatriz() {

        final SimianUseCase simianUseCase = new SimianUseCaseImpl(dnaValidation, converter, dnaGateway);

        assertThrows(InvalidDNAException.class, () ->
                simianUseCase.isSimian(Arrays.asList("AAAC", "GAAC", "ACAC")));
    }

    @Test
    public void nullDnaMatriz() {

        final SimianUseCase simianUseCase = new SimianUseCaseImpl(dnaValidation, converter, dnaGateway);

        assertThrows(InvalidDNAException.class, () ->
                simianUseCase.isSimian(null));
    }

    @Test
    public void invalidCharAtDnaMatriz() {

        final SimianUseCase simianUseCase = new SimianUseCaseImpl(dnaValidation, converter, dnaGateway);

        assertThrows(InvalidDNAException.class, () ->
                simianUseCase.isSimian(Arrays.asList("AAAC", "GAAC", "ACAC", "ABBM")));
    }

    @Test
    public void isSimianStatusWithoutData() {

        when(dnaGateway.getAll()).thenReturn(null);

        final SimianUseCase simianUseCase = new SimianUseCaseImpl(dnaValidation, converter, dnaGateway);
        final SimianStatusOutput simianStatusOutput = simianUseCase.simianStatus();
        assertThat(simianStatusOutput, hasProperty("ratio", equalTo(BigDecimal.valueOf(0.0))));
    }

    @Test
    public void isSimianStatus() {
        final List<DnaGatewayResponse> list = new ArrayList<>();
        list.add(new DnaGatewayResponse("AAACGAACACACCTAC", Boolean.TRUE));
        list.add(new DnaGatewayResponse("AAACGAACACACCTAG", Boolean.FALSE));
        list.add(new DnaGatewayResponse("AAACGAACACACCTAT", Boolean.FALSE));

        when(dnaGateway.getAll()).thenReturn(list);

        final SimianUseCase simianUseCase = new SimianUseCaseImpl(dnaValidation, converter, dnaGateway);
        final SimianStatusOutput simianStatusOutput = simianUseCase.simianStatus();
        assertThat(simianStatusOutput, hasProperty("ratio",
                equalTo(BigDecimal.valueOf(0.50).setScale(2, RoundingMode.FLOOR))));

    }
}
