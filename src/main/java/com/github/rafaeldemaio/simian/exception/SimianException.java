package com.github.rafaeldemaio.simian.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimianException extends Exception {

    protected Erro erro;

    public SimianException(final Erro erro) {
        super(erro.getMessage());
        this.erro = erro;
    }

    public SimianException(final String message) {
        super(message);
    }

    public SimianException(final String message, final Throwable t) {
        super(message, t);
    }

    public SimianException(final Throwable t) {
        super(t);
    }
}
