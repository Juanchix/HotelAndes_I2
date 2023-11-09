package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name="piscinas")

public class Piscina {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idpiscina;

    private Integer capacidad;
    private Float profundidad;
    //FK
    @ManyToOne
    @JoinColumn(name="idservicio", referencedColumnName = "idservicio")
    private Servicio idservicio;


    // Constructor
    public Piscina(Integer capacidad, Float profundidad, Servicio idservicio, Integer idpiscina)
    {
        this.capacidad = capacidad;
        this.profundidad = profundidad;
        this.idservicio = idservicio;
        this.idpiscina = idpiscina;
    }


    public Piscina(){;}


    // Getters
    public Integer getCapacidad() {
        return capacidad;
    }

    public Float getProfundidad() {
        return profundidad;
    }

    public Servicio getIdservicio(){
        return idservicio;
    }

    public Integer getIdpiscina(){
        return idpiscina;
    }

    // Setters
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setProfundidad(Float profundidad) {
        this.profundidad = profundidad;
    }

    public void setIdservicio(Servicio idservicio)
    {
        this.idservicio = idservicio;
    }

    public void setIdpiscina(Integer idpiscina)
    {
        this.idpiscina = idpiscina;
    }

}
