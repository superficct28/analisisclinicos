package org.example.medicos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Table(name = "dias")
@Entity
public class Dia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Column(nullable = false)
    private String nombre;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
