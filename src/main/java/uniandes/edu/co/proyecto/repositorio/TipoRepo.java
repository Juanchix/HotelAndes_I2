package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Tipo;


public interface TipoRepo extends JpaRepository <Tipo, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipos (tipo, capacidad, dotacion, idtipo) VALUES (:tipo, :capacidad, :dotacion, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarTipo(@Param("tipo") String tipo, @Param("capacidad") Integer capacidad, @Param("dotacion") String dotacion);


    // Read
    @Query(value = "SELECT * FROM tipos", nativeQuery = true)
    Collection<Tipo> darTipos();

    @Query(value = "SELECT * FROM tipos WHERE idtipo = :idtipo", nativeQuery = true)
    Tipo darTipo(@Param("idtipo") int idtipo);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE tipos SET tipo=:tipo, capacidad=:capacidad, dotacion=:dotacion WHERE idtipo=:idtipo", nativeQuery = true)
    void actualizarTipo(@Param("idtipo") int idtipo, @Param("tipo") String tipo, @Param("capacidad") Integer capacidad, @Param("dotacion") String dotacion);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tipos WHERE idtipo = :idtipo", nativeQuery = true)
    void eliminarTipo(@Param("idtipo") int idtipo);

}