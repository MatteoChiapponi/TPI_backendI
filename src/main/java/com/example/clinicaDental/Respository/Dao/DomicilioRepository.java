package com.example.clinicaDental.Respository.Dao;

import com.example.clinicaDental.Entitys.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio,Long> {
}
