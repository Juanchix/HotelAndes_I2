package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.repositorio.CuentaRepo;

@Controller
public class CuentasController {
    
    @Autowired
    private CuentaRepo cuentaRepo;

    @GetMapping("/cuentas")
    public String cuentas(Model model){
        model.addAttribute("cuentas", cuentaRepo.darCuentas());
        return "cuentas";
    }

    @GetMapping("/cuentas/new")
    public String cuentaForm(Model model){
        model.addAttribute("cuenta", new Cuenta());
        return "cuentaNuevo";
    }

    @PostMapping("/cuentas/new/save")
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta){
        cuentaRepo.insertarCuenta(cuenta.getNetocuenta(), cuenta.getAlojamiento());
        return "redirect:/cuentas";
    }

    @GetMapping("cuentas/{id}/edit")
    public String cuentaEditarForm(@PathVariable("id") int id, Model model){
        Cuenta cuenta = cuentaRepo.darCuenta(id);
        if(cuenta != null){
            model.addAttribute("cuenta", cuenta);
            return "cuentasEditar";
        } else {
            return "redirect:/cuenta";
        }
    }

    @PostMapping("/cuentas/{id}/edit/save")
    public String cuentaEditarGuardar(@PathVariable("id") int id, @ModelAttribute Cuenta cuenta){
        cuentaRepo.actualizarCuenta(id, cuenta.getNetocuenta(), cuenta.getAlojamiento());
        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{id}/delete")
    public String cuentaEliminar(@PathVariable("id") int id){
        cuentaRepo.eliminarCuenta(id);
        return "redirect:/cuentas";
}
}