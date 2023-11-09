package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Utensilio;

public interface UtensilioRepo extends JpaRepository <Utensilio, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO utensilios (devuelto, estado, idservicio) VALUES (:devuelto, :estado, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarUtensilio(@Param("devuelto") String devuelto, @Param("estado") String estado);


    // Read
    @Query(value = "SELECT * FROM utensilios", nativeQuery = true)
    Collection<Utensilio> darUtensilios();

    @Query(value = "SELECT * FROM utensilios WHERE idservicio = :idservicio", nativeQuery = true)
    Utensilio darUtensilio(@Param("idservicio") int idservicio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE utensilios SET devuelto=:devuelto, estado=:estado WHERE idservicio=:idservicio", nativeQuery = true)
    void actualizarUtensilio( @Param("idservicio") int idservicio, @Param("devuelto") String devuelto, @Param("estado") String estado);

    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM utensilios WHERE idservicio =: idservicio", nativeQuery = true)
    void eliminarUtensilio(@Param("idservicio") int idservicio);
}
