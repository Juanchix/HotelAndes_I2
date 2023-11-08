package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Servispa;
import uniandes.edu.co.proyecto.repositorio.ServispaRepo;

@Controller
public class ServispasController {
    
    @Autowired
    private ServispaRepo servispaRepo;

    @GetMapping("/servispas")
    public String servispas(Model model){
        model.addAttribute("servispas", servispaRepo.darServispas());
        return "servispas";
    }

    @GetMapping("/servispas/new")
    public String servispaForm(Model model){
        model.addAttribute("servispa", new Servispa());
        return "servispaNuevo";
    }

    @PostMapping("/servispas/new/save")
    public String servispaGuardar(@ModelAttribute Servispa servispa){
        servispaRepo.insertarServispa(servispa.getDuracion(), servispa.getCosto(), servispa.getFecha(), servispa.getSpa());
        return "redirect:/servispas";
    }

    @GetMapping("servispas/{id}/edit")
    public String servispaEditarForm(@PathVariable("id") int id, Model model){
        Servispa servispa = servispaRepo.darServispa(id);
        if(servispa != null){
            model.addAttribute("servispa", servispa);
            return "servispasEditar";
        } else {
            return "redirect:/servispa";
        }
    }

    @PostMapping("/servispas/{id}/edit/save")
    public String servispaEditarGuardar(@PathVariable("id") int id, @ModelAttribute Servispa servispa){
        servispaRepo.actualizarServispa(id, servispa.getDuracion(), servispa.getCosto(), servispa.getFecha(), servispa.getSpa());
        return "redirect:/servispas";
    }

    @GetMapping("/servispas/{id}/delete")
    public String servispaEliminar(@PathVariable("id") int id){
        servispaRepo.eliminarServispa(id);
        return "redirect:/servispas";
}
}