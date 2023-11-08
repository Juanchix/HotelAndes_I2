package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Plan;
import uniandes.edu.co.proyecto.repositorio.PlanRepo;

@Controller
public class PlanesController {
    
    @Autowired
    private PlanRepo planRepo;

    @GetMapping("/planes")
    public String planes(Model model){
        model.addAttribute("planes", planRepo.darPlanes());
        return "planes";
    }

    @GetMapping("/planes/new")
    public String planForm(Model model){
        model.addAttribute("plan", new Plan());
        return "planNuevo";
    }

    @PostMapping("/planes/new/save")
    public String planGuardar(@ModelAttribute Plan plan){
        planRepo.insertarPlan(plan.getTipoplan(), plan.getDescuento());
        return "redirect:/planes";
    }

    @GetMapping("planes/{id}/edit")
    public String planEditarForm(@PathVariable("id") int id, Model model){
        Plan plan = planRepo.darPlan(id);
        if(plan != null){
            model.addAttribute("plan", plan);
            return "planesEditar";
        } else {
            return "redirect:/plan";
        }
    }

    @PostMapping("/planes/{id}/edit/save")
    public String planEditarGuardar(@PathVariable("id") int id, @ModelAttribute Plan plan){
        planRepo.actualizarPlan(id, plan.getTipoplan(), plan.getDescuento());
        return "redirect:/planes";
    }

    @GetMapping("/planes/{id}/delete")
    public String planEliminar(@PathVariable("id") int id){
        planRepo.eliminarPlan(id);
        return "redirect:/planes";
}
}