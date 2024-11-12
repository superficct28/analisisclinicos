package org.example.medicos.models.historial;

import jakarta.validation.constraints.NotNull;

public class HistorialDTO {

    private String title = "";
    private String description = "";
    @NotNull(message = "El pacienteId no puede ser nulo")
    private Long pacienteId;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getPacienteId() {
        return pacienteId;
    }
}
