package com.github.rafaeldemaio.simian.exception;

import com.github.rafaeldemaio.simian.entity.enumerator.MessageMapping;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Erro {

    private String message;
    private String code;

    public Erro(final MessageMapping mapping) {
        this.message = mapping.getMessage();
    }
}
