package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;


@Entity
@Table(name="conferencias")

public class Conferencia {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idconferencia;

    private Integer capacidad;
    private Date fecha;
    private Time hora;
    private Integer duracion;
    //FK
    @ManyToOne
    @JoinColumn(name="idservicio", referencedColumnName = "idservicio")
    private Servicio idservicio;


    // Constructor
    public Conferencia(Integer capacidad, Date fecha, Time hora, Integer duracion, Servicio idservicio, Integer idconferencia)
    {
        this.capacidad = capacidad;
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.idservicio = idservicio;
        this.idconferencia = idconferencia;
    }


    public Conferencia(){;}


    // Getters
    public Integer getCapacidad() {
        return capacidad;
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

    public Integer getIdconferencia(){
        return idconferencia;
    }


    // Setters
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
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

    public void setIdservicio(Servicio idservicio){
        this.idservicio = idservicio;
    }

    public void setIdconferencia(Integer idconferencia)
    {
        this.idconferencia = idconferencia;
    }
}
