package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name="spas")

public class Spa {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idspa;

    private Integer capacidad;
    //FK
    @ManyToOne
    @JoinColumn(name="idservicio", referencedColumnName = "idservicio")
    private Servicio idservicio;


    // Constructor
    public Spa(Integer capacidad, Servicio idservicio, Integer idspa)
    {
        this.capacidad = capacidad;
        this.idservicio = idservicio;
        this.idspa = idspa;
    }


    public Spa(){;}


    // Getters
    public Integer getCapacidad() {
        return capacidad;
    }

    public Servicio getIdservicio(){
        return idservicio;
    }

    public Integer getIdspa(){
        return idspa;
    }

    // Setters
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setIdservicio(Servicio idservicio) {
        this.idservicio = idservicio;
    }

    public void setIdspa(Integer idspa) {
        this.idspa = idspa;
    }

}
