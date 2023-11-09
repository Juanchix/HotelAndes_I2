package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="habitaciones")

public class Habitacion {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idhabitacion;

    private Integer numhabitacion;
    private String disponible;
    private Integer precionoche;
    //FK
    @ManyToOne
    @JoinColumn(name="idhotel", referencedColumnName = "idhotel")
    private Hotel idhotel;
    @ManyToOne
    @JoinColumn(name="idtipo", referencedColumnName = "idtipo")
    private Tipo idtipo;
    @ManyToOne
    @JoinColumn(name="idalojamiento", referencedColumnName = "idalojamiento")
    private Alojamiento idalojamiento;


    // Constructor
    public Habitacion(Integer numhabitacion, String disponible, Integer precionoche, Hotel idhotel, Tipo idtipo, Alojamiento idalojamiento)
    {
        this.numhabitacion = numhabitacion;
        this.disponible = disponible;
        this.precionoche = precionoche;
        this.idhotel = idhotel;
        this.idtipo = idtipo;
        this.idalojamiento = idalojamiento;
    }


    public Habitacion(){;}


    // Getters
    public Integer getIdhabitacion(){
        return idhabitacion;
    }

    public Integer getNumhabitacion() {
        return numhabitacion;
    }

    public String getDisponible() {
        return disponible;
    }

    public Integer getPrecionoche() {
        return precionoche;
    }

    public Hotel getIdhotel() {
        return idhotel;
    }

    public Tipo getIdtipo() {
        return idtipo;
    }

    public Alojamiento getIdalojamiento() {
        return idalojamiento;
    }


    // Setters
    public void setIdhabitacion(Integer idhabitacion){
        this.idhabitacion = idhabitacion;
    }

    public void setNumhabitacion(Integer numhabitacion) {
        this.numhabitacion = numhabitacion;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public void setPrecionoche(Integer precionoche) {
        this.precionoche = precionoche;
    }

    public void setIdhotel(Hotel idhotel) {
        this.idhotel = idhotel;
    }

    public void setIdtipo(Tipo idtipo) {
        this.idtipo = idtipo;
    }

    public void setIdalojamiento(Alojamiento idalojamiento) {
        this.idalojamiento = idalojamiento;
    }

}
