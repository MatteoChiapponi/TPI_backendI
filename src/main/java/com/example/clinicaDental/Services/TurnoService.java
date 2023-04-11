package com.example.clinicaDental.Services;

import com.example.clinicaDental.Entitys.Odontologo;
import com.example.clinicaDental.Entitys.Paciente;
import com.example.clinicaDental.Entitys.Turno;
import com.example.clinicaDental.Exceptions.BadRequestException;
import com.example.clinicaDental.Exceptions.NotFoundException;
import com.example.clinicaDental.Respository.Dao.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepositoryImpl;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;

    public Turno asignarTurno(Turno turno) throws BadRequestException, NotFoundException {
        boolean exiteElPaciente = false;
        boolean exiteElOdontologo = false;
        if (turno.getPaciente().getId() != null) {
            Optional<Paciente> paciente = pacienteService.buscarUnPaciente(turno.getPaciente().getId());
            if (paciente.isPresent())
                exiteElPaciente = true;
            if (turno.getOdontologo().getId() != null) {
                Optional<Odontologo> odontologo = odontologoService.buscarUnOdontologo(turno.getOdontologo().getId());
                if (odontologo.isPresent())
                    exiteElOdontologo = true;
            } else
                throw new BadRequestException("dentist id required");
            if (!exiteElOdontologo && !exiteElPaciente)
                throw new NotFoundException("dentist and patient not found");
            else if (exiteElPaciente && exiteElOdontologo)
                return turnoRepositoryImpl.save(turno);

        } else
            throw new BadRequestException("patient id required");
        if (!exiteElPaciente)
            throw new NotFoundException("patient not found");
        else
            throw new NotFoundException("dentist not found");
    }

    public Optional<Turno> buscarTurno(Long id) {
        return turnoRepositoryImpl.findById(id);
    }

    public void eliminarTurno(Long id) {
        turnoRepositoryImpl.deleteById(id);
    }

    public List<Turno> buscarTodosLosOdontologos() {
        return turnoRepositoryImpl.findAll();
    }
}
