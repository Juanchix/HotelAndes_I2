package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.modelo.Tienda;

public interface TiendaRepo extends JpaRepository <Tienda, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tiendas (tipo, idservicio, idtienda) VALUES (:tipo, :idservicio, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarTienda(@Param("tipo") String tipo, @Param("idservicio") Servicio idservicio);


    // Read
    @Query(value = "SELECT * FROM tiendas", nativeQuery = true)
    Collection<Tienda> darTiendas();

    @Query(value = "SELECT * FROM tiendas WHERE idtienda = :idtienda", nativeQuery = true)
    Tienda darTienda(@Param("idtienda") int idtienda);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE tiendas SET tipo=:tipo, idservicio =:idservicio WHERE idtienda=:idtienda", nativeQuery = true)
    void actualizarTienda(@Param("idtienda") int idtienda, @Param("tipo") String tipo, @Param("idservicio") Servicio idservicio);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tiendas WHERE idtienda = :idtienda", nativeQuery = true)
    void eliminarTienda(@Param("idtienda") int idtienda);
}
