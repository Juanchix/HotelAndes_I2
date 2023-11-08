package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Bar;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.Restaurante;
import uniandes.edu.co.proyecto.modelo.Tienda;

public interface ProductoRepo extends JpaRepository <Producto, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos (idproducto, nombre, precio, bar, restaurante, tienda) VALUES (parranderos_sequence.nextval, :nombre, :precio, :bar, :restaurante, :tienda)", nativeQuery = true)
    void insertarProducto(@Param("nombre") String nombre, @Param("precio") Integer precio, @Param("bar") Bar bar,
                            @Param("restaurante") Restaurante restaurante, @Param("tienda") Tienda tienda);


    // Read
    @Query(value = "SELECT * FROM prodcutos", nativeQuery = true)
    Collection<Producto> darProductos();

    @Query(value = "SELECT * FROM productos WHERE idproducto = :idproducto", nativeQuery = true)
    Producto darProducto(@Param("idproducto") Integer idproducto);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre=:nombre, precio=:precio, bar=:bar, restaurante=:restaurante, tienda=:tienda WHERE idproducto=:idproducto", nativeQuery = true)
    void actualizarProducto(@Param("idproducto") Integer idproducto, @Param("nombre") String nombre, @Param("precio") Integer precio, @Param("bar") Bar bar,
                            @Param("restaurante") Restaurante restaurante, @Param("tienda") Tienda tienda);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE idproducto =: idproducto", nativeQuery = true)
    void eliminarProducto(@Param("idproducto") Integer idproducto);
}
