package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositorio.ProductoRepo;

@Controller
public class ProductosController {
    
    @Autowired
    private ProductoRepo productoRepo;

    @GetMapping("/productos")
    public String productos(Model model){
        model.addAttribute("productos", productoRepo.darProductos());
        return "productos";
    }

    @GetMapping("/productos/new")
    public String productoForm(Model model){
        model.addAttribute("producto", new Producto());
        return "productoNuevo";
    }

    @PostMapping("/productos/new/save")
    public String productoGuardar(@ModelAttribute Producto producto){
        productoRepo.insertarProducto(producto.getNombre(), producto.getPrecio(), producto.getBar(), producto.getRestaurante(), producto.getTienda());
        return "redirect:/productos";
    }

    @GetMapping("productos/{id}/edit")
    public String productoEditarForm(@PathVariable("id") int id, Model model){
        Producto producto = productoRepo.darProducto(id);
        if(producto != null){
            model.addAttribute("producto", producto);
            return "productosEditar";
        } else {
            return "redirect:/producto";
        }
    }

    @PostMapping("/productos/{id}/edit/save")
    public String productoEditarGuardar(@PathVariable("id") int id, @ModelAttribute Producto producto){
        productoRepo.actualizarProducto(id, producto.getNombre(), producto.getPrecio(), producto.getBar(), producto.getRestaurante(), producto.getTienda());
        return "redirect:/productos";
    }

    @GetMapping("/productos/{id}/delete")
    public String productoEliminar(@PathVariable("id") int id){
        productoRepo.eliminarProducto(id);
        return "redirect:/productos";
}
}