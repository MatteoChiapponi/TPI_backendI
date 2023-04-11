package com.example.clinicaDental.Services;

import com.example.clinicaDental.Entitys.Domicilio;
import com.example.clinicaDental.Respository.Dao.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {

    @Autowired
    DomicilioRepository domicilioRepositoryImpl;

    public Domicilio agregaDomicilio(Domicilio domicilio){
        return domicilioRepositoryImpl.save(domicilio);
    }
    public void eliminarDomicilio(Domicilio domicilio){
        domicilioRepositoryImpl.delete(domicilio);
    }
    public Domicilio actualizarDomicilio(Domicilio domicilio){
        return domicilioRepositoryImpl.save(domicilio);
    }
    public Optional<Domicilio> buscarUnDomicilio(Long id){
        return domicilioRepositoryImpl.findById(id);
    }
    public List<Domicilio> buscarTodosLosDomicilios(){
        return domicilioRepositoryImpl.findAll();
    }

}
