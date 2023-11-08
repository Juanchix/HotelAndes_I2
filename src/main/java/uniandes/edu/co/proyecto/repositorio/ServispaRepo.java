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
    @Query(value = "INSERT INTO servispas (idservispas, duracion, costo, fecha, spa) VALUES (parranderos_sequence.nextval, :duracion, :costo, :fecha, :spa)", nativeQuery = true)
    void insertarServispa(@Param("duracion") Integer duracion, @Param("costo") Integer costo, @Param("fecha") Date fecha, 
                            @Param("spa") Spa spa);


    // Read
    @Query(value = "SELECT * FROM servispas", nativeQuery = true)
    Collection<Servispa> darServispas();

    @Query(value = "SELECT * FROM servispas WHERE idservispas = :idservispas", nativeQuery = true)
    Servispa darServispa(@Param("idservispas") Integer idservispas);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE servispas SET duracion=:duracion, costo=:costo, fecha=:fecha, spa=:spa WHERE idservispas=:idservispas", nativeQuery = true)
    void actualizarServispa(@Param("idservispas") Integer idservispas, @Param("duracion") Integer duracion, @Param("costo") Integer costo, @Param("fecha") Date fecha, 
                            @Param("spa") Spa spa);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servispas WHERE idservispas =: idservispas", nativeQuery = true)
    void eliminarServispa(@Param("idservispas") Integer idservispas);
}
