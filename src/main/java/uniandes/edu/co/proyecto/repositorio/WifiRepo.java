package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.modelo.Wifi;

public interface WifiRepo extends JpaRepository <Wifi, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO wifis (anchobanda, idservicio, idwifi) VALUES (:anchobanda, :idservicio, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarWifi(@Param("anchobanda") Integer anchobanda, @Param("idservicio") Servicio idservicio);


    // Read
    @Query(value = "SELECT * FROM wifis", nativeQuery = true)
    Collection<Wifi> darWifis();

    @Query(value = "SELECT * FROM wifis WHERE idwifi = :idwifi", nativeQuery = true)
    Wifi darWifi(@Param("idwifi") int idwifi);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE wifis SET anchobanda=:anchobanda, idservicio =:idservicio WHERE idwifi=:idwifi", nativeQuery = true)
    void actualizarWifi(@Param("idwifi") int idwifi, @Param("anchobanda") Integer anchobanda, @Param("idservicio") Servicio idservicio);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM wifis WHERE idwifi = :idwifi", nativeQuery = true)
    void eliminarWifi(@Param("idwifi") int idwifi);
}
