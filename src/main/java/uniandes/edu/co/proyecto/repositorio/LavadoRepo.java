package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Lavado;
import uniandes.edu.co.proyecto.modelo.Servicio;

public interface LavadoRepo extends JpaRepository <Lavado, Integer>{

    
    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO lavados (prendas, tipo, zapatos, idservicio, idlavado) VALUES (:prendas, :tipo, :zapatos, :idservicio, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarLavado(@Param("prendas") String prendas, @Param("tipo") String tipo, @Param("zapatos") String zapatos, @Param("idservicio") Servicio idservicio);


    // Read
    @Query(value = "SELECT * FROM lavados WHERE idlavado = :idlavado", nativeQuery = true)
    Lavado darLavado(@Param("idlavado") int idlavado);

    @Query(value = "SELECT * FROM lavados", nativeQuery = true)
    Lavado darLavados();


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE lavados SET prendas=:prendas, tipo =:tipo, zapatos =:zapatos, idservicio =:idservicio WHERE idlavado=:idlavado", nativeQuery = true)
    void actualizarLavado(@Param("idlavado") int idlavado, @Param("prendas") String prendas, @Param("tipo") String tipo, @Param("zapatos") String zapatos,
                            @Param("idservicio") Servicio idservicio);
    
    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM lavados WHERE idlavado = :idlavado", nativeQuery = true)
    void eliminarLavado(@Param("idlavado") int idlavado);

}
