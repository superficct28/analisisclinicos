package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.Dia;
import org.example.medicos.services.DiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/dias")
public class DiaController {

    @Autowired
    private DiaService diaService;

    @GetMapping
    public List<Dia> findAll() {
        return diaService.findAll();
    }

    @PostMapping
    public Dia create(@RequestBody Dia dia) {
        return diaService.create(dia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dia> findById(@PathVariable Long id) {
        Optional<Dia> dia = diaService.findById(id);
        return dia.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Dia> updateById(@PathVariable Long id,@Valid @RequestBody Dia diaDetalles) {
        Optional<Dia> optionalDia = diaService.findById(id);
        if (optionalDia.isPresent()) {
            Dia dia = optionalDia.get();
            dia.setNombre(diaDetalles.getNombre());
            final Dia updatedDia = diaService.update(dia);
            return ResponseEntity.ok(updatedDia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable Long id) {
        if (diaService.getDiaRepository().existsById(id)) {
            diaService.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Dia eliminado correctamente");
            response.put("statusCode", 200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
