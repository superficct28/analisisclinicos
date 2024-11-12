package org.example.medicos.models.medico;

import jakarta.validation.constraints.NotNull;

public class MedicoDTO {

    @NotNull(message = "La especialidad no puede ser nulo")
    private String especialidad;

    @NotNull(message = "El tipo no puede ser nulo")
    private String tipo;

    @NotNull(message = "El user_id no puede ser nulo")
    private long userId;


    public String getEspecialidad() {
        return especialidad;
    }

    public String getTipo() {
        return tipo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
