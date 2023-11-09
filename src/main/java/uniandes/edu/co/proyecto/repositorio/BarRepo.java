package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Bar;

public interface BarRepo extends JpaRepository <Bar, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO bares (estilo, idservicio) VALUES (:estilo, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarBar(@Param("estilo") String estilo);


    // Read
    @Query(value = "SELECT * FROM bares", nativeQuery = true)
    Collection<Bar> darBares();

    @Query(value = "SELECT * FROM bares WHERE idservicio = :idservicio", nativeQuery = true)
    Bar darBar(@Param("idservicio") int idservicio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE bares SET estilo=:estilo WHERE idservicio=:idservicio", nativeQuery = true)
    void actualizarBar(@Param("idservicio") int idservicio, @Param("estilo") String estilo);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bares WHERE idservicio =: idservicio", nativeQuery = true)
    void eliminarBar(@Param("idservicio") int idservicio);
}
