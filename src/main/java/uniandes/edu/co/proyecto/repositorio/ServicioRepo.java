package uniandes.edu.co.proyecto.repositorio;

import java.sql.Time;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Reserva;
import uniandes.edu.co.proyecto.modelo.Servicio;

public interface ServicioRepo extends JpaRepository <Servicio, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicios (idservicio, horarioinicio, horariofin, nombre, costo, cargado, existe, reserva) VALUES (parranderos_sequence.nextval, :horarioinicio, :horariofin, :nombre, :costo, :cargado, :existe, :reserva)", nativeQuery = true)
    void insertarServicio(@Param("horarioinicio") Time horarioinicio, @Param("horariofin") Time horariofin, @Param("nombre") String nombre, 
                            @Param("costo") Integer costo, @Param("cargado") String cargado, @Param("existe") String existe, @Param("reserva") Reserva reserva);


    // Read
    @Query(value = "SELECT * FROM servicios", nativeQuery = true)
    Collection<Servicio> darServicios();

    @Query(value = "SELECT * FROM servicios WHERE idservicio = :idservicio", nativeQuery = true)
    Servicio darServicio(@Param("idservicio") Integer idservicio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE servicios SET horarioinicio=:horarioinicio, horariofin=:horariofin, nombre=:nombre, costo=:costo, cargado=:cargado, existe=:existe, reserva=:reserva WHERE idservicio=:idservicio", nativeQuery = true)
    void actualizarServicio(@Param("idservicio") Integer idservicio, @Param("horarioinicio") Time horarioinicio, @Param("horariofin") Time horariofin, @Param("nombre") String nombre, 
                            @Param("costo") Integer costo, @Param("cargado") String cargado, @Param("existe") String existe, @Param("reserva") Reserva reserva);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicios WHERE idservicio =: idservicio", nativeQuery = true)
    void eliminarServicio(@Param("idservicio") Integer idservicio);
}
