package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Alojamiento;
import uniandes.edu.co.proyecto.modelo.Usuario;

public interface UsuarioRepo extends JpaRepository <Usuario, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (iduser, nombreuser, tipodocuser, numdocuser, correouser) VALUES (parranderos_sequence.nextval, :nombreuser, :tipodocuser, :numdocuser, :correouser)", nativeQuery = true)
    void insertarUsuario(@Param("nombreuser") String nombreuser, @Param("tipodocuser") String tipodocuser, @Param("numdocuser") Integer numdocuser, 
                            @Param("correouser") String correouser);


    // Read
    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM usuarios WHERE iduser = :iduser", nativeQuery = true)
    Usuario darUsuario(@Param("iduser") Integer iduser);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET nombreuser=:nombreuser, tipodocuser=:tipodocuser, numdocuser=:numdocuser, correouser=:correouser WHERE iduser=:iduser", nativeQuery = true)
    void actualizarUsuario(@Param("iduser") Integer iduser, @Param("nombreuser") String nombreuser, @Param("tipodocuser") String tipodocuser, @Param("numdocuser") Integer numdocuser, 
                            @Param("correouser") String correouser);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE iduser =: iduser", nativeQuery = true)
    void eliminarUsuario(@Param("iduser") Integer iduser);
}
