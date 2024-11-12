package org.example.medicos.repository;

import org.example.medicos.models.historial.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
    Optional<Historial> findByPacienteId(Long pacienteId);
}
