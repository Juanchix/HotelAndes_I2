package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reservas")

public class Reserva {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idreserva;

    private Date horareserva;
    //FK
    @ManyToOne
    @JoinColumn(name = "idcuenta", referencedColumnName = "idcuenta")
    private Cuenta idcuenta;


    // Constructor
    public Reserva(Date horareserva, Cuenta idcuenta)
    {
        this.horareserva = horareserva;
        this.idcuenta = idcuenta;
    }


    public Reserva(){;}


    // Getters
    public Integer getIdreserva(){
        return idreserva;
    }

    public Date getHorareserva() {
        return horareserva;
    }

    public Cuenta getIdcuenta() {
        return idcuenta;
    }


    // Setters
    public void setIdreserva(Integer idreserva){
        this.idreserva = idreserva;
    }
    
    public void setHorareserva(Date horareserva) {
        this.horareserva = horareserva;
    }

    public void setIdcuenta(Cuenta idcuenta) {
        this.idcuenta = idcuenta;
    }

}
