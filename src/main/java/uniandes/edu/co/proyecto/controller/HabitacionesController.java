package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Habitacion;
import uniandes.edu.co.proyecto.repositorio.HabitacionRepo;

@Controller
public class HabitacionesController {
    
    @Autowired
    private HabitacionRepo habitacionRepo;

    @GetMapping("/habitaciones")
    public String habitaciones(Model model){
        model.addAttribute("habitaciones", habitacionRepo.darHabitaciones());
        return "habitaciones";
    }

    @GetMapping("/habitaciones/new")
    public String habitacionForm(Model model){
        model.addAttribute("habitacion", new Habitacion());
        return "habitacionNuevo";
    }

    @PostMapping("/habitaciones/new/save")
    public String habitacionGuardar(@ModelAttribute Habitacion habitacion){
        habitacionRepo.insertarHabitacion(habitacion.getNumhabitacion(), habitacion.getDisponible(), habitacion.getPrecionoche(), habitacion.getHotel(), habitacion.getTipo(), habitacion.getAlojamiento());
        return "redirect:/habitaciones";
    }

    @GetMapping("habitaciones/{id}/edit")
    public String habitacionEditarForm(@PathVariable("id") int id, Model model){
        Habitacion habitacion = habitacionRepo.darHabitacion(id);
        if(habitacion != null){
            model.addAttribute("habitacion", habitacion);
            return "habitacionesEditar";
        } else {
            return "redirect:/habitacion";
        }
    }

    @PostMapping("/habitaciones/{id}/edit/save")
    public String habitacionEditarGuardar(@PathVariable("id") int id, @ModelAttribute Habitacion habitacion){
        habitacionRepo.actualizarHabitacion(id, habitacion.getNumhabitacion(), habitacion.getDisponible(), habitacion.getPrecionoche(), habitacion.getHotel(), habitacion.getTipo(), habitacion.getAlojamiento());
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id}/delete")
    public String habitacionEliminar(@PathVariable("id") int id){
        habitacionRepo.eliminarHabitacion(id);
        return "redirect:/habitaciones";
}
}