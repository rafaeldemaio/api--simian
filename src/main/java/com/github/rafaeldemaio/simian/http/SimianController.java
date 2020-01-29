package com.github.rafaeldemaio.simian.http;

import com.github.rafaeldemaio.simian.http.converter.SimianControllerConverter;
import com.github.rafaeldemaio.simian.http.data.request.SimianRequest;
import com.github.rafaeldemaio.simian.http.data.response.SimianResponse;
import com.github.rafaeldemaio.simian.http.data.response.SimianStatusResponse;
import com.github.rafaeldemaio.simian.exception.SimianException;
import com.github.rafaeldemaio.simian.usecase.SimianUseCase;
import com.github.rafaeldemaio.simian.usecase.data.output.SimianStatusOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("simian")
@RequiredArgsConstructor
@Log4j2
public class SimianController {

    private final SimianUseCase simianUseCase;
    private final SimianControllerConverter converter;

    @PostMapping
    public ResponseEntity<SimianResponse> isSimian(final @RequestBody SimianRequest request)
            throws ResponseStatusException {
        try {
            Boolean isSimian = simianUseCase.isSimian(request.getDna());
            return ResponseEntity.ok(new SimianResponse(isSimian));
        } catch (SimianException e) {
            log.error("Erro na requisição da chamada do método isSimian");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<SimianStatusResponse> status() throws  ResponseStatusException {
        try {
            final SimianStatusOutput simianStatusOutput = simianUseCase.simianStatus();
            return ResponseEntity.ok(converter.fromUsecase(simianStatusOutput));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
