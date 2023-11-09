package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;


@Entity
@Table(name="reuniones")

public class Reunion {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idreunion;

    private Integer capacidad;
    private Integer costoadicional;
    private Date fecha;
    private Time hora;
    private Integer duracion;
    //FK
    @ManyToOne
    @JoinColumn(name="idservicio", referencedColumnName = "idservicio")
    private Servicio idservicio;



    // Constructor
    public Reunion(Integer capacidad, Integer costoadicional, Date fecha, Time hora, Integer duracion, Servicio idservicio, Integer idreunion)
    {
        this.capacidad = capacidad;
        this.costoadicional = costoadicional;
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.idservicio = idservicio;
        this.idreunion = idreunion;
    }


    public Reunion(){;}


    // Getters
    public Integer getCapacidad() {
        return capacidad;
    }

    public Integer getCostoadicional() {
        return costoadicional;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public Servicio getIdservicio(){
        return idservicio;
    }

    public Integer getIdreunion(){
        return idreunion;
    }

    // Setters
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setCostoadicional(Integer costoadicional) {
        this.costoadicional = costoadicional;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public void setIdservicio(Servicio idservicio)
    {
        this.idservicio = idservicio;
    }

    public void setIdreunion(Integer idreunion)
    {
        this.idreunion = idreunion;
    }

}
