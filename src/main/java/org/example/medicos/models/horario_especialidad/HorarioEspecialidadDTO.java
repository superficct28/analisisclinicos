package org.example.medicos.models.horario_especialidad;

import jakarta.validation.constraints.NotNull;

public class HorarioEspecialidadDTO {

    private boolean estado = true;

    @NotNull(message = "El medicoId no puede ser nulo")
    private Long medicoId;

    @NotNull(message = "El horarioId no puede ser nulo")
    private Long horarioId;

    @NotNull(message = "El especialidadId no puede ser nulo")
    private Long especialidadId;

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }
    public void setEspecialidad(Long especialidadId) {
        this.especialidadId = especialidadId;
    }
    public void setHorario(Long horarioId) {
        this.horarioId = horarioId;
    }
    public Long getMedicoId() {
        return medicoId;
    }
    public Long getEspecialidadId() {
        return especialidadId;
    }
    public Long getHorarioId() {
        return horarioId;
    }
}
