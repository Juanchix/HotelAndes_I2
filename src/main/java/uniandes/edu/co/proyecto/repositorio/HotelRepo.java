package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Hotel;


public interface HotelRepo extends JpaRepository <Hotel, Integer> {

    // Creation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO hoteles (nombrehotel, nithotel, idhotel) VALUES (:nombrehotel, :nithotel, parranderos_sequence.nextval)", nativeQuery = true)
    void insertarHotel(@Param("nombrehotel") String nombrehotel, @Param("nithotel") Integer nithotel);


    // Read
    @Query(value = "SELECT * FROM hoteles", nativeQuery = true)
    Collection<Hotel> darHoteles();

    @Query(value = "SELECT * FROM hoteles WHERE idhotel = :idhotel", nativeQuery = true)
    Hotel darHotel(@Param("idhotel") int idhotel);


    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE hoteles SET nombrehotel=:nombrehotel, nithotel=:nithotel WHERE idhotel=:idhotel", nativeQuery = true)
    void actualizarHotel(@Param("idhotel") int idhotel, @Param("nombrehotel") String nombrehotel, @Param("nithotel") Integer nithotel);

    
    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM hoteles WHERE idhotel = :idhotel", nativeQuery = true)
    void eliminarHotel(@Param("idhotel") int idhotel);

}