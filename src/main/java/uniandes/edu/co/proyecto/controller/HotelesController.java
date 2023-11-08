package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Hotel;
import uniandes.edu.co.proyecto.repositorio.HotelRepo;

@Controller
public class HotelesController {
    
    @Autowired
    private HotelRepo hotelRepo;

    @GetMapping("/hoteles")
    public String hoteles(Model model){
        model.addAttribute("hoteles", hotelRepo.darHoteles());
        return "hoteles";
    }

    @GetMapping("/hoteles/new")
    public String hotelForm(Model model){
        model.addAttribute("hotel", new Hotel());
        return "hotelNuevo";
    }

    @PostMapping("/hoteles/new/save")
    public String hotelGuardar(@ModelAttribute Hotel hotel){
        hotelRepo.insertarHotel(hotel.getNombrehotel(), hotel.getNithotel());
        return "redirect:/hoteles";
    }

    @GetMapping("hoteles/{id}/edit")
    public String hotelEditarForm(@PathVariable("id") int id, Model model){
        Hotel hotel = hotelRepo.darHotel(id);
        if(hotel != null){
            model.addAttribute("hotel", hotel);
            return "hotelesEditar";
        } else {
            return "redirect:/hotel";
        }
    }

    @PostMapping("/hoteles/{id}/edit/save")
    public String hotelEditarGuardar(@PathVariable("id") int id, @ModelAttribute Hotel hotel){
        hotelRepo.actualizarHotel(id, hotel.getNombrehotel(), hotel.getNithotel());
        return "redirect:/hoteles";
    }

    @GetMapping("/hoteles/{id}/delete")
    public String hotelEliminar(@PathVariable("id") int id){
        hotelRepo.eliminarHotel(id);
        return "redirect:/hoteles";
}
}