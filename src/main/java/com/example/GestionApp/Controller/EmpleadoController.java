package com.example.GestionApp.Controller;

import com.example.GestionApp.Models.Empleado;
import com.example.GestionApp.Repo.EmpleadoRepo;
import com.example.GestionApp.Service.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class EmpleadoController {
    @Autowired
    private EmpleadoServicio empleadoServicio;
    @Autowired
    private EmpleadoRepo empleadoRepo;

    @GetMapping("/")
    public String inicio() {
        return "empleados";
    }
    @GetMapping("/empleados")
    private String listarEmpleados(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size, Model model){
        Pageable pageable= PageRequest.of(page,size, Sort.by("nombre").ascending());
        Page<Empleado> empleadoPage=empleadoRepo.findAll(pageable);
        model.addAttribute("empleadosPage",empleadoPage);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",empleadoPage.getTotalPages());
        model.addAttribute("totalItems",empleadoPage.getTotalElements());
        return "empleados";
    }

    @GetMapping("/empleados/{id}")
    private String traerEmpleado(@PathVariable long id, Model model){
        //return empleadoServicio.buscarEmpleado(id);
        model.addAttribute(empleadoServicio.buscarEmpleado(id).get());
        return "detalle-empleado";
    }

    @GetMapping("/empleados/nuevo")
    private String formularioAgregadoEmpleado(Model model){
        model.addAttribute("empleado",new Empleado());
        return "crear-empleado";
    }

    @PostMapping("/empleados/agregar")
    private String agregarEmpleado(@ModelAttribute("empleado") Empleado e){
        System.out.println(e.getApellido());
        empleadoServicio.registrarEmpleado(e);
        return "redirect:/empleados";
    }
    @GetMapping("/empleados/{id}/editar")
    private String formularioEditadoEmpleado(Model model,@PathVariable long id){
        Empleado e=empleadoServicio.buscarEmpleado(id).get();
        model.addAttribute("empleado",e);
        return "editar-empleado";
    }

    @GetMapping("/empleados/{id}/eliminar")
    private String formularioEliminadoEmpleado(@PathVariable long id,Model model){
        model.addAttribute("empleado",empleadoServicio.buscarEmpleado(id).get());
        return "eliminar-empleado";
    }
    @PostMapping("/empleados/{id}/eliminar/confirmar")
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

}
