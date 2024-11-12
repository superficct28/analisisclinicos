package org.example.medicos.services;

import org.example.medicos.models.horario_especialidad.HorarioEspecialidad;
import org.example.medicos.repository.HorarioEspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioEspecialidadService {
    @Autowired
    final HorarioEspecialidadRepository horarioEspecialidadRepository;

    public HorarioEspecialidadService(HorarioEspecialidadRepository especialidadRepository) {
        horarioEspecialidadRepository = especialidadRepository;
    }

    public List<HorarioEspecialidad> findAll() {
        return horarioEspecialidadRepository.findAll();
    }
    public List<HorarioEspecialidad> findAllByEspecialidadId(long especialidadId) {
        return horarioEspecialidadRepository.findAllByEspecialidad_Id(especialidadId);
    }

    public Optional<HorarioEspecialidad> findById(long id) {
        return horarioEspecialidadRepository.findById(id);
    }

    public HorarioEspecialidad create(HorarioEspecialidad horarioEspecialidad) {
        return horarioEspecialidadRepository.save(horarioEspecialidad);
    }

    public HorarioEspecialidad update(HorarioEspecialidad horarioEspecialidad) {
        return horarioEspecialidadRepository.save(horarioEspecialidad);
    }

    public void deleteById(long id) {
        horarioEspecialidadRepository.deleteById(id);
    }

    public HorarioEspecialidadRepository getHorarioEspecialidadRepository() {
        return horarioEspecialidadRepository;
    }

}
