package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.medico.Medico;
import org.example.medicos.models.User;
import org.example.medicos.models.medico.MedicoDTO;
import org.example.medicos.repository.MedicoRepository;
import org.example.medicos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Medico> getMedicos() {
        return medicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable long id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        return medico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medico createMedico(@Valid @RequestBody MedicoDTO medicoDto) {
        User user = userRepository.findById(medicoDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User no encontrado"));

        Medico medico = new Medico();
        medico.setId(medicoDto.getUserId());
        medico.setEspecialidad(medicoDto.getEspecialidad());
        medico.setTipo(medicoDto.getTipo());
        medico.setUser(user);

        Medico savedMedico = medicoRepository.save(medico);
        return medicoRepository.save(savedMedico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> updateMedico(@PathVariable long id, @Valid @RequestBody MedicoDTO medicoDto) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);

        if (optionalMedico.isPresent()) {
            Medico medico = optionalMedico.get();
            medico.setEspecialidad(medicoDto.getEspecialidad());
            medico.setTipo(medicoDto.getTipo());

            User newUser = userRepository.findById(medicoDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User no encontrado"));

            medico.setUser(newUser);
            final Medico updatedMedico = medicoRepository.save(medico);
            return ResponseEntity.ok(updatedMedico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteMedico(@PathVariable Long id) {
        if (medicoRepository.existsById(id)) {
            medicoRepository.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Medico eliminado correctamente");
            response.put("statusCode", 200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
