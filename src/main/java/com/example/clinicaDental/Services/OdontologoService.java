package com.example.clinicaDental.Services;

import com.example.clinicaDental.Entitys.Odontologo;
import com.example.clinicaDental.Respository.Dao.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    OdontologoRepository odontologoRepositoryImpl;


    public Odontologo agregaOdontologo(Odontologo odontologo){
        return odontologoRepositoryImpl.save(odontologo);
    }
    public void eliminarOdontologo(Long id){
        odontologoRepositoryImpl.deleteById(id);
    }
    public Odontologo actualizarOdontologo(Odontologo odontologo){
        if (buscarUnOdontologo(odontologo.getId()).isPresent()){
            return odontologoRepositoryImpl.save(odontologo);
        }
        return null;
    }
    public Optional<Odontologo> buscarUnOdontologo(Long id){
        return odontologoRepositoryImpl.findById(id);
    }
    public List<Odontologo> buscarTodosLosOdontologos(){
        return odontologoRepositoryImpl.findAll();
    }
    public Optional<Odontologo> buscarOdontologoPorMatricula(String matricla){
        return odontologoRepositoryImpl.findOdontologoByMatricula(matricla);
    }

}
