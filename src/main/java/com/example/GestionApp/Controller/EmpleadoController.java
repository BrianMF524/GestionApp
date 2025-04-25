package com.example.GestionApp.Controller;

import com.example.GestionApp.Models.Empleado;
import com.example.GestionApp.Service.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class EmpleadoController {
    @Autowired
    private EmpleadoServicio empleadoServicio;

    @GetMapping("/")
    public String inicio() {
        return "index"; // Debe coincidir con index.html
    }

    @GetMapping("/empleados")
    private String listarEmpleados(Model model){
        model.addAttribute("empleados",empleadoServicio.traerEmpleados());
        return "empleados";
    }

    @GetMapping("/empleados/{id}")
    private Optional<Empleado> traerEmpleado(@PathVariable long id){
        return empleadoServicio.buscarEmpleado(id);
    }

    @GetMapping("/empleados/nuevo")
    private String formularioAgregadoEmpleado(Model model){
        model.addAttribute("empleado",new Empleado());
        return "crear-empleado";
    }

    @PostMapping("/empleados/agregar")
    private String agregarEmpleado(@ModelAttribute("empleado") Empleado e){
        empleadoServicio.registrarEmpleado(e);
        return "redirect:/empleados";
    }
    @GetMapping("/empleados/{id}/editar")
    private String formularioEditadoEmpleado(Model model,@PathVariable long id){
        Empleado e=empleadoServicio.buscarEmpleado(id).get();
        model.addAttribute("empleado",e);
        return "editar-empleado";
    }

    @DeleteMapping("/empleados/{id}/eliminar")
    private String eliminarEmpleado(@PathVariable long id){
        empleadoServicio.borrarEmpleado(id);
        return "redirect:/empleados";
    }
    @PostMapping("/empleados/{id}/editar")
    private String editarEmpleado(@PathVariable long id,@ModelAttribute("empleado") Empleado e){
        empleadoServicio.actualizarEmpleado(e,id);
        return "redirect:/empleados";
    }
    @ExceptionHandler(Exception.class)
    public String manejarErrores(Model model,Exception ex){
        model.addAttribute("error",ex.getMessage());
        return "error";
    }

    /*@PutMapping("/empleados/{id}/editar")
    private Empleado editarEmpleado(@RequestBody Empleado e,@PathVariable long id){
        return empleadoServicio.actualizarEmpleado(e,id);
    }


    @PostMapping("/empleados/agregar")
    private Empleado agregarEmpleado(@RequestBody Empleado e){
        return empleadoServicio.registrarEmpleado(e);
    }
    @DeleteMapping("/empleados/{id}/delete")
    private void borrarEmpleado(@PathVariable long id){
        empleadoServicio.borrarEmpleado(id);
    }*/

}
