package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.roles.Rol;
import org.example.medicos.repository.RolRepository;
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
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @GetMapping
    public List<Rol> getRoles() {
        return rolRepository.findAll();
    }

    @PostMapping
    public Rol createRol(@RequestBody Rol rol) {
        return rolRepository.save(rol);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable Long id) {
        Optional<Rol> rol = rolRepository.findById(id);
        return rol.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable Long id,@Valid @RequestBody Rol rolDetalles) {
        Optional<Rol> optionalRol = rolRepository.findById(id);

        if (optionalRol.isPresent()) {
            Rol rol = optionalRol.get();
            rol.setNombre(rolDetalles.getNombre());

            final Rol updatedRol = rolRepository.save(rol);
            return ResponseEntity.ok(updatedRol);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteRol(@PathVariable Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Rol eliminado correctamente");
            response.put("statusCode", 200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
