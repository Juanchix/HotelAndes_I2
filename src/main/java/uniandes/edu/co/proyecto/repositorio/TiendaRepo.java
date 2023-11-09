package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Tienda;

public interface TiendaRepo extends JpaRepository <Tienda, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tiendas (tipo, idservicio) VALUES (:tipo, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarTienda(@Param("tipo") String tipo);


    // Read
    @Query(value = "SELECT * FROM tiendas", nativeQuery = true)
    Collection<Tienda> darTiendas();

    @Query(value = "SELECT * FROM tiendas WHERE idservicio = :idservicio", nativeQuery = true)
    Tienda darTienda(@Param("idservicio") int idservicio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE tiendas SET tipo=:tipo WHERE idservicio=:idservicio", nativeQuery = true)
    void actualizarTienda(@Param("idservicio") int idservicio, @Param("tipo") String tipo);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tiendas WHERE idservicio =: idservicio", nativeQuery = true)
    void eliminarTienda(@Param("idservicio") int idservicio);
}
