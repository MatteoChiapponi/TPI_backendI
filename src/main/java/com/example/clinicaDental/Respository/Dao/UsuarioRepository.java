package com.example.clinicaDental.Respository.Dao;

import com.example.clinicaDental.Entitys.LogIn.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findUserByCorreo (String correo);
}
