package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.modelo.Spa;

public interface SpaRepo extends JpaRepository <Spa, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO spas (capacidad, idsercicio, idspa) VALUES (:capacidad, :idservicio, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarSpa(@Param("capacidad") Integer capacidad, @Param("idservicio") Servicio idservicio);


    // Read
    @Query(value = "SELECT * FROM spas", nativeQuery = true)
    Collection<Spa> darSpas();

    @Query(value = "SELECT * FROM spas WHERE idspa = :idspa", nativeQuery = true)
    Spa darSpa(@Param("idspa") int idspa);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE spas SET capacidad=:capacidad, idservicio =:idservicio WHERE idspa=:idspa", nativeQuery = true)
    void actualizarSpa(@Param("idspa") int idspa, @Param("capacidad") Integer capacidad, @Param("idservicio") Servicio idservicio);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM spas WHERE idspa = :idspa", nativeQuery = true)
    void eliminarSpa(@Param("idspa") int idspa);
}
