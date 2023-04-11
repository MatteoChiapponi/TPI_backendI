package com.example.clinicaDental.Security;

import com.example.clinicaDental.Entitys.LogIn.Rol;
import com.example.clinicaDental.Services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SegurityConfig {
    BCryptPasswordEncoder passwordEncoder;
    UserService userService;

    public SegurityConfig(BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityConfig(HttpSecurity httpRequest) throws Exception {
        return httpRequest
                .csrf().disable().
                authorizeHttpRequests(auth -> {
                auth.requestMatchers("/home.html").permitAll();
                auth.requestMatchers("/views/pacientes/**","/views/odontologos/**").hasAuthority(Rol.ROLE_ADMIN.name());
                auth.requestMatchers("/views/turnos/**").hasAnyAuthority(Rol.ROLE_USER.name(),Rol.ROLE_ADMIN.name());
                auth.anyRequest().authenticated();
            })
                .formLogin().and()
                .logout().and()
                .httpBasic().and()
                .build();
    }
}
