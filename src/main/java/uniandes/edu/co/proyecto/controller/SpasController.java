package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Spa;
import uniandes.edu.co.proyecto.repositorio.SpaRepo;

@Controller
public class SpasController {
    
    @Autowired
    private SpaRepo spaRepo;

    @GetMapping("/spas")
    public String spas(Model model){
        model.addAttribute("spas", spaRepo.darSpas());
        return "spas";
    }

    @GetMapping("/spas/new")
    public String spaForm(Model model){
        model.addAttribute("spa", new Spa());
        return "spaNuevo";
    }

    @PostMapping("/spas/new/save")
    public String spaGuardar(@ModelAttribute Spa spa){
        spaRepo.insertarSpa(spa.getCapacidad());
        return "redirect:/spas";
    }

    @GetMapping("spas/{id}/edit")
    public String spaEditarForm(@PathVariable("id") int id, Model model){
        Spa spa = spaRepo.darSpa(id);
        if(spa != null){
            model.addAttribute("spa", spa);
            return "spasEditar";
        } else {
            return "redirect:/spa";
        }
    }

    @PostMapping("/spas/{id}/edit/save")
    public String spaEditarGuardar(@PathVariable("id") int id, @ModelAttribute Spa spa){
        spaRepo.actualizarSpa(id, spa.getCapacidad());
        return "redirect:/spas";
    }

    @GetMapping("/spas/{id}/delete")
    public String spaEliminar(@PathVariable("id") int id){
        spaRepo.eliminarSpa(id);
        return "redirect:/spas";
}
}