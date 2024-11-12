package org.example.medicos.models.citas;

import jakarta.validation.constraints.NotNull;

public class CitaDTO {

    @NotNull(message = "La fichaId no puede ser nulo")
    private Long fichaId;

    @NotNull(message = "El pacienteId no puede ser nulo")
    private Long pacienteId;

    public void setFichaId(@NotNull(message = "La fichaId no puede ser nulo") Long fichaId) {
        this.fichaId = fichaId;
    }

    public void setPacienteId(@NotNull(message = "El pacienteId no puede ser nulo") Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public @NotNull(message = "La fichaId no puede ser nulo") Long getFichaId() {
        return fichaId;
    }

    public @NotNull(message = "El pacienteId no puede ser nulo") Long getPacienteId() {
        return pacienteId;
    }
}
