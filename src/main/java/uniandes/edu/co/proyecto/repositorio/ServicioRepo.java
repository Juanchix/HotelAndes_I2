package uniandes.edu.co.proyecto.repositorio;

import java.sql.Time;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Reserva;
import uniandes.edu.co.proyecto.modelo.Servicio;

public interface ServicioRepo extends JpaRepository <Servicio, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicios (horarioinicial, horariofinal, costo, cargado, existe, idservicio, idreserva) VALUES (:horarioinicial, :horariofinal, :costo, :cargado, :existe, :idservicio, :idreserva)", nativeQuery = true)
    void insertarServicio(@Param("horarioinicial") Time horarioinicial, @Param("horariofinal") Time horariofinal, @Param("costo") Integer costo, 
                            @Param("cargado") String cargado, @Param("existe") String existe, @Param("idreserva") Reserva idreserva);


    // Read
    @Query(value = "SELECT * FROM servicios", nativeQuery = true)
    Collection<Servicio> darServicios();

    @Query(value = "SELECT * FROM servicios WHERE idservicio = :idservicio", nativeQuery = true)
    Servicio darServicio(@Param("idservicio") int idservicio);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE servicios SET horarioinicial=:horarioinicial, horariofinal=:horariofinal, costo=:costo, cargado=:cargado, existe=:existe, idreserva=:idreserva WHERE idservicio=:idservicio", nativeQuery = true)
    void actualizarServicio( @Param("idservicio") int idservicio, @Param("horarioinicial") Time horarioinicial, @Param("horariofinal") Time horariofinal, @Param("costo") Integer costo, 
                            @Param("cargado") String cargado, @Param("existe") String existe, @Param("idreserva") Reserva idreserva);

    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicios WHERE idservicio = :idservicio", nativeQuery = true)
    void eliminarServicio(@Param("idservicio") int idservicio);

    //REQ2
    @Query(value = "SELECT s.idservicio, " +
    "s.horarioinicial, " +
    "s.horariofinal, " +
    "s.costo, " +
    "b.estilo AS bar_estilo, " +
    "c.capacidad AS conferencia_capacidad, " +
    "c.fecha AS conferencia_fecha, " +
    "g.capacidad AS gimnasio_capacidad, " +
    "p.capacidad AS piscina_capacidad, " +
    "r.estilo AS restaurante_estilo, " +
    "re.capacidad AS reunion_capacidad, " +
    "spa.capacidad AS spa_capacidad, " +
    "t.tipo AS tienda_tipo, " +
    "u.estado AS utensilio_estado, " +
    "w.anchobanda AS wifi_anchobanda, " +
    "la.prendas AS lavado_prendas, " +
    "COUNT(res.idreserva) AS cantidad_reservas " +
    "FROM servicios s " +
    "LEFT JOIN bares b ON s.idservicio = b.idservicio " +
    "LEFT JOIN conferencias c ON s.idservicio = c.idservicio " +
    "LEFT JOIN gimnasios g ON s.idservicio = g.idservicio " +
    "LEFT JOIN piscinas p ON s.idservicio = p.idservicio " +
    "LEFT JOIN restaurantes r ON s.idservicio = r.idservicio " +
    "LEFT JOIN reuniones re ON s.idservicio = re.idservicio " +
    "LEFT JOIN spas spa ON s.idservicio = spa.idservicio " +
    "LEFT JOIN tiendas t ON s.idservicio = t.idservicio " +
    "LEFT JOIN utensilios u ON s.idservicio = u.idservicio " +
    "LEFT JOIN wifi w ON s.idservicio = w.idservicio " +
    "LEFT JOIN lavados la ON s.idservicio = la.idservicio " +
    "JOIN reservas res ON s.idreserva = res.idreserva " +
    "GROUP BY s.idservicio, " +
    "s.horarioinicial, " +
    "s.horariofinal, " +
    "s.costo, " +
    "b.estilo, " +
    "c.capacidad, " +
    "c.fecha, " +
    "g.capacidad, " +
    "p.capacidad, " +
    "r.estilo, " +
    "re.capacidad, " +
    "spa.capacidad, " +
    "t.tipo, " +
    "u.estado, " +
    "w.anchobanda, " +
    "la.prendas " +
    "ORDER BY cantidad_reservas DESC", nativeQuery = true)
    List<Object[]> findTop20PopularServices();

    // Requerimiento 4: Mostrar servicios de ventas proporcionados por tiendas
    @Query(value = "SELECT s.* " +
    "FROM servicios s " +
    "JOIN tiendas t ON s.idservicio = t.idservicio", nativeQuery = true)
    List<Servicio> findAllServiciosDeTiendas();

    //Requerimiento 8
    @Query(value = "SELECT new com.tu.paquete.ServicioDemanda(s.idservicio, COUNT(r.idreserva), TO_CHAR(r.horareserva, 'IW'), TO_CHAR(r.horareserva, 'YYYY')) " +
    "FROM Servicio s JOIN Reserva r ON s.idservicio = r.idreserva " +
    "WHERE r.horareserva BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE " +
    "GROUP BY s.idservicio, TO_CHAR(r.horareserva, 'IW'), TO_CHAR(r.horareserva, 'YYYY') " +
    "HAVING COUNT(r.idreserva) < 3 " +
    "ORDER BY TO_CHAR(r.horareserva, 'YYYY'), TO_CHAR(r.horareserva, 'IW')",
nativeQuery = true)
List<Object[]> findServiciosConBajaDemanda();

}
