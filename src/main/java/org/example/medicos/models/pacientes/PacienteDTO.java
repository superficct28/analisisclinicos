package org.example.medicos.models.pacientes;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class PacienteDTO {

    private String descripcion = "";

    private String tipo = "";

    @NotNull(message = "El userId no puede ser nulo")
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
