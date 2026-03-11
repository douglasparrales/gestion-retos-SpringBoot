package com.gestion_retos.payload;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse{
    private String message;
    private int status;
    private LocalDate timestamp;
}
