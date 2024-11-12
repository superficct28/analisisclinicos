package org.example.medicos.models.pacientes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.medicos.models.User;


@Table(name = "pacientes")
@Entity
public class Paciente {
    @Id
    @Column(nullable = false)
    private long id;

    @Column()
    private String descripcion;

    @Column()
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "El user_id no puede ser nulo")
    private User user;

    public long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
