package org.example.medicos.models.horarios;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.medicos.models.Dia;
import org.example.medicos.models.User;

@Table(name = "horarios")
@Entity
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hora_inicio;

    @Column(nullable = false)
    private String hora_fin;

    @ManyToOne
    @JoinColumn(name = "dia_id", nullable = false)
    @NotNull(message = "El dia_id no puede ser nulo")
    private Dia dia;

    public void setId(Long id) {
        this.id = id;
    }
    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }
    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }
    public void setDia(Dia dia) {
        this.dia = dia;
    }
    public Long getId() {
        return id;
    }
    public String getHora_inicio() {
        return hora_inicio;
    }
    public String getHora_fin() {
        return hora_fin;
    }
    public Dia getDia() {
        return dia;
    }
}
