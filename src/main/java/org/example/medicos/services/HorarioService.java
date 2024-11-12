package org.example.medicos.services;

import org.example.medicos.models.horarios.Horario;
import org.example.medicos.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioService
{
    final HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository diaRepository){
        this.horarioRepository = diaRepository;
    }

    public List<Horario> findAll() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> findById(long id) {
        return horarioRepository.findById(id);
    }

    public Horario create(Horario horario) {
        return horarioRepository.save(horario);
    }

    public Horario update(Horario horario) {
        return horarioRepository.save(horario);
    }

    public void deleteById(Long id) {
        horarioRepository.deleteById(id);
    }

    public HorarioRepository getDiaRepository() {
        return horarioRepository;
    }


}
