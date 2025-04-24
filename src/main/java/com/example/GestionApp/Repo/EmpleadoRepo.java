package com.example.GestionApp.Repo;

import com.example.GestionApp.Models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepo extends JpaRepository<Empleado,Long> {

}
