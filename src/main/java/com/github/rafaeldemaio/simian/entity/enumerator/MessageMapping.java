package com.github.rafaeldemaio.simian.entity.enumerator;

import lombok.Getter;

@Getter
public enum MessageMapping {
    EMPTY_MATRIZ("001", "DNA inválido. Matriz informada está vazia ou é nula"),
    INVALID_MATRIZ_FORMAT("002", "DNA inválido. Matriz deve ser N x N"),
    INVALID_CHAR("003", "DNA inválido. Só podem conter os seguintes caracteres(A, T, C, G)");

    private String code;
    private String message;

    MessageMapping(final String code, final String message){
        this.code = code;
        this.message = message;
    }
}
