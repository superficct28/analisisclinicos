package org.example.medicos.repository;

import org.example.medicos.models.fichas.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {
    List<Ficha> findAllByHorarioEspecialidadId(long horarioEspecialidadId);
}
