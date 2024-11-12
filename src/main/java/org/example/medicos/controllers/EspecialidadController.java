package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.Especialidad;
import org.example.medicos.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @GetMapping
    public List<Especialidad> getEspecialidades() {
        return especialidadRepository.findAll();
    }

    @PostMapping
    public Especialidad createEspecialidad(@RequestBody Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> getEspecialidadById(@PathVariable Long id) {
        Optional<Especialidad> especialidad = especialidadRepository.findById(id);
        return especialidad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Especialidad> updateEspecialidad(@PathVariable Long id,@Valid @RequestBody Especialidad especialidadDetalles) {
        Optional<Especialidad> optionalEspecialidad = especialidadRepository.findById(id);

        if (optionalEspecialidad.isPresent()) {
            Especialidad especialidad = optionalEspecialidad.get();
            especialidad.setNombre(especialidadDetalles.getNombre());
            especialidad.setDescription(especialidadDetalles.getDescription());

            final Especialidad updatedEspecialidad = especialidadRepository.save(especialidad);
            return ResponseEntity.ok(updatedEspecialidad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEspecialidad(@PathVariable Long id) {
        if (especialidadRepository.existsById(id)) {
            especialidadRepository.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Especialidad eliminado correctamente");
            response.put("statusCode", 200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
