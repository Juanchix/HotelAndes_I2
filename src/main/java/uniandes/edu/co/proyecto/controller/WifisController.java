package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Wifi;
import uniandes.edu.co.proyecto.repositorio.WifiRepo;

@Controller
public class WifisController {
    
    @Autowired
    private WifiRepo wifiRepo;

    @GetMapping("/wifis")
    public String wifis(Model model){
        model.addAttribute("wifis", wifiRepo.darWifis());
        return "wifis";
    }

    @GetMapping("/wifis/new")
    public String wifiForm(Model model){
        model.addAttribute("wifi", new Wifi());
        return "wifiNuevo";
    }

    @PostMapping("/wifis/new/save")
    public String wifiGuardar(@ModelAttribute Wifi wifi){
        wifiRepo.insertarWifi(wifi.getAnchobanda());
        return "redirect:/wifis";
    }

    @GetMapping("wifis/{id}/edit")
    public String wifiEditarForm(@PathVariable("id") int id, Model model){
        Wifi wifi = wifiRepo.darWifi(id);
        if(wifi != null){
            model.addAttribute("wifi", wifi);
            return "wifisEditar";
        } else {
            return "redirect:/wifi";
        }
    }

    @PostMapping("/wifis/{id}/edit/save")
    public String wifiEditarGuardar(@PathVariable("id") int id, @ModelAttribute Wifi wifi){
        wifiRepo.actualizarWifi(id, wifi.getAnchobanda());
        return "redirect:/wifis";
    }

    @GetMapping("/wifis/{id}/delete")
    public String wifiEliminar(@PathVariable("id") int id){
        wifiRepo.eliminarWifi(id);
        return "redirect:/wifis";
}
}