package org.example.medicos.repository;

import org.example.medicos.models.citas.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    public List<Cita> findAllByPacienteId(long pacienteId);
}
