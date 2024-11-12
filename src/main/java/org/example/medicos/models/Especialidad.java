package org.example.medicos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Table(name = "especialidades")
@Entity
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "La description no puede ser nulo")
    @Column(nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
