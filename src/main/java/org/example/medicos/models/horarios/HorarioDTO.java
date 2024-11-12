package org.example.medicos.models.horarios;

import jakarta.validation.constraints.NotNull;

public class HorarioDTO {

    @NotNull(message = "El hora_inicio no puede ser nulo")
    private String hora_inicio;

    @NotNull(message = "El hora_fin no puede ser nulo")
    private String hora_fin;

    @NotNull(message = "El diaId no puede ser nulo")
    private Long diaId;

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }
    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }
    public void setDiaId(Long diaId) {
        this.diaId = diaId;
    }
    public String getHora_inicio() {
        return hora_inicio;
    }
    public String getHora_fin() {
        return hora_fin;
    }
    public Long getDiaId() {
        return diaId;
    }

}
