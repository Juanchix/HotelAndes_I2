package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Alojamiento;
import uniandes.edu.co.proyecto.modelo.Plan;

public interface AlojamientoRepo extends JpaRepository <Alojamiento, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO alojamientos (activa, checkin, checkout, acompanantes, idplan, idalojamiento) VALUES (:activa, :checkin, :checkout, :acompanantes, :idplan, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarAlojamiento(@Param("activa") String activa, @Param("checkin") Date checkin, @Param("checkout") Date checkout, 
                            @Param("acompanantes")Integer acompanantes, @Param("idplan") Plan idplan);


    // Read
    @Query(value = "SELECT * FROM alojamientos", nativeQuery = true)
    Collection<Alojamiento> darAlojamientos();

    @Query(value = "SELECT * FROM alojamientos WHERE idalojamiento = :idalojamiento", nativeQuery = true)
    Alojamiento darAlojamiento(@Param("idalojamiento") int idalojamiento);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE alojamientos SET activa=:activa, checkin=:checkin, checkout=:checkout, acompanantes=:acompanantes, idplan=:idplan WHERE idalojamiento=:idalojamiento", nativeQuery = true)
    void actualizarAlojamiento(@Param("idalojamiento") int idalojamiento, @Param("activa") String activa, @Param("checkin") Date checkin, @Param("checkout") Date checkout, 
                                @Param("acompanantes")Integer acompanantes, @Param("idplan") Plan idplan);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM alojamientos WHERE idalojamiento = :idalojamiento", nativeQuery = true)
    void eliminarAlojamiento(@Param("idalojamiento") int idalojamiento);

    //REQ6
    @Query(value = "WITH Ocupacion AS ( " +
    "SELECT a.checkin, COUNT(*) AS habitaciones_ocupadas " +
    "FROM alojamientos a " +
    "JOIN habitaciones h ON a.idalojamiento = h.idalojamiento " +
    "WHERE a.activa = 'SI' " +
    "GROUP BY a.checkin " +
    "), " +
    "Ingresos AS ( " +
    "SELECT a.checkin, SUM(c.netocuenta) AS ingresos_totales " +
    "FROM alojamientos a " +
    "JOIN cuentas c ON a.idalojamiento = c.idalojamiento " +
    "WHERE a.activa = 'SI' " +
    "GROUP BY a.checkin " +
    ") " +
    "SELECT " +
    "(SELECT checkin FROM Ocupacion ORDER BY habitaciones_ocupadas DESC FETCH FIRST ROW ONLY) AS dia_mayor_ocupacion, " +
    "(SELECT checkin FROM Ingresos ORDER BY ingresos_totales DESC FETCH FIRST ROW ONLY) AS dia_mayores_ingresos, " +
    "(SELECT checkin FROM Ocupacion ORDER BY habitaciones_ocupadas ASC FETCH FIRST ROW ONLY) AS dia_menor_demanda " +
    "FROM dual", nativeQuery = true)
Map<String, Date> findHotelOperationAnalysis();

}


