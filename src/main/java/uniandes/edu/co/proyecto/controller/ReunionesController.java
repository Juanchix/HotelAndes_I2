package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Reunion;
import uniandes.edu.co.proyecto.repositorio.ReunionRepo;

@Controller
public class ReunionesController {
    
    @Autowired
    private ReunionRepo reunionRepo;

    @GetMapping("/reuniones")
    public String reuniones(Model model){
        model.addAttribute("reuniones", reunionRepo.darReuniones());
        return "reuniones";
    }

    @GetMapping("/reuniones/new")
    public String reunionForm(Model model){
        model.addAttribute("reunion", new Reunion());
        return "reunionNuevo";
    }

    @PostMapping("/reuniones/new/save")
    public String reunionGuardar(@ModelAttribute Reunion reunion){
        reunionRepo.insertarReunion(reunion.getCapacidad(), reunion.getCostoadicional(), reunion.getFecha(), reunion.getHora(), reunion.getDuracion());
        return "redirect:/reuniones";
    }

    @GetMapping("reuniones/{id}/edit")
    public String reunionEditarForm(@PathVariable("id") int id, Model model){
        Reunion reunion = reunionRepo.darReunion(id);
        if(reunion != null){
            model.addAttribute("reunion", reunion);
            return "reunionesEditar";
        } else {
            return "redirect:/reunion";
        }
    }

    @PostMapping("/reuniones/{id}/edit/save")
    public String reunionEditarGuardar(@PathVariable("id") int id, @ModelAttribute Reunion reunion){
        reunionRepo.actualizarReunion(id, reunion.getCapacidad(), reunion.getCostoadicional(), reunion.getFecha(), reunion.getHora(), reunion.getDuracion());
        return "redirect:/reuniones";
    }

    @GetMapping("/reuniones/{id}/delete")
    public String reunionEliminar(@PathVariable("id") int id){
        reunionRepo.eliminarReunion(id);
        return "redirect:/reuniones";
} 
}
