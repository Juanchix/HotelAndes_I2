package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Plan;

public interface PlanRepo extends JpaRepository <Plan, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO planes (tipoplan, descuento, idplan) VALUES (:tipoplan, :descuento, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarPlan(@Param("tipoplan") String tipoplan, @Param("descuento") Float descuento);


    // Read
    @Query(value = "SELECT * FROM planes", nativeQuery = true)
    Collection<Plan> darPlanes();

    @Query(value = "SELECT * FROM planes WHERE idplan = :idplan", nativeQuery = true)
    Plan darPlan(@Param("idplan") int idplan);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE planes SET tipoplan=:tipoplan, descuento=:descuento WHERE idplan=:idplan", nativeQuery = true)
    void actualizarPlan(@Param("idplan") int idplan, @Param("tipoplan") String tipoplan, @Param("descuento") Float descuento);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM planes WHERE idplan = :idplan", nativeQuery = true)
    void eliminarPlan(@Param("idplan") int idplan);
}
