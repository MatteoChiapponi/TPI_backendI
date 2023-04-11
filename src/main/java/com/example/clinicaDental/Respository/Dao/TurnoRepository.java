package com.example.clinicaDental.Respository.Dao;

import com.example.clinicaDental.Entitys.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {
}
