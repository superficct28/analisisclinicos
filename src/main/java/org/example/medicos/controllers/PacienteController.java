package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.User;
import org.example.medicos.models.pacientes.Paciente;
import org.example.medicos.models.pacientes.PacienteDTO;
import org.example.medicos.repository.UserRepository;
import org.example.medicos.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Paciente> findAl() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable long id) {
        Optional<Paciente> medico = pacienteService.findById(id);
        return medico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Paciente createPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        User user = userRepository.findById(pacienteDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User no encontrado"));

        Paciente paciente = new Paciente();
        paciente.setId(pacienteDTO.getUserId());
        paciente.setUser(user);

        Paciente savedPaciente = pacienteService.create(paciente);
        return pacienteService.create(savedPaciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updateById(@PathVariable long id, @Valid @RequestBody PacienteDTO pacienteDTO) {
        Optional<Paciente> optionalPaciente = pacienteService.findById(id);

        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();

            User newUser = userRepository.findById(pacienteDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User no encontrado"));

            paciente.setUser(newUser);
            final Paciente updatedPaciente = pacienteService.create(paciente);
            return ResponseEntity.ok(updatedPaciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable Long id) {
        if (pacienteService.getPacienteRepository().existsById(id)) {
            pacienteService.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Paciente eliminado correctamente");
            response.put("statusCode", 200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
