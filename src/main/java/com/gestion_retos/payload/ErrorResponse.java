package com.gestion_retos.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse{
    private String message;
    private int status;
    private LocalDate timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;
}
