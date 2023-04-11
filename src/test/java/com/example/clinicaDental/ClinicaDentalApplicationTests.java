package com.example.clinicaDental;

import com.example.clinicaDental.Entitys.Domicilio;
import com.example.clinicaDental.Entitys.Odontologo;
import com.example.clinicaDental.Entitys.Paciente;
import com.example.clinicaDental.Services.DomicilioService;
import com.example.clinicaDental.Services.OdontologoService;
import com.example.clinicaDental.Services.PacienteService;
import com.example.clinicaDental.Services.TurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;


@SpringBootTest
class ClinicaDentalApplicationTests {
	@Autowired
	DomicilioService domicilioService;
	@Autowired
	OdontologoService odontologoService;
	@Autowired
	PacienteService pacienteService;
	@Autowired
	TurnoService turnoService;

	public void testAgregarDomicilio(){
		Domicilio domicilio1 = new Domicilio("calle 123","33","san miguel de tucuman","tucuman");
		Long idDomicilio = domicilioService.agregaDomicilio(domicilio1).getId();
		Assertions.assertEquals(2l,idDomicilio);
	}
	public void testAgregarPaciente(){
		Domicilio domicilio2 = new Domicilio("calle 43","22","san miguel de tucuman","tucuman");
		Paciente paciente1 = new Paciente("leandro","garcia","4234344", LocalDate.of(2020,05,23),"garcia@gmail.com",domicilio2);
		Long idPaciente = pacienteService.agregarPaciente(paciente1).getId();
		Assertions.assertEquals(2l,idPaciente);
	}

	public void testAgregarOdontologo(){
		Odontologo odontologo1 = new Odontologo("11111","raul","gimenez", "raul@gmail.com");
		odontologoService.agregaOdontologo(odontologo1);
		Long idOdontologo = odontologoService.agregaOdontologo(odontologo1).getId();
		Assertions.assertEquals(3l,idOdontologo);
	}
	public void testBuscarUnOdontologo(){
		Optional<Odontologo> odontologoOptional =odontologoService.buscarUnOdontologo(3l);
		Assertions.assertTrue(odontologoOptional.isPresent());
	}
	public void testActualizarodontologo(){
		Odontologo newOdontologo = new Odontologo(3l,"77777","raul","gimenez","raul@gmail.com");
		String newMatricula = odontologoService.actualizarOdontologo(newOdontologo).getMatricula();
		Assertions.assertEquals("77777",newMatricula);
	}
	public void testEliminarOdontologo(){
		odontologoService.eliminarOdontologo(3l);
		Assertions.assertEquals(2,odontologoService.buscarTodosLosOdontologos().size());
	}
	@Test
	void contextLoads() {
		testAgregarDomicilio();
		testAgregarPaciente();
		testAgregarOdontologo();
		testBuscarUnOdontologo();
		testActualizarodontologo();
		testEliminarOdontologo();
	}
}
