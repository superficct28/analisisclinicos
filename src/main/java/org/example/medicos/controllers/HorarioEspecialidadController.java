package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.Especialidad;
import org.example.medicos.models.fichas.Ficha;
import org.example.medicos.models.horario_especialidad.HorarioEspecialidad;
import org.example.medicos.models.horario_especialidad.HorarioEspecialidadDTO;
import org.example.medicos.models.horarios.Horario;
import org.example.medicos.models.medico.Medico;
import org.example.medicos.repository.EspecialidadRepository;
import org.example.medicos.repository.MedicoRepository;
import org.example.medicos.services.HorarioEspecialidadService;
import org.example.medicos.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/horarios_especialidad")
public class HorarioEspecialidadController {

    @Autowired
    private HorarioEspecialidadService horarioEspecialidadService;
    @Autowired
    private HorarioService horarioService;
    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public List<HorarioEspecialidad> findAll() {
        return horarioEspecialidadService.findAll();
    }

    @GetMapping("/dias/{especialidadId}")
    public List<HorarioEspecialidad> findAllById(@PathVariable long especialidadId ) {
        return horarioEspecialidadService.findAllByEspecialidadId(especialidadId );
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioEspecialidad> findById(@PathVariable long id) {
        Optional<HorarioEspecialidad> horario_especialidad = horarioEspecialidadService.findById(id);
        return horario_especialidad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public HorarioEspecialidad create(@Valid @RequestBody HorarioEspecialidadDTO hor_especialidadDTO) {
        Medico medico = medicoRepository.findById(hor_especialidadDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
        Horario horario = horarioService.findById(hor_especialidadDTO.getHorarioId())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
        Especialidad especialidad = especialidadRepository.findById(hor_especialidadDTO.getEspecialidadId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrado"));

        HorarioEspecialidad horario_especialidad = new HorarioEspecialidad();
        horario_especialidad.setMedico(medico);
        horario_especialidad.setHorario(horario);
        horario_especialidad.setEspecialidad(especialidad);

        HorarioEspecialidad savedHorarioEspecialidad = horarioEspecialidadService.create(horario_especialidad);
        return horarioEspecialidadService.create(savedHorarioEspecialidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioEspecialidad> updateById(@PathVariable long id, @Valid @RequestBody HorarioEspecialidadDTO horarioEspecialidadDTO) {
        Optional<HorarioEspecialidad> optionalHorarioEspecialidad = horarioEspecialidadService.findById(id);

        if (optionalHorarioEspecialidad.isPresent()) {
            HorarioEspecialidad horarioEspecialidad = optionalHorarioEspecialidad.get();

            Medico medico = medicoRepository.findById(horarioEspecialidadDTO.getMedicoId())
                    .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
            Horario horario = horarioService.findById(horarioEspecialidadDTO.getHorarioId())
                    .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
            Especialidad especialidad = especialidadRepository.findById(horarioEspecialidadDTO.getEspecialidadId())
                    .orElseThrow(() -> new RuntimeException("Especialidad no encontrado"));

            horarioEspecialidad.setMedico(medico);
            horarioEspecialidad.setHorario(horario);
            horarioEspecialidad.setEspecialidad(especialidad);

            final HorarioEspecialidad updatedHorarioEspecialidad = horarioEspecialidadService.update(horarioEspecialidad);
            return ResponseEntity.ok(updatedHorarioEspecialidad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable Long id) {
        if (horarioEspecialidadService.getHorarioEspecialidadRepository().existsById(id)) {
            horarioEspecialidadService.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "HorarioEspecialidad eliminado correctamente");
            response.put("statusCode", 200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
