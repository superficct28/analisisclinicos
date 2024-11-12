package org.example.medicos.repository;

import org.example.medicos.models.Dia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaRepository  extends JpaRepository<Dia, Long> {
}
