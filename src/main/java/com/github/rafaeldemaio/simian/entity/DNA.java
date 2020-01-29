package com.github.rafaeldemaio.simian.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DNA {

    private String dnaString;
    private Boolean isSimian;
}
