package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Bar;
import uniandes.edu.co.proyecto.modelo.Servicio;

public interface BarRepo extends JpaRepository <Bar, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO bares (estilo, idservicio, idbar) VALUES (:estilo, :idservicio, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarBar(@Param("estilo") String estilo, @Param("idservicio") Servicio idServicio);


    // Read
    @Query(value = "SELECT * FROM bares", nativeQuery = true)
    Collection<Bar> darBares();

    @Query(value = "SELECT * FROM bares WHERE idbar = :idbar", nativeQuery = true)
    Bar darBar(@Param("idbar") int idbar);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE bares SET estilo=:estilo, idservicio =:idservicio WHERE idbar=:idbar", nativeQuery = true)
    void actualizarBar(@Param("idbar") int idbar, @Param("estilo") String estilo, @Param("idservicio") Servicio idServicio);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bares WHERE idbar = :idbar", nativeQuery = true)
    void eliminarBar(@Param("idbar") int idbar);
}
