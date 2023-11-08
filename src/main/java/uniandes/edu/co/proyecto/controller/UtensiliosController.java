package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Utensilio;
import uniandes.edu.co.proyecto.repositorio.UtensilioRepo;

@Controller
public class UtensiliosController {
    
    @Autowired
    private UtensilioRepo utensilioRepo;

    @GetMapping("/utensilios")
    public String utensilios(Model model){
        model.addAttribute("utensilios", utensilioRepo.darUtensilios());
        return "utensilios";
    }

    @GetMapping("/utensilios/new")
    public String utensilioForm(Model model){
        model.addAttribute("utensilio", new Utensilio());
        return "utensilioNuevo";
    }

    @PostMapping("/utensilios/new/save")
    public String utensilioGuardar(@ModelAttribute Utensilio utensilio){
        utensilioRepo.insertarUtensilio(utensilio.getDevuelto(), utensilio.getEstado());
        return "redirect:/utensilios";
    }

    @GetMapping("utensilios/{id}/edit")
    public String utensilioEditarForm(@PathVariable("id") int id, Model model){
        Utensilio utensilio = utensilioRepo.darUtensilio(id);
        if(utensilio != null){
            model.addAttribute("utensilio", utensilio);
            return "utensiliosEditar";
        } else {
            return "redirect:/utensilio";
        }
    }

    @PostMapping("/utensilios/{id}/edit/save")
    public String utensilioEditarGuardar(@PathVariable("id") int id, @ModelAttribute Utensilio utensilio){
        utensilioRepo.actualizarUtensilio(id, utensilio.getDevuelto(), utensilio.getEstado());
        return "redirect:/utensilios";
    }

    @GetMapping("/utensilios/{id}/delete")
    public String utensilioEliminar(@PathVariable("id") int id){
        utensilioRepo.eliminarUtensilio(id);
        return "redirect:/utensilios";
}
}