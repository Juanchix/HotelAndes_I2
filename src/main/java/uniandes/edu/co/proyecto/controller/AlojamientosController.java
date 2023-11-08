package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Alojamiento;
import uniandes.edu.co.proyecto.repositorio.AlojamientoRepo;

@Controller
public class AlojamientosController {
    
    @Autowired
    private AlojamientoRepo alojamientoRepo;

    @GetMapping("/alojamientos")
    public String alojamientos(Model model){
        model.addAttribute("alojamientos", alojamientoRepo.darAlojamientos());
        return "alojamientos";
    }

    @GetMapping("/alojamientos/new")
    public String alojamientoForm(Model model){
        model.addAttribute("alojamiento", new Alojamiento());
        return "alojamientoNuevo";
    }

    @PostMapping("/alojamientos/new/save")
    public String alojamientoGuardar(@ModelAttribute Alojamiento alojamiento){
        alojamientoRepo.insertarAlojamiento(alojamiento.getActiva(), alojamiento.getCheckin(), alojamiento.getCheckout(), alojamiento.getAcompanantes(), alojamiento.getUsuario(), alojamiento.getPlan(), alojamiento.getCuenta(), alojamiento.getHabitacion());
        return "redirect:/alojamientos";
    }

    @GetMapping("alojamientos/{id}/edit")
    public String alojamientoEditarForm(@PathVariable("id") int id, Model model){
        Alojamiento alojamiento = alojamientoRepo.darAlojamiento(id);
        if(alojamiento != null){
            model.addAttribute("alojamiento", alojamiento);
            return "alojamientosEditar";
        } else {
            return "redirect:/alojamiento";
        }
    }

    @PostMapping("/alojamientos/{id}/edit/save")
    public String alojamientoEditarGuardar(@PathVariable("id") int id, @ModelAttribute Alojamiento alojamiento){
        alojamientoRepo.actualizarAlojamiento(id, alojamiento.getActiva(), alojamiento.getCheckin(), alojamiento.getCheckout(), alojamiento.getAcompanantes(), alojamiento.getUsuario(), alojamiento.getPlan(), alojamiento.getCuenta(), alojamiento.getHabitacion());
        return "redirect:/alojamientos";
    }

    @GetMapping("/alojamientos/{id}/delete")
    public String alojamientoEliminar(@PathVariable("id") int id){
        alojamientoRepo.eliminarAlojamiento(id);
        return "redirect:/alojamientos";
}
}