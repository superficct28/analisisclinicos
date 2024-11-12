package org.example.medicos.models.fichas;

import jakarta.validation.constraints.NotNull;

public class FichaDTO {

    @NotNull(message = "La fecha no puede ser nulo")
    private String fecha;

    @NotNull(message = "La cantidad no puede ser nulo")
    private int cantidad;

    private int cantidadVendidad = 0;

    @NotNull(message = "El horarioEspecialidadId no puede ser nulo")
    private long horarioEspecialidadId;

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setCantidadVendidad(int cantidadVendidad) {
        this.cantidadVendidad  = cantidadVendidad;
    }
    public void setHorarioEspecialidadId(long horarioEspecialidadId) {
        this.horarioEspecialidadId = horarioEspecialidadId;
    }
    public String getFecha() {
        return fecha;
    }
    public int getCantidad() {
        return cantidad;
    }
    public int getCantidadVendidad() {
        return cantidadVendidad;
    }
    public long getHorarioEspecialidadId() {
        return horarioEspecialidadId;
    }
}
