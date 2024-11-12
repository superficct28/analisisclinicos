package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.historial.Historial;
import org.example.medicos.models.historial.HistorialDTO;
import org.example.medicos.models.pacientes.Paciente;
import org.example.medicos.services.HistorialService;
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
@RequestMapping("/historiales")
public class HistorialController {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private HistorialService historialService;

    @GetMapping
    public List<Historial> findAll() {
        return historialService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> findById(@PathVariable long id) {
        Optional<Historial> historial = historialService.findById(id);
        return historial.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<Historial> findByPacienteId(@PathVariable long id) {
        Optional<Historial> historial = historialService.findByPacienteId(id);
        return historial.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Object create(@Valid @RequestBody HistorialDTO historialDTO) {
        Paciente paciente = pacienteService.findById(historialDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Optional<Historial> optionalHistorial = historialService.findByPacienteId(historialDTO.getPacienteId());
        if (!optionalHistorial.isPresent()) {
            Historial historial = new Historial();
            historial.setTitle(historialDTO.getTitle());
            historial.setDescription(historialDTO.getDescription());
            historial.setPaciente(paciente);

            Historial savedHistorial = historialService.create(historial);
            return historialService.create(savedHistorial);
        } else {
            Historial historial = optionalHistorial.get();
            historial.setTitle(historialDTO.getTitle());
            historial.setDescription(historialDTO.getDescription());


            historial.setPaciente(paciente);
            final Historial updatedHistorial = historialService.create(historial);
            return ResponseEntity.ok(updatedHistorial);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Historial> update(@PathVariable long id, @Valid @RequestBody HistorialDTO historialDTO) {
        Optional<Historial> optionalHistorial = historialService.findById(id);

        if (optionalHistorial.isPresent()) {
            Historial historial = optionalHistorial.get();
            historial.setTitle(historialDTO.getTitle());
            historial.setDescription(historialDTO.getDescription());

            Paciente newPaciente = pacienteService.findById(historialDTO.getPacienteId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

            historial.setPaciente(newPaciente);
            final Historial updatedHistorial = historialService.create(historial);
            return ResponseEntity.ok(updatedHistorial);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable Long id) {
        if (historialService.getHistorialRepository().existsById(id)) {
            historialService.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Historial eliminado correctamente");
            response.put("statusCode", 200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
