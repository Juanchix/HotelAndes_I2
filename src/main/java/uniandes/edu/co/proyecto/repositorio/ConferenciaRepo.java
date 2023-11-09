package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Conferencia;
import uniandes.edu.co.proyecto.modelo.Servicio;

public interface ConferenciaRepo extends JpaRepository <Conferencia, Integer> {


    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO conferencias (capacidad, fecha, hora, duracion, idservicio, idconferencia) VALUES (:capacidad, :fecha, :hora, :duracion, :idservicio, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarConferencia(@Param("capacidad") Integer capacidad, @Param("fecha") Date fecha, @Param("hora") Time hora,
                            @Param("duracion") Integer duracion, @Param("idservicio") Servicio idservicio);


    // Read
    @Query(value = "SELECT * FROM conferencias", nativeQuery = true)
    Collection<Conferencia> darConferencias();

    @Query(value = "SELECT * FROM conferencias WHERE idconferencia = :idconferencia", nativeQuery = true)
    Conferencia darConferencia(@Param("idconferencia") int idconferencia);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE conferencias SET capacidad=:capacidad, fecha =:fecha, hora =:hora, duracion =:duracion, idservicio =:idservicio WHERE idconferencia=:idconferencia", nativeQuery = true)
    void actualizarConferencia(@Param("idconferencia") int idconferencia, @Param("capacidad") Integer capacidad, @Param("fecha") Date fecha,
                            @Param("hora") Time hora, @Param("duracion") Integer duracion, @Param("idservicio") Servicio idservicio);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM conferencias WHERE idconferencia = :idconferencia", nativeQuery = true)
    void eliminarConferencia(@Param("idconferencia") int idconferencia);
}
