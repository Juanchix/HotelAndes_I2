package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Restaurante;
import uniandes.edu.co.proyecto.modelo.Servicio;

public interface RestauranteRepo extends JpaRepository <Restaurante, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO restaurantes (estilo, idservicio, idrestaurante) VALUES (:estilo, :idservicio, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarRestaurante(@Param("estilo") String estilo, @Param("idservicio") Servicio idServicio);

    // Read
    @Query(value = "SELECT * FROM restaurantes", nativeQuery = true)
    Collection<Restaurante> darRestaurantes();

    @Query(value = "SELECT * FROM restaurantes WHERE idrestaurante = :idrestaurante", nativeQuery = true)
    Restaurante darRestaurante(@Param("idrestaurante") int idrestaurante);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE restaurantes SET estilo=:estilo, idservicio =:idservicio WHERE idrestaurante=:idrestaurante", nativeQuery = true)
    void actualizarRestaurante(@Param("idrestaurante") int idrestaurante, @Param("estilo") String estilo, @Param("idservicio") Servicio idServicio);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM restaurantes WHERE idrestaurante = :idrestaurante", nativeQuery = true)
    void eliminarRestaurante(@Param("idrestaurante") int idrestaurante);
}
