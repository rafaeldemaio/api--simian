package com.github.rafaeldemaio.simian.http.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimianResponse {

    @JsonProperty("is_simian")
    private Boolean isSimian;
}
