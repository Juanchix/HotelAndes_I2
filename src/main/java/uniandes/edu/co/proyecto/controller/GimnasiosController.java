package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Gimnasio;
import uniandes.edu.co.proyecto.repositorio.GimnasioRepo;

@Controller
public class GimnasiosController {
    
    @Autowired
    private GimnasioRepo gimnasioRepo;

    @GetMapping("/gimnasios")
    public String gimnasios(Model model){
        model.addAttribute("gimnasios", gimnasioRepo.darGimnasios());
        return "gimnasios";
    }

    @GetMapping("/gimnasios/new")
    public String gimnasioForm(Model model){
        model.addAttribute("gimnasio", new Gimnasio());
        return "gimnasioNuevo";
    }

    @PostMapping("/gimnasios/new/save")
    public String gimnasioGuardar(@ModelAttribute Gimnasio gimnasio){
        gimnasioRepo.insertarGimnasio(gimnasio.getCapacidad(), gimnasio.getMaquinas());
        return "redirect:/gimnasios";
    }

    @GetMapping("gimnasios/{id}/edit")
    public String gimnasioEditarForm(@PathVariable("id") int id, Model model){
        Gimnasio gimnasio = gimnasioRepo.darGimnasio(id);
        if(gimnasio != null){
            model.addAttribute("gimnasio", gimnasio);
            return "gimnasiosEditar";
        } else {
            return "redirect:/gimnasio";
        }
    }

    @PostMapping("/gimnasios/{id}/edit/save")
    public String gimnasioEditarGuardar(@PathVariable("id") int id, @ModelAttribute Gimnasio gimnasio){
        gimnasioRepo.actualizarGimnasio(id, gimnasio.getCapacidad(), gimnasio.getMaquinas());
        return "redirect:/gimnasios";
    }

    @GetMapping("/gimnasios/{id}/delete")
    public String gimnasioEliminar(@PathVariable("id") int id){
        gimnasioRepo.eliminarGimnasio(id);
        return "redirect:/gimnasios";
}
}