package org.example.medicos.models.fichas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.medicos.models.horario_especialidad.HorarioEspecialidad;

@Table(name = "fichas")
@Entity
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "cantidad_vendidad", nullable = false)
    private int cantidadVendidad = 0;

    @ManyToOne
    @JoinColumn(name = "horario_servicio_id", nullable = false)
    @NotNull(message = "El horario_servicio_id no puede ser nulo")
    private HorarioEspecialidad horarioEspecialidad;

    public void setId(long id) {
        this.id = id;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setCantidadVendidad(int cantidadVendidad) {
        this.cantidadVendidad  = cantidadVendidad;
    }
    public void setHorarioEspecialidad(HorarioEspecialidad horarioEspecialidad) {
        this.horarioEspecialidad = horarioEspecialidad;
    }
    public long getId() {
        return id;
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
    public HorarioEspecialidad getHorarioEspecialidad() {
        return horarioEspecialidad;
    }
}
