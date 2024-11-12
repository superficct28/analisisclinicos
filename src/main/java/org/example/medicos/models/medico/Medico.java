package org.example.medicos.models.medico;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.medicos.models.User;

@Table(name="medicos") @Entity
public class Medico {

    @Id
    @Column(nullable = false)
    private long id;

    @NotNull(message = "La especialidad no puede ser nulo")
    @Column(nullable = false)
    private String especialidad;

    @NotNull(message = "El tipo no puede ser nulo")
    @Column(nullable = false)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "El user_id no puede ser nulo")
    private User user;

    public long getId() {
        return id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getTipo() {
        return tipo;
    }

    public User getUser() {
        return user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
