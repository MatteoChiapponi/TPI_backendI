package com.example.clinicaDental.Services;

import com.example.clinicaDental.Entitys.Domicilio;
import com.example.clinicaDental.Entitys.Paciente;
import com.example.clinicaDental.Exceptions.BadRequestException;
import com.example.clinicaDental.Respository.Dao.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepositoryImpl;
    @Autowired
    DomicilioService domicilioService;

    /**
     * necesita los datos del paciente y domicilio para guardarlos en la bd
     *
     * @param paciente
     * @return el paciente y el domicilio con el id generado por la base de datos.
     */
    public Paciente agregarPaciente(Paciente paciente) {
        return pacienteRepositoryImpl.save(paciente);
    }

    /**
     * necesita el id del paciente y del domicilio para eliminarlos de la bd
     *
     * @param id
     */
    public void eliminarPaciente(Long id) {
        pacienteRepositoryImpl.deleteById(id);
    }

    /**
     * @param id
     * @return el paciente encontrado en bd con ese id o null en caso que no lo encuentre
     */
    public Optional<Paciente> buscarUnPaciente(Long id) {
        return pacienteRepositoryImpl.findById(id);
    }

    public List<Paciente> buscarTodosLosPacientes() {
        return pacienteRepositoryImpl.findAll();
    }

    public Optional<Paciente> buscarPacientePorEmail(String email) {
        return pacienteRepositoryImpl.findPacienteByEmail(email);
    }

    public Paciente actualizarUnPaciente(Paciente paciente) throws BadRequestException {
        Optional<Domicilio> domicilioOptional = domicilioService.buscarUnDomicilio(paciente.getDomicilio().getId());
        if (domicilioOptional.isPresent())
            return agregarPaciente(paciente);
        throw new BadRequestException("address doesn't exists");
    }
}
