package com.github.rafaeldemaio.simian.exception;

public class InvalidDNAException extends SimianException {

    public InvalidDNAException(final Erro erro) {
        super(erro.getMessage());
        this.erro = erro;
    }

    public InvalidDNAException(final String message) {
        super(message);
    }

    public InvalidDNAException(final String message, final Throwable t) {
        super(message, t);
    }

    public InvalidDNAException(final Throwable t) {
        super(t);
    }
}
