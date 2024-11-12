package org.example.medicos.repository;

import org.example.medicos.models.horario_especialidad.HorarioEspecialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioEspecialidadRepository extends JpaRepository<HorarioEspecialidad, Long> {
    List<HorarioEspecialidad> findAllByEspecialidad_Id(long especialidad_id);
}
