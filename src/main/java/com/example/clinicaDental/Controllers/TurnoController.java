package com.example.clinicaDental.Controllers;

import com.example.clinicaDental.Entitys.Turno;
import com.example.clinicaDental.Exceptions.BadRequestException;
import com.example.clinicaDental.Exceptions.NotFoundException;
import com.example.clinicaDental.Services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class TurnoController {
    @Autowired
    TurnoService turnoService;

    @PostMapping
    public ResponseEntity<?> asignarTurno(@RequestBody Turno turno) throws NotFoundException, BadRequestException {
        if (turno.getOdontologo()!=null && turno.getPaciente()!=null)
            return ResponseEntity.ok(turnoService.asignarTurno(turno));
        if (turno.getPaciente()==null)
            throw new BadRequestException("patient required");
        throw new BadRequestException("dentist required");
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodosLosTurnos() {
        return ResponseEntity.ok(turnoService.buscarTodosLosOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable Long id) throws BadRequestException, NotFoundException {
        if (id <= 0)
            throw new BadRequestException("id must be greater than 0");
        Optional<Turno> turno = turnoService.buscarTurno(id);
        if (turno.isPresent())
            return ResponseEntity.ok(turno.get());
        throw new NotFoundException("appointment not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTurno(@PathVariable Long id) throws BadRequestException {
        if (id <= 0)
            throw new BadRequestException("id must be greater than 0");
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok().body("appointment deleted");
    }

    @PutMapping("/update")
    public ResponseEntity<?> actualizarTurno(@RequestBody Turno turno) throws NotFoundException, BadRequestException {
        if (turno != null) {
            Long idTurno = turno.getId();
            if (idTurno != null) {
                Optional<Turno> turnoOptional = turnoService.buscarTurno(idTurno);
                if (turnoOptional.isPresent())
                    return asignarTurno(turno);
                else
                    throw new NotFoundException("appointment doesn't exists");
            }
            throw new BadRequestException("appointment id missing");
        }
        throw new BadRequestException("body missing");
    }
}
