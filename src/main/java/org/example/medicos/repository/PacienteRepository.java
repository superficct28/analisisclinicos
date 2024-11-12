package org.example.medicos.repository;

import org.example.medicos.models.pacientes.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
