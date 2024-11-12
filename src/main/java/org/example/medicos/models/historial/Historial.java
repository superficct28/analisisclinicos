package org.example.medicos.models.historial;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.medicos.models.pacientes.Paciente;

@Table(name = "historials")
@Entity
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title = "";
    @Column
    private String description = "";

    @ManyToOne
    @JoinColumn(name = "pacienteId", nullable = false)
    @NotNull(message = "El pacienteId no puede ser nulo")
    private Paciente paciente;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPaciente(@NotNull(message = "El pacienteId no puede ser nulo") Paciente paciente) {
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public @NotNull(message = "El pacienteId no puede ser nulo") Paciente getPaciente() {
        return paciente;
    }
}
