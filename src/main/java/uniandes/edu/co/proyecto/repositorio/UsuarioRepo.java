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
    @Query(value = "INSERT INTO usuarios (nombreuser, tipodocuser, numdocuser, correouser, iduser, idalojamiento) VALUES (:nombreuser, :tipodocuser, :numdocuser, :correouser, parranderos_sequence.nextval, :idalojamiento)", nativeQuery = true)
    void insertarUsuario(@Param("nombreuser") String nombreuser, @Param("tipodocuser") String tipodocuser, @Param("numdocuser") Integer numdocuser, 
                        @Param("correouser") String correouser, @Param("idalojamiento") Alojamiento idalojamiento);


    // Read
    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM usuarios WHERE iduser = :iduser", nativeQuery = true)
    Usuario darUsuario(@Param("iduser") int iduser);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET nombreuser=:nombreuser, tipodocuser=:tipodocuser, numdocuser=:numdocuser, correouser=:correouser, idalojamiento=:idalojamiento WHERE iduser=:iduser", nativeQuery = true)
    void actualizarUsuario(@Param("iduser") int iduser, @Param("nombreuser") String nombreuser, @Param("tipodocuser") String tipodocuser, @Param("numdocuser") Integer numdocuser, 
                        @Param("correouser") String correouser, @Param("idalojamiento") Alojamiento idalojamiento);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE iduser =: iduser", nativeQuery = true)
    void eliminarUsuario(@Param("iduser") int iduser);
}
