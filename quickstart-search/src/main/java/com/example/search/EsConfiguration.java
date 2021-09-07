package com.example.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
public class EsConfiguration {

    @JsonProperty
    @NotBlank(message = "This field cannot be empty")
    private String serverUri;
}

