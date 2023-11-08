package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Restaurante;
import uniandes.edu.co.proyecto.repositorio.RestauranteRepo;

@Controller
public class RestaurantesController {
    
    @Autowired
    private RestauranteRepo restauranteRepo;

    @GetMapping("/restaurantes")
    public String restaurantes(Model model){
        model.addAttribute("restaurantes", restauranteRepo.darRestaurantes());
        return "restaurantes";
    }

    @GetMapping("/restaurantes/new")
    public String restauranteForm(Model model){
        model.addAttribute("restaurante", new Restaurante());
        return "restauranteNuevo";
    }

    @PostMapping("/restaurantes/new/save")
    public String restauranteGuardar(@ModelAttribute Restaurante restaurante){
        restauranteRepo.insertarRestaurante(restaurante.getEstilo());
        return "redirect:/restaurantes";
    }

    @GetMapping("restaurantes/{id}/edit")
    public String restauranteEditarForm(@PathVariable("id") int id, Model model){
        Restaurante restaurante = restauranteRepo.darRestaurante(id);
        if(restaurante != null){
            model.addAttribute("restaurante", restaurante);
            return "restaurantesEditar";
        } else {
            return "redirect:/restaurante";
        }
    }

    @PostMapping("/restaurantes/{id}/edit/save")
    public String restauranteEditarGuardar(@PathVariable("id") int id, @ModelAttribute Restaurante restaurante){
        restauranteRepo.actualizarRestaurante(id, restaurante.getEstilo());
        return "redirect:/restaurantes";
    }

    @GetMapping("/restaurantes/{id}/delete")
    public String restauranteEliminar(@PathVariable("id") int id){
        restauranteRepo.eliminarRestaurante(id);
        return "redirect:/restaurantes";
}
}