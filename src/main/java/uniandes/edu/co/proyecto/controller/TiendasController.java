package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Tienda;
import uniandes.edu.co.proyecto.repositorio.TiendaRepo;

@Controller
public class TiendasController {
    
    @Autowired
    private TiendaRepo tiendaRepo;

    @GetMapping("/tiendas")
    public String tiendas(Model model){
        model.addAttribute("tiendas", tiendaRepo.darTiendas());
        return "tiendas";
    }

    @GetMapping("/tiendas/new")
    public String tiendaForm(Model model){
        model.addAttribute("tienda", new Tienda());
        return "tiendaNuevo";
    }

    @PostMapping("/tiendas/new/save")
    public String tiendaGuardar(@ModelAttribute Tienda tienda){
        tiendaRepo.insertarTienda(tienda.getTipo());
        return "redirect:/tiendas";
    }

    @GetMapping("tiendas/{id}/edit")
    public String tiendaEditarForm(@PathVariable("id") int id, Model model){
        Tienda tienda = tiendaRepo.darTienda(id);
        if(tienda != null){
            model.addAttribute("tienda", tienda);
            return "tiendasEditar";
        } else {
            return "redirect:/tienda";
        }
    }

    @PostMapping("/tiendas/{id}/edit/save")
    public String tiendaEditarGuardar(@PathVariable("id") int id, @ModelAttribute Tienda tienda){
        tiendaRepo.actualizarTienda(id, tienda.getTipo());
        return "redirect:/tiendas";
    }

    @GetMapping("/tiendas/{id}/delete")
    public String tiendaEliminar(@PathVariable("id") int id){
        tiendaRepo.eliminarTienda(id);
        return "redirect:/tiendas";
}
}