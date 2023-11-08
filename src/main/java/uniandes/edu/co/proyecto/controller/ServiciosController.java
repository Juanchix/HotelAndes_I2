package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.repositorio.ServicioRepo;

@Controller
public class ServiciosController {
    
    @Autowired
    private ServicioRepo servicioRepo;

    @GetMapping("/servicios")
    public String servicios(Model model){
        model.addAttribute("servicios", servicioRepo.darServicios());
        return "servicios";
    }

    @GetMapping("/servicios/new")
    public String servicioForm(Model model){
        model.addAttribute("servicio", new Servicio());
        return "servicioNuevo";
    }

    @PostMapping("/servicios/new/save")
    public String servicioGuardar(@ModelAttribute Servicio servicio){
        servicioRepo.insertarServicio(servicio.getHorarioinicio(), servicio.getHorariofin(), servicio.getNombre(), servicio.getCosto(), servicio.getCargado(), servicio.getExiste(), servicio.getReserva());
        return "redirect:/servicios";
    }

    @GetMapping("servicios/{id}/edit")
    public String servicioEditarForm(@PathVariable("id") int id, Model model){
        Servicio servicio = servicioRepo.darServicio(id);
        if(servicio != null){
            model.addAttribute("servicio", servicio);
            return "serviciosEditar";
        } else {
            return "redirect:/servicio";
        }
    }

    @PostMapping("/servicios/{id}/edit/save")
    public String servicioEditarGuardar(@PathVariable("id") int id, @ModelAttribute Servicio servicio){
        servicioRepo.actualizarServicio(id, servicio.getHorarioinicio(), servicio.getHorariofin(), servicio.getNombre(), servicio.getCosto(), servicio.getCargado(), servicio.getExiste(), servicio.getReserva());
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/delete")
    public String servicioEliminar(@PathVariable("id") int id){
        servicioRepo.eliminarServicio(id);
        return "redirect:/servicios";
}
}