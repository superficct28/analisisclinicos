package org.example.medicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.medicos.models.medico.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
