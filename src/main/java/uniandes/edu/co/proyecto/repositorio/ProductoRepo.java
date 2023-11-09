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
    @Query(value = "INSERT INTO productos (nombre, precio, idrestaurante,idbar,  idtienda, idproducto) VALUES (:nombre, :precio, :idrestaurante, :idbar, :idtienda, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarProducto(@Param("nombre") String nombre, @Param("precio") Integer precio, @Param("idrestaurante") Restaurante idrestaurante, @Param("idbar") Bar idbar, 
                        @Param("idtienda") Tienda idtienda);

    // Read
    @Query(value = "SELECT * FROM prodcutos", nativeQuery = true)
    Collection<Producto> darProductos();

    @Query(value = "SELECT * FROM productos WHERE idproducto = :idproducto", nativeQuery = true)
    Producto darProducto(@Param("idproducto") int idproducto);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre=:nombre, precio=:precio, idrestaurante=:idrestaurante, idbar=:idbar, idtienda=:idtienda WHERE idproducto=:idproducto", nativeQuery = true)
    void actualizarProducto(@Param("idproducto") int idproducto, @Param("nombre") String nombre, @Param("precio") Integer precio, @Param("idrestaurante") Restaurante idrestaurante,
                            @Param("idbar") Bar idbar,@Param("idtienda") Tienda idtienda);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE idproducto = :idproducto", nativeQuery = true)
    void eliminarProducto(@Param("idproducto") int idproducto);
}
