package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Gimnasio;
import uniandes.edu.co.proyecto.modelo.Lavado;

public interface LavadoRepo extends JpaRepository <Lavado, Integer>{

    
    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO lavados (prendas, tipo, zapatos, idservicio) VALUES (:prendas, :tipo, :zapatos, :idservicio)", nativeQuery = true)
    void insertarLavado(@Param("prendas") String prendas, @Param("tipo") String tipo, @Param("zapatos") String zapatos, @Param("idservicio") int idservicio);


    // Read
    @Query(value = "SELECT * FROM lavados WHERE idservicio = :idservicio", nativeQuery = true)
    Lavado darLavado(@Param("idservicio") int idservicio);

    @Query(value = "SELECT * FROM lavados WHERE idservicio = :idservicio", nativeQuery = true)
    Gimnasio darLavados(@Param("idservicio") int idservicio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE lavados SET prendas=:prendas, tipo=:tipo, zapatos=:zapatos WHERE idservicio=:idservicio", nativeQuery = true)
    void actualizarLavado(@Param("prendas") String prendas, @Param("tipo") String tipo, @Param("zapatos") String zapatos, @Param("idservicio") int idservicio);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM lavados WHERE idservicio =: idservicio", nativeQuery = true)
    void eliminarLavado(@Param("idservicio") int idservicio);

}
