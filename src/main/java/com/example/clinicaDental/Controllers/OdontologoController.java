package com.example.clinicaDental.Controllers;

import com.example.clinicaDental.Entitys.Odontologo;
import com.example.clinicaDental.Exceptions.BadRequestException;
import com.example.clinicaDental.Exceptions.NotFoundException;
import com.example.clinicaDental.Services.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentists")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<Odontologo> buscarOdontologoPorMatricula(@RequestParam("matricula") String matricula) throws NotFoundException, BadRequestException {
        if (matricula == null || matricula.equals("")) {
            throw new BadRequestException("matricula missing");
        }
        Optional<Odontologo> odontologo = odontologoService.buscarOdontologoPorMatricula(matricula);
        if (odontologo.isPresent())
            return ResponseEntity.ok(odontologo.get());
        throw new NotFoundException("dentist not found");
    }

    @PostMapping
    public ResponseEntity<Odontologo> agregarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        if (odontologo == null) {
            throw new BadRequestException("body missing");
        } else if (odontologo.getId() == null) {
            return ResponseEntity.ok(odontologoService.agregaOdontologo(odontologo));
        }
        throw new BadRequestException("dentist id is not required");
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException, NotFoundException {
        if (odontologo != null) {
            Long idOdontologo = odontologo.getId();
            if (idOdontologo != null) {
                Optional<Odontologo> optionalPaciente = odontologoService.buscarUnOdontologo(idOdontologo);
                if (optionalPaciente.isPresent())
                    return ResponseEntity.ok(odontologoService.agregaOdontologo(odontologo));
                else
                    throw new NotFoundException("dentist not found to update");
            }
            throw new BadRequestException("id required");
        }
        throw new BadRequestException("body missing");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable("id") Long odontoloId) throws NotFoundException, BadRequestException {
        if (odontoloId <= 0)
            throw new BadRequestException("id must be greater than 0");
        Optional<Odontologo> odontologoOptional = odontologoService.buscarUnOdontologo(odontoloId);
        if (odontologoOptional.isPresent()) {
            return ResponseEntity.ok(odontologoOptional.get());
        }
        throw new NotFoundException("dentist not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarOdontologo(@PathVariable Long id) throws BadRequestException {
        if (id <= 0)
            throw new BadRequestException("id must be greater than 0");
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("dentist " + id + " deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Odontologo>> buscarTodosLosOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarTodosLosOdontologos());
    }
}
