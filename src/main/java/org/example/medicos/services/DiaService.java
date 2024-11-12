package org.example.medicos.services;

import org.example.medicos.models.Dia;
import org.example.medicos.repository.DiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaService
{
    final DiaRepository diaRepository;

    public DiaService(DiaRepository diaRepository){
        this.diaRepository = diaRepository;
    }

    public List<Dia> findAll() {
        return diaRepository.findAll();
    }

    public Optional<Dia> findById(long id) {
        return diaRepository.findById(id);
    }

    public Dia create(Dia dia) {
        return diaRepository.save(dia);
    }

    public Dia update(Dia dia) {
        return diaRepository.save(dia);
    }

    public void deleteById(Long id) {
        diaRepository.deleteById(id);
    }

    public DiaRepository getDiaRepository() {
        return diaRepository;
    }
}
