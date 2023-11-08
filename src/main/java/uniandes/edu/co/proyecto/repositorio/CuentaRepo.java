package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Alojamiento;
import uniandes.edu.co.proyecto.modelo.Cuenta;


public interface CuentaRepo extends JpaRepository <Cuenta, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cuentas (idcuenta, netocuenta, alojamiento) VALUES (parranderos_sequence.nextval, :netocuenta, :alojamiento)", nativeQuery = true)
    void insertarCuenta(@Param("netocuenta") Integer netocuenta, @Param("alojamiento") Alojamiento alojamiento);


    // Read
    @Query(value = "SELECT * FROM cuentas", nativeQuery = true)
    Collection<Cuenta> darCuentas();

    @Query(value = "SELECT * FROM cuentas WHERE idcuenta = :idcuenta", nativeQuery = true)
    Cuenta darCuenta(@Param("idcuenta") int idcuenta);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentas SET netocuenta=:netocuenta, alojamiento=:alojamiento WHERE idcuenta = :idcuenta", nativeQuery = true)
    void actualizarCuenta(@Param("idcuenta") int idcuenta, @Param("netocuenta") Integer netocuenta, @Param("alojamiento") Alojamiento alojamiento);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cuentas WHERE idcuenta =: idcuenta", nativeQuery = true)
    void eliminarCuenta(@Param("idcuenta") int idcuenta);

}