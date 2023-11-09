package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Piscina;
import uniandes.edu.co.proyecto.modelo.Servicio;

public interface PiscinaRepo extends JpaRepository <Piscina, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO piscinas (capacidad, profundidad, idservicio, idpiscina) VALUES (:capacidad, :profundidad, :idservicio, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarPiscina(@Param("capacidad") Integer capacidad, @Param("profundidad") Float profundidad, @Param("idservicio") Servicio idservicio);


    // Read
    @Query(value = "SELECT * FROM piscinas", nativeQuery = true)
    Collection<Piscina> darPiscinas();

    @Query(value = "SELECT * FROM piscinas WHERE idpiscina = :idpiscina", nativeQuery = true)
    Piscina darPiscina(@Param("idpiscina") int idpiscina);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE piscinas SET capacidad=:capacidad, profundidad =:profundidad, idservicio =:idservicio WHERE idpiscina=:idpiscina", nativeQuery = true)
    void actualizarPiscina(@Param("idpiscina") int idpiscina, @Param("capacidad") Integer capacidad, @Param("profundidad") Float profundidad,
                            @Param("idservicio") Servicio idservicio);

    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM piscinas WHERE idpiscina = :idpiscina", nativeQuery = true)
    void eliminarPiscina(@Param("idpiscina") int idpiscina);
}
