package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Spa;

public interface SpaRepo extends JpaRepository <Spa, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO spas (capacidad, idservicio) VALUES (:capacidad, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarSpa(@Param("capacidad") Integer capacidad);


    // Read
    @Query(value = "SELECT * FROM spas", nativeQuery = true)
    Collection<Spa> darSpas();

    @Query(value = "SELECT * FROM spas WHERE idservicio = :idservicio", nativeQuery = true)
    Spa darSpa(@Param("idservicio") int idservicio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE spas SET capacidad=:capacidad WHERE idservicio=:idservicio", nativeQuery = true)
    void actualizarSpa(@Param("idservicio") int idservicio, @Param("capacidad") Integer capacidad);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM spas WHERE idservicio =: idservicio", nativeQuery = true)
    void eliminarSpa(@Param("idservicio") int idservicio);
}
