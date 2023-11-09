package uniandes.edu.co.proyecto.modelo;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="servicios")

public class Servicio {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idservicio;

    private Time horarioinicial;
    private Time horariofinal;
    private Integer costo;
    private String cargado;
    private String existe;
    //FK
    @ManyToOne
    @JoinColumn(name="idreserva", referencedColumnName = "idreserva")
    private Reserva idreserva;


    // Constructor
    public Servicio(Time horarioinicial, Time horariofinal, Integer costo, String cargado, String existe, Reserva idreserva)
    {
        this.horarioinicial = horarioinicial;
        this.horariofinal = horariofinal;
        this.costo = costo;
        this.cargado = cargado;
        this.existe = existe;
        this.idreserva = idreserva;
    }


    public Servicio(){;}


    // Getters
    public Integer getIdservicio(){
        return idservicio;
    }

    public Time getHorarioinicial() {
        return horarioinicial;
    }

    public Time getHorariofinal() {
        return horariofinal;
    }

    public Integer getCosto() {
        return costo;
    }

    public String getCargado() {
        return cargado;
    }

    public String getExiste() {
        return existe;
    }

    public Reserva getIdreserva() {
        return idreserva;
    }


    // Setters
    public void setIdservicio(Integer idservicio){
        this.idservicio = idservicio;
    }

    public void setHorarioinicial(Time horarioinicial) {
        this.horarioinicial = horarioinicial;
    }

    public void setHorariofinal(Time horariofinal) {
        this.horariofinal = horariofinal;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public void setCargado(String cargado) {
        this.cargado = cargado;
    }

    public void setExiste(String existe) {
        this.existe = existe;
    }

    public void setIdreserva(Reserva idreserva) {
        this.idreserva = idreserva;
    }

}
