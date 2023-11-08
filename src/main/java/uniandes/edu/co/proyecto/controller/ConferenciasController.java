package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Conferencia;
import uniandes.edu.co.proyecto.repositorio.ConferenciaRepo;

@Controller
public class ConferenciasController {
    
    @Autowired
    private ConferenciaRepo conferenciaRepo;

    @GetMapping("/conferencias")
    public String conferencias(Model model){
        model.addAttribute("conferencias", conferenciaRepo.darConferencias());
        return "conferencias";
    }

    @GetMapping("/conferencias/new")
    public String conferenciaForm(Model model){
        model.addAttribute("conferencia", new Conferencia());
        return "conferenciaNuevo";
    }

    @PostMapping("/conferencias/new/save")
    public String conferenciaGuardar(@ModelAttribute Conferencia conferencia){
        conferenciaRepo.insertarConferencia(conferencia.getCapacidad(), conferencia.getFecha(), conferencia.getHora(), conferencia.getDuracion());
        return "redirect:/conferencias";
    }

    @GetMapping("conferencias/{id}/edit")
    public String conferenciaEditarForm(@PathVariable("id") int id, Model model){
        Conferencia conferencia = conferenciaRepo.darConferencia(id);
        if(conferencia != null){
            model.addAttribute("conferencia", conferencia);
            return "conferenciasEditar";
        } else {
            return "redirect:/conferencia";
        }
    }

    @PostMapping("/conferencias/{id}/edit/save")
    public String conferenciaEditarGuardar(@PathVariable("id") int id, @ModelAttribute Conferencia conferencia){
        conferenciaRepo.actualizarConferencia(id, conferencia.getCapacidad(), conferencia.getFecha(), conferencia.getHora(), conferencia.getDuracion());
        return "redirect:/conferencias";
    }

    @GetMapping("/conferencias/{id}/delete")
    public String conferenciaEliminar(@PathVariable("id") int id){
        conferenciaRepo.eliminarConferencia(id);
        return "redirect:/conferencias";
}
}