package com.example.clinicaDental.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "PACIENTES")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apellido;
    private String nombre;
    @Column(unique = true,nullable = false)
    private String dni;
    private LocalDate fechaIngreso;
    @Column(unique = true,nullable = false)
    private String email;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id",nullable = false)
    private Domicilio domicilio;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;

    public Paciente() {
    }

    public Paciente(String apellido, String nombre, String dni, LocalDate fechaIngreso, String email, Domicilio domicilio) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.email = email;
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }
    public String getApellido() {
        return apellido;
    }
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDni() {
        return dni;
    }
    public Domicilio getDomicilio() {
        return domicilio;
    }
    public String getEmail() {
        return email;
    }
    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
