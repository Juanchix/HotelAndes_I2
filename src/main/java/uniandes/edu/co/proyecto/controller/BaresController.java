package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Bar;
import uniandes.edu.co.proyecto.repositorio.BarRepo;

@Controller
public class BaresController {
    
    @Autowired
    private BarRepo barRepo;

    @GetMapping("/bares")
    public String bares(Model model){
        model.addAttribute("bares", barRepo.darBares());
        return "bares";
    }

    @GetMapping("/bares/new")
    public String barForm(Model model){
        model.addAttribute("bar", new Bar());
        return "barNuevo";
    }

    @PostMapping("/bares/new/save")
    public String barGuardar(@ModelAttribute Bar bar){
        barRepo.insertarBar(bar.getEstilo());
        return "redirect:/bares";
    }

    @GetMapping("bares/{id}/edit")
    public String barEditarForm(@PathVariable("id") int id, Model model){
        Bar bar = barRepo.darBar(id);
        if(bar != null){
            model.addAttribute("bar", bar);
            return "baresEditar";
        } else {
            return "redirect:/bar";
        }
    }

    @PostMapping("/bares/{id}/edit/save")
    public String barEditarGuardar(@PathVariable("id") int id, @ModelAttribute Bar bar){
        barRepo.actualizarBar(id, bar.getEstilo());
        return "redirect:/bares";
    }

    @GetMapping("/bares/{id}/delete")
    public String barEliminar(@PathVariable("id") int id){
        barRepo.eliminarBar(id);
        return "redirect:/bares";
}
}