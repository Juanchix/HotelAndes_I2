package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Gimnasio;
import uniandes.edu.co.proyecto.modelo.Servicio;

public interface GimnasioRepo extends JpaRepository <Gimnasio, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO gimnasios (capacidad, maquinas, idservicio, idgimnasio) VALUES (:capacidad, :maquinas, :idservicio, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarGimnasio(@Param("capacidad") Integer capacidad, @Param("maquinas") String maquinas, @Param("idservicio") Servicio idservicio);


    // Read
    @Query(value = "SELECT * FROM gimnasios", nativeQuery = true)
    Collection<Gimnasio> darGimnasios();

    @Query(value = "SELECT * FROM gimnasios WHERE idgimnasio = :idgimnasio", nativeQuery = true)
    Gimnasio darGimnasio(@Param("idgimnasio") int idgimnasio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE gimnasios SET capacidad=:capacidad, maquinas =:maquinas, idservicio =:idservicio WHERE idgimnasio=:idgimnasio", nativeQuery = true)
    void actualizarGimnasio(@Param("idgimnasio") int idgimnasio, @Param("capacidad") Integer capacidad, @Param("maquinas") String maquinas,
                            @Param("idservicio") Servicio idservicio);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM gimnasios WHERE idgimnasio = :idgimnasio", nativeQuery = true)
    void eliminarGimnasio(@Param("idgimnasio") int idgimnasio);
}
