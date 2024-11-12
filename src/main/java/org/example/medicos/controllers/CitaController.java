package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.citas.Cita;
import org.example.medicos.models.citas.CitaDTO;
import org.example.medicos.models.fichas.Ficha;
import org.example.medicos.models.horario_especialidad.HorarioEspecialidad;
import org.example.medicos.models.medico.Medico;
import org.example.medicos.models.pacientes.Paciente;
import org.example.medicos.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;
    @Autowired
    private FichaService fichaService;
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Cita> findAll() {
        return citaService.findAll();
    }

    @GetMapping("/paciente/{id}")
    public List<Cita> findAllByPacienteId(@PathVariable long id) {
        return citaService.findAllByPacienteId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> findById(@PathVariable long id) {
        Optional<Cita> cita = citaService.findById(id);
        return cita.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/medico/{id}")
    public List<Cita> findAllByMedicoId(@PathVariable long id) {
        List<Cita> citas = citaService.findAll();
        List<Cita> citasResult = new ArrayList<>();
        citas.forEach((Cita cita) -> {
            Ficha ficha = cita.getFicha();
            HorarioEspecialidad he = ficha.getHorarioEspecialidad();
            Medico medico = he.getMedico();
            if( medico.getId() == id) {
                citasResult.add(cita);
            }
        });
        return  citasResult;
    }

    @PostMapping
    public Cita create(@Valid @RequestBody CitaDTO citaDTO) {
        Ficha ficha = fichaService.findById(citaDTO.getFichaId())
                .orElseThrow(() -> new RuntimeException("Ficha no encontrado"));
        Paciente paciente = pacienteService.findById(citaDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Cita cita = new Cita();
        ficha.setCantidadVendidad(ficha.getCantidadVendidad() + 1);
        Ficha updated = fichaService.update(ficha);
        cita.setFicha(updated);
        cita.setPaciente(paciente);

        Cita savedCita = citaService.create(cita);
        return citaService.create(savedCita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateById(@PathVariable long id, @Valid @RequestBody CitaDTO citaDTO ) {
        Optional<Cita> optionalCita = citaService.findById(id);

        if (optionalCita.isPresent()) {
            Cita cita = optionalCita.get();

            Ficha ficha = fichaService.findById(citaDTO.getFichaId())
                    .orElseThrow(() -> new RuntimeException("Ficha no encontrado"));
            Paciente paciente = pacienteService.findById(citaDTO.getPacienteId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            cita.setFicha(ficha);
            cita.setPaciente(paciente);

            final Cita updatedFicha = citaService.update(cita);
            return ResponseEntity.ok(updatedFicha);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable Long id) {
        if (citaService.getCitaRepository().existsById(id)) {
            citaService.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Cita eliminado correctamente");
            response.put("statusCode", 200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
