package org.example.medicos.repository;

import org.example.medicos.models.horarios.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
}
