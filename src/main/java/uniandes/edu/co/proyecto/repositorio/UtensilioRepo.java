package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.modelo.Utensilio;

public interface UtensilioRepo extends JpaRepository <Utensilio, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO utensilios (devuelto, estado, idservicio, idutensilio) VALUES (:devuelto, :estado, :idservicio, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarUtensilio(@Param("devuelto") String devuelto, @Param("estado") String estado, @Param("idservicio") Servicio idservicio);


    // Read
    @Query(value = "SELECT * FROM utensilios", nativeQuery = true)
    Collection<Utensilio> darUtensilios();

    @Query(value = "SELECT * FROM utensilios WHERE idutensilio = :idutensilio", nativeQuery = true)
    Utensilio darUtensilio(@Param("idutensilio") int idutensilio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE utensilios SET devuelto=:devuelto, estado =:estado, idservicio =:idservicio WHERE idutensilio=:idutensilio", nativeQuery = true)
    void actualizarUtensilio(@Param("idutensilio") int idutensilio, @Param("devuelto") String devuelto, @Param("estado") String estado,
                            @Param("idservicio") Servicio idservicio);
                            
    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM utensilios WHERE idutensilio = :idutensilio", nativeQuery = true)
    void eliminarUtensilio(@Param("idutensilio") int idutensilio);
}
