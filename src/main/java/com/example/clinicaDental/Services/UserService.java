package com.example.clinicaDental.Services;

import com.example.clinicaDental.Entitys.LogIn.Usuario;
import com.example.clinicaDental.Respository.Dao.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    UsuarioRepository usuarioRepository;

    public UserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional =usuarioRepository.findUserByCorreo(username);
        if (usuarioOptional.isPresent())
            return usuarioOptional.get();
        throw new UsernameNotFoundException("user not found");
    }
}
