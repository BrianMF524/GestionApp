package com.example.GestionApp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre,apellido,puesto;
    @Email
    @NotNull
    private String email;
    private long salario;

}
