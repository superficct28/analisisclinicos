package org.example.medicos.models.citas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.medicos.models.Especialidad;
import org.example.medicos.models.fichas.Ficha;
import org.example.medicos.models.horarios.Horario;
import org.example.medicos.models.pacientes.Paciente;

@Table(name = "citas")
@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fichaId", nullable = false)
    @NotNull(message = "La fichaId no puede ser nulo")
    private Ficha ficha;

    @ManyToOne
    @JoinColumn(name = "pacienteId", nullable = false)
    @NotNull(message = "el pacienteId no puede ser nulo")
    private Paciente paciente;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFicha(@NotNull(message = "La fichaId no puede ser nulo") Ficha ficha) {
        this.ficha = ficha;
    }

    public void setPaciente(@NotNull(message = "el pacienteId no puede ser nulo") Paciente paciente) {
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public @NotNull(message = "La fichaId no puede ser nulo") Ficha getFicha() {
        return ficha;
    }

    public @NotNull(message = "el pacienteId no puede ser nulo") Paciente getPaciente() {
        return paciente;
    }
}
