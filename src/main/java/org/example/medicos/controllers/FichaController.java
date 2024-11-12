package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.fichas.Ficha;
import org.example.medicos.models.fichas.FichaDTO;
import org.example.medicos.models.horario_especialidad.HorarioEspecialidad;
import org.example.medicos.services.FichaService;
import org.example.medicos.services.HorarioEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/fichas")
public class FichaController {

    @Autowired
    private FichaService fichaService;
    @Autowired
    private HorarioEspecialidadService horarioEspecialidadService;


    @GetMapping
    public List<Ficha> findAll() {
        return fichaService.findAll();
    }

    @GetMapping("/fichas/{horarioEspecialidadId}")
    public List<Ficha> findAllById(@PathVariable long horarioEspecialidadId ) {
        return fichaService.findAllById(horarioEspecialidadId );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ficha> findById(@PathVariable long id) {
        Optional<Ficha> ficha = fichaService.findById(id);
        return ficha.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ficha create(@Valid @RequestBody FichaDTO fichaDTO) {
        HorarioEspecialidad horarioEspecialidad = horarioEspecialidadService.findById(fichaDTO.getHorarioEspecialidadId())
                .orElseThrow(() -> new RuntimeException("horarioServicio no encontrado"));

        Ficha ficha = new Ficha();
        ficha.setFecha(fichaDTO.getFecha());
        ficha.setCantidad(fichaDTO.getCantidad());
        ficha.setHorarioEspecialidad(horarioEspecialidad);

        Ficha savedFicha = fichaService.create(ficha);
        return fichaService.create(savedFicha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ficha> updateById(@PathVariable long id, @Valid @RequestBody FichaDTO fichaDTO) {
        Optional<Ficha> optionalFicha = fichaService.findById(id);

        if (optionalFicha.isPresent()) {
            Ficha ficha = optionalFicha.get();
            HorarioEspecialidad horarioEspecialidad = horarioEspecialidadService.findById(fichaDTO.getHorarioEspecialidadId())
                    .orElseThrow(() -> new RuntimeException("HorarioEspecialidad no encontrado"));

            ficha.setFecha(fichaDTO.getFecha());
            ficha.setCantidad(fichaDTO.getCantidad());
            ficha.setCantidadVendidad(fichaDTO.getCantidadVendidad());
            ficha.setHorarioEspecialidad(horarioEspecialidad);

            final Ficha updatedFicha = fichaService.update(ficha);
            return ResponseEntity.ok(updatedFicha);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable Long id) {
        if (fichaService.getFichaRepository().existsById(id)) {
            fichaService.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Ficha eliminado correctamente");
            response.put("statusCode", 200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
