package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Reunion;
import uniandes.edu.co.proyecto.modelo.Servicio;

public interface ReunionRepo extends JpaRepository <Reunion, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reuniones (capacidad, costoadicional, fecha, hora, duracion, idservicio, idreunion) VALUES (:capacidad, :costoadicional, :fecha, :hora, :duracion, :idservicio, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarReunion(@Param("capacidad") Integer capacidad, @Param("costoadicional") Integer costoadicional, @Param("fecha") Date fecha, 
                        @Param("hora") Time hora, @Param("duracion") Integer duracion, @Param("idservicio") Servicio idservicio);

    // Read
    @Query(value = "SELECT * FROM reuniones", nativeQuery = true)
    Collection<Reunion> darReuniones();

    @Query(value = "SELECT * FROM reuniones WHERE idreunion = :idreunion", nativeQuery = true)
    Reunion darReunion(@Param("idreunion") int idreunion);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE reuniones SET capacidad=:capacidad, costoadicional =:costoadicional, fecha =:fecha, hora =:hora, duracion =:duracion, idservicio =:idservicio WHERE idreunion=:idreunion", nativeQuery = true)
    void actualizarReunion(@Param("idreunion") int idreunion, @Param("capacidad") Integer capacidad, @Param("costoadicional") Integer costoadicional, @Param("fecha") Date fecha,
                            @Param("hora") Time hora, @Param("duracion") Integer duracion, @Param("idservicio") Servicio idservicio);

    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reuniones WHERE idreunion = :idreunion", nativeQuery = true)
    void eliminarReunion(@Param("idreunion") int idreunion);
}
