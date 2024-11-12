package org.example.medicos.services;

import org.example.medicos.models.pacientes.Paciente;
import org.example.medicos.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> findById(long id) {
        return pacienteRepository.findById(id);
    }
    public Paciente create(Paciente user) {
        return pacienteRepository.save(user);
    }
    public Paciente update(Paciente user) {
        return pacienteRepository.save(user);
    }
    public void deleteById(long id) {
        pacienteRepository.deleteById(id);
    }
    public PacienteRepository getPacienteRepository() {
        return pacienteRepository;
    }
}
