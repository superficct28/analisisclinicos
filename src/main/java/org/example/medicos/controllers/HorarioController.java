package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.Dia;
import org.example.medicos.models.User;
import org.example.medicos.models.horarios.Horario;
import org.example.medicos.models.horarios.HorarioDTO;
import org.example.medicos.services.DiaService;
import org.example.medicos.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;
    @Autowired
    private DiaService diaService;

    @GetMapping
    public List<Horario> findAll() {
        return horarioService.findAll();
    }

    @PostMapping
    public Horario create(@RequestBody HorarioDTO horarioDTO) {
        Dia dia = diaService.findById(horarioDTO.getDiaId())
                .orElseThrow(() -> new RuntimeException("Dia no encontrado"));
        Horario horario = new Horario();
        horario.setHora_inicio(horarioDTO.getHora_inicio());
        horario.setHora_fin(horarioDTO.getHora_fin());
        horario.setDia(dia);
        return horarioService.create(horario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> findById(@PathVariable Long id) {
        Optional<Horario> horario = horarioService.findById(id);
        return horario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Horario> updateById(@PathVariable Long id,@Valid @RequestBody HorarioDTO horarioDTO) {
        Optional<Horario> optionalHorario = horarioService.findById(id);

        if (optionalHorario.isPresent()) {
            Horario horario = optionalHorario.get();
            horario.setHora_fin(horarioDTO.getHora_inicio());
            horario.setHora_fin(horarioDTO.getHora_fin());

            Dia dia = diaService.findById(horarioDTO.getDiaId())
                    .orElseThrow(() -> new RuntimeException("Dia no encontrado"));

            horario.setDia(dia);
            final Horario updatedHorario = horarioService.update(horario);
            return ResponseEntity.ok(updatedHorario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable Long id) {
        if (horarioService.getDiaRepository().existsById(id)) {
            horarioService.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Horario eliminado correctamente");
            response.put("statusCode", 200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
