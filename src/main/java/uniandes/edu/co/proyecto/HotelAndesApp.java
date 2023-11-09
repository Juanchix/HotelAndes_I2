package uniandes.edu.co.proyecto;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.proyecto.modelo.Hotel;
import uniandes.edu.co.proyecto.repositorio.HotelRepo;

@SpringBootApplication
public class HotelAndesApp implements CommandLineRunner{

	@Autowired
	private HotelRepo hotelRepo;
	public static void main(String[] args) {
		SpringApplication.run(HotelAndesApp.class, args);
	}

	@Override
	public void run(String... args) {
		Collection<Hotel> hoteles = hotelRepo.darHoteles();
		for(Hotel h: hoteles)
		{
			System.out.println(h);
		}
		
	}
}