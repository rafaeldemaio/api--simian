package com.github.rafaeldemaio.simian.http.data.request;

import lombok.Data;

import java.util.List;

@Data
public class SimianRequest {
    private List<String> dna;
}
