package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Piscina;
import uniandes.edu.co.proyecto.repositorio.PiscinaRepo;

@Controller
public class PiscinasController {
    
    @Autowired
    private PiscinaRepo piscinaRepo;

    @GetMapping("/piscinas")
    public String piscinas(Model model){
        model.addAttribute("piscinas", piscinaRepo.darPiscinas());
        return "piscinas";
    }

    @GetMapping("/piscinas/new")
    public String piscinaForm(Model model){
        model.addAttribute("piscina", new Piscina());
        return "piscinaNuevo";
    }

    @PostMapping("/piscinas/new/save")
    public String piscinaGuardar(@ModelAttribute Piscina piscina){
        piscinaRepo.insertarPiscina(piscina.getCapacidad(), piscina.getProfundidad());
        return "redirect:/piscinas";
    }

    @GetMapping("piscinas/{id}/edit")
    public String piscinaEditarForm(@PathVariable("id") int id, Model model){
        Piscina piscina = piscinaRepo.darPiscina(id);
        if(piscina != null){
            model.addAttribute("piscina", piscina);
            return "piscinasEditar";
        } else {
            return "redirect:/piscina";
        }
    }

    @PostMapping("/piscinas/{id}/edit/save")
    public String piscinaEditarGuardar(@PathVariable("id") int id, @ModelAttribute Piscina piscina){
        piscinaRepo.actualizarPiscina(id, piscina.getCapacidad(), piscina.getProfundidad());
        return "redirect:/piscinas";
    }

    @GetMapping("/piscinas/{id}/delete")
    public String piscinaEliminar(@PathVariable("id") int id){
        piscinaRepo.eliminarPiscina(id);
        return "redirect:/piscinas";
}
}