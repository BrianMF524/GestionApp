package com.example.GestionApp.Controller;

import com.example.GestionApp.Models.Empleado;
import com.example.GestionApp.Service.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoServicio empleadoServicio;

    @GetMapping("/empleados")
    private List<Empleado> listarEmpleados(){
        return empleadoServicio.traerEmpleados();
    }
    @GetMapping("/empleados/{id}")
    private Optional<Empleado> traerEmpleado(@PathVariable long id){
        return empleadoServicio.buscarEmpleado(id);
    }
    @DeleteMapping("/empleados/{id}/delete")
    private void borrarEmpleado(@PathVariable long id){
        empleadoServicio.borrarEmpleado(id);
    }
    @PostMapping("/empleados/agregar")
    private Empleado agregarEmpleado(@RequestBody Empleado e){
        return empleadoServicio.registrarEmpleado(e);
    }
    @PutMapping("/empleados/{id}/editar")
    private Empleado editarEmpleado(@RequestBody Empleado e,@PathVariable long id){
        return empleadoServicio.actualizarEmpleado(e,id);
    }

}
