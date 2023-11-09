package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Wifi;

public interface WifiRepo extends JpaRepository <Wifi, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO wifis (anchobanda, idservicio) VALUES (:anchobanda, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarWifi(@Param("anchobanda") Integer anchobanda);


    // Read
    @Query(value = "SELECT * FROM wifis", nativeQuery = true)
    Collection<Wifi> darWifis();

    @Query(value = "SELECT * FROM wifis WHERE idservicio = :idservicio", nativeQuery = true)
    Wifi darWifi(@Param("idservicio") int idservicio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE wifis SET anchobanda = :anchobanda WHERE idservicio = :idservicio", nativeQuery = true)
    void actualizarWifi(@Param("idservicio") int idservicio, @Param("anchobanda") Integer anchobanda);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM wifis WHERE idservicio =: idservicio", nativeQuery = true)
    void eliminarWifi(@Param("idservicio") int idservicio);
}
