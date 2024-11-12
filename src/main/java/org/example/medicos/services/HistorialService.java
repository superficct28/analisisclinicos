package org.example.medicos.services;

import org.example.medicos.models.historial.Historial;
import org.example.medicos.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialService {
    @Autowired
    private HistorialRepository historialRepository;

    public List<Historial> findAll() {
        return historialRepository.findAll();
    }
    public Optional<Historial> findById(Long id) {
        return historialRepository.findById(id);
    }
    public Optional<Historial> findByPacienteId(Long id) {
        return historialRepository.findByPacienteId(id);
    }
    public Historial create(Historial historial) {
        return historialRepository.save(historial);
    }
    public Historial update(Historial historial) {
        return historialRepository.save(historial);
    }
    public void deleteById(Long id) {
        historialRepository.deleteById(id);
    }

    public HistorialRepository getHistorialRepository() {
        return historialRepository;
    }
}
