package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Servispa;
import uniandes.edu.co.proyecto.modelo.Spa;

public interface ServispaRepo extends JpaRepository <Servispa, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servispas (duracion, costo, fecha, idspa, idservispas) VALUES (:duracion, :costo, :fecha, :idspa, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarServispa(@Param("duracion") Integer duracion, @Param("costo") Integer costo, @Param("fecha") Date fecha, 
                            @Param("idspa") Spa idspa);

    // Read
    @Query(value = "SELECT * FROM servispas", nativeQuery = true)
    Collection<Servispa> darServispas();

    @Query(value = "SELECT * FROM servispas WHERE idservispas = :idservispas", nativeQuery = true)
    Servispa darServispa(@Param("idservispas") int idservispas);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE servispas SET duracion=:duracion, costo=:costo, fecha=:fecha, idspa=:idspa WHERE idservispas=:idservispas", nativeQuery = true)
    void actualizarServispa( @Param("idservispas") int idservispas, @Param("duracion") Integer duracion, @Param("costo") Integer costo, @Param("fecha") Date fecha, 
                            @Param("idspa") Spa idspa);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servispas WHERE idservispas = :idservispas", nativeQuery = true)
    void eliminarServispa(@Param("idservispas") int idservispas);
}
