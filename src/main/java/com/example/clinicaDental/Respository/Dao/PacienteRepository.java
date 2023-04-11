package com.example.clinicaDental.Respository.Dao;

import com.example.clinicaDental.Entitys.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    @Query
    Optional<Paciente> findPacienteByEmail(String email);
}
