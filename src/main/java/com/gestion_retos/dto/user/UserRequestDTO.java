package com.gestion_retos.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "username is required")
    private String username;
    @Email(message = "email format is required")
    private String email;
    @NotBlank(message = "password is required") @Size(min = 8, message = "password must have at least 8 characters")
    private String password;
}
