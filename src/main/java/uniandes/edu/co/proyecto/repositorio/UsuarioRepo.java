package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.Date;
import java.util.List;

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
    void insertarUsuario(@Param("nombreuser") String nombreuser, @Param("tipodocuser") String tipodocuser, @Param("numdocuser") Long numdocuser, 
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
    void actualizarUsuario(@Param("iduser") int iduser, @Param("nombreuser") String nombreuser, @Param("tipodocuser") String tipodocuser, @Param("numdocuser") Long numdocuser, 
                        @Param("correouser") String correouser, @Param("idalojamiento") Alojamiento idalojamiento);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE iduser = :iduser", nativeQuery = true)
    void eliminarUsuario(@Param("iduser") int iduser);

    //REQ5
    @Query(value = "SELECT u.nombreuser, " +
    "h.numhabitacion, " +
    "h.precionoche, " +
    "a.checkin, " +
    "a.checkout, " +
    "s.costo AS costo_servicio, " +
    "p.nombre AS nombre_producto, " +
    "p.precio AS precio_producto " +
    "FROM usuarios u " +
    "JOIN alojamientos a ON u.iduser = a.idusuario " +
    "JOIN habitaciones h ON a.idalojamiento = h.idalojamiento " +
    "LEFT JOIN reservas r ON u.iduser = r.idusuario " +
    "LEFT JOIN servicios s ON r.idreserva = s.idreserva " +
    "LEFT JOIN productos p ON s.idservicio = p.idproducto " +
    "WHERE u.iduser = :idUsuario " +
    "AND a.checkin BETWEEN :fechaInicio AND :fechaFin " +
    "AND a.checkout BETWEEN :fechaInicio AND :fechaFin " +
    "AND a.activa = 'SI'", nativeQuery = true)
List<Object[]> obtenerConsumoPorUsuarioYFechas(@Param("idUsuario") Integer idUsuario,
                                    @Param("fechaInicio") Date fechaInicio,
                                    @Param("fechaFin") Date fechaFin);

    //REQ7
    @Query(value = "SELECT u.nombreuser, " +
    "u.tipodocuser, " +
    "u.numdocuser, " +
    "u.correouser, " +
    "SUM(CASE WHEN a.checkout IS NOT NULL THEN a.checkout - a.checkin ELSE 0 END), " +
    "SUM(c.netocuenta) " +
    "FROM usuarios u " +
    "INNER JOIN alojamientos a ON u.iduser = a.iduser " +
    "INNER JOIN cuentas c ON a.idalojamiento = c.idalojamiento " +
    "WHERE (a.checkin BETWEEN ADD_MONTHS(CURRENT_DATE, -12) AND CURRENT_DATE " +
    "OR a.checkout BETWEEN ADD_MONTHS(CURRENT_DATE, -12) AND CURRENT_DATE) " +
    "GROUP BY u.nombreuser, u.tipodocuser, u.numdocuser, u.correouser " +
    "HAVING SUM(CASE WHEN a.checkout IS NOT NULL THEN a.checkout - a.checkin ELSE 0 END) >= 14 " +
    "OR SUM(c.netocuenta) > 15000000", nativeQuery = true)
List<Object[]> findBuenosClientes();
}
