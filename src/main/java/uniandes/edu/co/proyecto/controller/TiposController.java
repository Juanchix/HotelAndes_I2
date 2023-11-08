package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Tipo;
import uniandes.edu.co.proyecto.repositorio.TipoRepo;

@Controller
public class TiposController {
    
    @Autowired
    private TipoRepo tipoRepo;

    @GetMapping("/tipos")
    public String tipos(Model model){
        model.addAttribute("tipos", tipoRepo.darTipos());
        return "tipos";
    }

    @GetMapping("/tipos/new")
    public String tipoForm(Model model){
        model.addAttribute("tipo", new Tipo());
        return "tipoNuevo";
    }

    @PostMapping("/tipos/new/save")
    public String tipoGuardar(@ModelAttribute Tipo tipo){
        tipoRepo.insertarTipo(tipo.getTipo(), tipo.getCapacidad(), tipo.getDotacion());
        return "redirect:/tipos";
    }

    @GetMapping("tipos/{id}/edit")
    public String tipoEditarForm(@PathVariable("id") int id, Model model){
        Tipo tipo = tipoRepo.darTipo(id);
        if(tipo != null){
            model.addAttribute("tipo", tipo);
            return "tiposEditar";
        } else {
            return "redirect:/tipo";
        }
    }

    @PostMapping("/tipos/{id}/edit/save")
    public String tipoEditarGuardar(@PathVariable("id") int id, @ModelAttribute Tipo tipo){
        tipoRepo.actualizarTipo(id, tipo.getTipo(), tipo.getCapacidad(), tipo.getDotacion());
        return "redirect:/tipos";
    }

    @GetMapping("/tipos/{id}/delete")
    public String tipoEliminar(@PathVariable("id") int id){
        tipoRepo.eliminarTipo(id);
        return "redirect:/tipos";
}
}