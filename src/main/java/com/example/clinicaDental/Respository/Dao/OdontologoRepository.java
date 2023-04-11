package com.example.clinicaDental.Respository.Dao;

import com.example.clinicaDental.Entitys.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    @Query
    Optional<Odontologo> findOdontologoByMatricula(String matricula);
}
