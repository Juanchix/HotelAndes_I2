package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Restaurante;

public interface RestauranteRepo extends JpaRepository <Restaurante, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO restaurantes (estilo, idservicio) VALUES (:estilo, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarRestaurante(@Param("estilo") String estilo);


    // Read
    @Query(value = "SELECT * FROM restaurantes", nativeQuery = true)
    Collection<Restaurante> darRestaurantes();

    @Query(value = "SELECT * FROM restaurantes WHERE idservicio = :idservicio", nativeQuery = true)
    Restaurante darRestaurante(@Param("idservicio") int idservicio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE restaurantes SET estilo=:estilo WHERE idservicio=:idservicio", nativeQuery = true)
    void actualizarRestaurante(@Param("idservicio") int idservicio, @Param("estilo") String estilo);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM restaurantes WHERE idservicio =: idservicio", nativeQuery = true)
    void eliminarRestaurante(@Param("idservicio") int idservicio);
}
