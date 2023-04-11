package com.example.clinicaDental.Security;

import com.example.clinicaDental.Entitys.Domicilio;
import com.example.clinicaDental.Entitys.LogIn.Rol;
import com.example.clinicaDental.Entitys.LogIn.Usuario;
import com.example.clinicaDental.Entitys.Odontologo;
import com.example.clinicaDental.Entitys.Paciente;
import com.example.clinicaDental.Respository.Dao.OdontologoRepository;
import com.example.clinicaDental.Respository.Dao.PacienteRepository;
import com.example.clinicaDental.Respository.Dao.UsuarioRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataUsuarioLoader implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;
    private OdontologoRepository odontologoRepository;
    private PacienteRepository pacienteRepository;

    public DataUsuarioLoader(UsuarioRepository usuarioRepository, OdontologoRepository odontologoRepository, PacienteRepository pacienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Domicilio domicilio = new Domicilio("calle 2","22","san miguel de tucuman","tucuman");
        Paciente paciente = new Paciente("matteo","chiappni","45184390", LocalDate.of(2020,05,23),"mateo@gmail.com",domicilio);

        pacienteRepository.save(paciente);


        Odontologo odontologo1 = new Odontologo("sdfkjo1","leandro","aguirre", "leandro@gmail.com");
        Odontologo odontologo2 = new Odontologo("44334","lisandro","ramirez","lisandro@gmail.com");

        odontologoRepository.save(odontologo1);
        odontologoRepository.save(odontologo2);



        BCryptPasswordEncoder bcEncoder = new BCryptPasswordEncoder();

        String passwordUSER = "user";
        String passwordEncodedUSER = bcEncoder.encode(passwordUSER);
        Usuario usuarioUSER = new Usuario("user@gmail.com", passwordEncodedUSER, Rol.ROLE_USER);

        String passwordADMIN = "admin";
        String passwordEncodedADMIN = bcEncoder.encode(passwordADMIN);
        Usuario usuarioADMIN = new Usuario("admin@gmail.com", passwordEncodedADMIN, Rol.ROLE_ADMIN);

        usuarioRepository.save(usuarioUSER);
        usuarioRepository.save(usuarioADMIN);

    }

}
