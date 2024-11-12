package org.example.medicos.models.user;

import jakarta.validation.constraints.NotNull;

public class LoginRequestDTO {

    @NotNull(message = "El email no puede ser nulo")
    private String email;

    @NotNull(message = "El password no puede ser nulo")
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
