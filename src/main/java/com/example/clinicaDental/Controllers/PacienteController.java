package com.example.clinicaDental.Controllers;

import com.example.clinicaDental.Entitys.Domicilio;
import com.example.clinicaDental.Entitys.Paciente;
import com.example.clinicaDental.Exceptions.BadRequestException;
import com.example.clinicaDental.Exceptions.NotFoundException;
import com.example.clinicaDental.Services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("patients")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<Paciente> buscarPacientePorMail(@RequestParam("email") String email) throws BadRequestException, NotFoundException {
        if (email == null || email.equals("")) {
            throw new BadRequestException("email missing");
        }
        Optional<Paciente> paciente = pacienteService.buscarPacientePorEmail(email);
        if (paciente.isPresent()) return ResponseEntity.ok(paciente.get());
        throw new NotFoundException("patient not found");
    }

    @PostMapping
    public ResponseEntity<Paciente> agregarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        if (paciente == null) {
            throw new BadRequestException("body missing");
        } else if (paciente.getId() == null) {
            return ResponseEntity.ok(pacienteService.agregarPaciente(paciente));
        }
        throw new BadRequestException("patient id is not required");
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) throws BadRequestException, NotFoundException {
        if (paciente != null) {
            Domicilio domicilio = paciente.getDomicilio();
            if (domicilio != null) {
                if (domicilio.getId() == null) throw new BadRequestException("address id required");
            } else throw new BadRequestException("address information missing");
            Long idPaciente = paciente.getId();
            if (idPaciente != null) {
                Optional<Paciente> optionalPaciente = pacienteService.buscarUnPaciente(idPaciente);
                if (optionalPaciente.isPresent())
                    return ResponseEntity.ok(pacienteService.actualizarUnPaciente(paciente));
                else throw new NotFoundException("patient not found to update");
            }
            throw new BadRequestException("patient id required");
        }
        throw new BadRequestException("patient information missing");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Paciente>> buscarTodosLosPacientes() {
        return ResponseEntity.ok().body(pacienteService.buscarTodosLosPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPaciente(@PathVariable("id") Long id) throws NotFoundException, BadRequestException {
        if (id <= 0) throw new BadRequestException("id must be greater than 0");
        Optional<Paciente> paciente = pacienteService.buscarUnPaciente(id);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente.get());
        }
        throw new NotFoundException("patient not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPaciente(@PathVariable Long id) throws BadRequestException {
        if (id <= 0) throw new BadRequestException("id must be greater than 0");
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("patient " + id + " deleted");
    }
}
