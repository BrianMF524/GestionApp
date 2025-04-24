package com.example.GestionApp.Service;

import com.example.GestionApp.Models.Empleado;
import com.example.GestionApp.Repo.EmpleadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicio {
    @Autowired
    private EmpleadoRepo empleadoRepo;
    //Metodos de empleado
    public Empleado registrarEmpleado(Empleado e){
        return empleadoRepo.save(e);
    }
    public  List<Empleado> traerEmpleados(){
        return empleadoRepo.findAll();
    }
    public  Optional<Empleado> buscarEmpleado(long id){
        return empleadoRepo.findById(id);
    }
    public  void borrarEmpleado(Long id){
        empleadoRepo.deleteById(id);
    }
    public  Empleado actualizarEmpleado(Empleado empleadoActualizado,long id){
        Optional<Empleado> aux= buscarEmpleado(id);
        if (aux.isEmpty()){
            throw new RuntimeException("Error al editar empleado");
        }
        aux.get().setNombre(empleadoActualizado.getNombre());
        aux.get().setApellido(empleadoActualizado.getApellido());
        aux.get().setEmail(empleadoActualizado.getEmail());
        aux.get().setSalario(empleadoActualizado.getSalario());
        return empleadoRepo.save(aux.get());
    }

}
