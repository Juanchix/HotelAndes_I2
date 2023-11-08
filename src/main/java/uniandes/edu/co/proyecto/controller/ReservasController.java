package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Reserva;
import uniandes.edu.co.proyecto.repositorio.ReservaRepo;

@Controller
public class ReservasController {
    
    @Autowired
    private ReservaRepo reservaRepo;

    @GetMapping("/reservas")
    public String reservas(Model model){
        model.addAttribute("reservas", reservaRepo.darReservas());
        return "reservas";
    }

    @GetMapping("/reservas/new")
    public String reservaForm(Model model){
        model.addAttribute("reserva", new Reserva());
        return "reservaNuevo";
    }

    @PostMapping("/reservas/new/save")
    public String reservaGuardar(@ModelAttribute Reserva reserva){
        reservaRepo.insertarReserva(reserva.getHorareserva(), reserva.getCuenta());
        return "redirect:/reservas";
    }

    @GetMapping("reservas/{id}/edit")
    public String reservaEditarForm(@PathVariable("id") int id, Model model){
        Reserva reserva = reservaRepo.darReserva(id);
        if(reserva != null){
            model.addAttribute("reserva", reserva);
            return "reservasEditar";
        } else {
            return "redirect:/reserva";
        }
    }

    @PostMapping("/reservas/{id}/edit/save")
    public String reservaEditarGuardar(@PathVariable("id") int id, @ModelAttribute Reserva reserva){
        reservaRepo.actualizarReserva(id, reserva.getHorareserva(), reserva.getCuenta());
        return "redirect:/reservas";
    }

    @GetMapping("/reservas/{id}/delete")
    public String reservaEliminar(@PathVariable("id") int id){
        reservaRepo.eliminarReserva(id);
        return "redirect:/reservas";
}
}