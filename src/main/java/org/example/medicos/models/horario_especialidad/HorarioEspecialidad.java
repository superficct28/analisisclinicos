package org.example.medicos.models.horario_especialidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.medicos.models.Especialidad;
import org.example.medicos.models.horarios.Horario;
import org.example.medicos.models.medico.Medico;

@Table(name = "horario_especialidad")
@Entity
public class HorarioEspecialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    @NotNull(message = "El medico_id no puede ser nulo")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = false)
    @NotNull(message = "El horario_id no puede ser nulo")
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = false)
    @NotNull(message = "El especialidad_id no puede ser nulo")
    private Especialidad especialidad;

    public void setId(Long id) {
        this.id = id;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    public Long getId() {
        return id;
    }
    public Medico getMedico() {
        return medico;
    }
    public Especialidad getEspecialidad() {
        return especialidad;
    }
    public Horario getHorario() {
        return horario;
    }
}
