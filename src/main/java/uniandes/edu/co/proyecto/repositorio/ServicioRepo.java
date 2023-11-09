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
    @Query(value = "INSERT INTO servicios (horarioinicial, horariofinal, costo, cargado, existe, idservicio, idreserva) VALUES (:horarioinicial, :horariofinal, :costo, :cargado, :existe, :idservicio, :idreserva)", nativeQuery = true)
    void insertarServicio(@Param("horarioinicial") Time horarioinicial, @Param("horariofinal") Time horariofinal, @Param("costo") Integer costo, 
                            @Param("cargado") String cargado, @Param("existe") String existe, @Param("idreserva") Reserva idreserva);


    // Read
    @Query(value = "SELECT * FROM servicios", nativeQuery = true)
    Collection<Servicio> darServicios();

    @Query(value = "SELECT * FROM servicios WHERE idservicio = :idservicio", nativeQuery = true)
    Servicio darServicio(@Param("idservicio") int idservicio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE servicios SET horarioinicial=:horarioinicial, horariofinal=:horariofinal, costo=:costo, cargado=:cargado, existe=:existe, idreserva=:idreserva WHERE idservicio=:idservicio", nativeQuery = true)
    void actualizarServicio( @Param("idservicio") int idservicio, @Param("horarioinicial") Time horarioinicial, @Param("horariofinal") Time horariofinal, @Param("costo") Integer costo, 
                            @Param("cargado") String cargado, @Param("existe") String existe, @Param("idreserva") Reserva idreserva);

    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicios WHERE idservicio = :idservicio", nativeQuery = true)
    void eliminarServicio(@Param("idservicio") int idservicio);
}
