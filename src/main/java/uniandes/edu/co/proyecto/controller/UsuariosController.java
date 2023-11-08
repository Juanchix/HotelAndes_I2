package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepo;

@Controller
public class UsuariosController {
    
    @Autowired
    private UsuarioRepo usuarioRepo;

    @GetMapping("/usuarios")
    public String usuarios(Model model){
        model.addAttribute("usuarios", usuarioRepo.darUsuarios());
        return "usuarios";
    }

    @GetMapping("/usuarios/new")
    public String usuarioForm(Model model){
        model.addAttribute("usuario", new Usuario());
        return "usuarioNuevo";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario){
        usuarioRepo.insertarUsuario(usuario.getNombreuser(), usuario.getTipodocuser(), usuario.getNumdocuser(), usuario.getCorreouser());
        return "redirect:/usuarios";
    }

    @GetMapping("usuarios/{id}/edit")
    public String usuarioEditarForm(@PathVariable("id") int id, Model model){
        Usuario usuario = usuarioRepo.darUsuario(id);
        if(usuario != null){
            model.addAttribute("usuario", usuario);
            return "usuariosEditar";
        } else {
            return "redirect:/usuario";
        }
    }

    @PostMapping("/usuarios/{id}/edit/save")
    public String usuarioEditarGuardar(@PathVariable("id") int id, @ModelAttribute Usuario usuario){
        usuarioRepo.actualizarUsuario(id, usuario.getNombreuser(), usuario.getTipodocuser(), usuario.getNumdocuser(), usuario.getCorreouser());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/delete")
    public String usuarioEliminar(@PathVariable("id") int id){
        usuarioRepo.eliminarUsuario(id);
        return "redirect:/usuarios";
}
}