package uniandes.edu.co.proyecto.repositorio;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Alojamiento;
import uniandes.edu.co.proyecto.modelo.Habitacion;
import uniandes.edu.co.proyecto.modelo.Hotel;
import uniandes.edu.co.proyecto.modelo.Tipo;


public interface HabitacionRepo extends JpaRepository <Habitacion, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO habitaciones (idhabitacion, numhabitacion, disponible, precionoche, hotel, tipo, alojamiento) VALUES (parranderos_sequence.nextval, :numhabitacion, :disponible, :precionoche, :hotel, :tipo, :alojamiento)", nativeQuery = true)
    void insertarHabitacion(@Param("numhabitacion") Integer numhabitacion, @Param("disponible") String disponible,
                            @Param("precionoche") Integer precionoche, @Param("hotel") Hotel hotel, 
                            @Param("tipo") Tipo tipo, @Param("alojamiento") Alojamiento alojamiento);


    // Read
    @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();

    @Query(value = "SELECT * FROM habitaciones WHERE idhabitacion = :idhabitacion", nativeQuery = true)
    Habitacion darHabitacion(@Param("idhabitacion") int idhabitacion);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE habitacones SET numhabitacion=:numhabitacion, disponible=:disponible, precionoche=:precionoche, hotel=:hotel, tipo=:tipo, alojamiento=:alojamiento WHERE idhabitacion=:idhabitacion", nativeQuery = true)
    void actualizarHabitacion(@Param("idhabitacion") int idhabitacion, @Param("numhabitacion") Integer numhabitacion, @Param("disponible") String disponible,
                            @Param("precionoche") Integer precionoche, @Param("hotel") Hotel hotel, 
                            @Param("tipo") Tipo tipo, @Param("alojamiento") Alojamiento alojamiento);


    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM habitaciones WHERE idhabitacion =: idhabitacion", nativeQuery = true)
    void eliminarHabitacion(@Param("idhabitacion") int idhabitacion);

      // REQ1
      @Query(value = "SELECT h.numhabitacion, SUM(s.costo) AS totalRecolectado " +
      "FROM habitaciones h " +
      "JOIN alojamientos a ON h.alojamiento = a.idalojamiento " +
      "JOIN cuentas c ON a.idalojamiento = c.alojamiento " +
      "JOIN reservas r ON c.idcuenta = r.cuenta " +
      "JOIN servicios s ON r.idreserva = s.reserva " +
      "WHERE s.horarioinicial >= :startDate " +
      "AND s.horariofinal <= :endDate " +
      "AND s.existe = 'SI' " +
      "GROUP BY h.numhabitacion", nativeQuery = true)
List<Object[]> calcularDineroRecolectadoPorHabitacionEnElUltimoAnio(
@Param("startDate") Date startDate,
@Param("endDate") Date endDate
);

    // REQ3
    @Query(value = "SELECT new map(h.numhabitacion as numhabitacion, " + 
    "SUM(a.checkout - a.checkin) as dias_ocupados, " +
    "(SUM(a.checkout - a.checkin) / 365) * 100 as indice_ocupacion) " +
    "FROM Habitaciones h " +
    "JOIN h.alojamientos a " +
    "WHERE a.checkin BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE " +
    "GROUP BY h.numhabitacion")
Collection<Map<String, Object>> calcularIndiceOcupacion();

}