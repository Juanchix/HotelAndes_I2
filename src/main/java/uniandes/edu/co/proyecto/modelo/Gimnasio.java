package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name="gimnasios")

public class Gimnasio{

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idgimnasio;

    private Integer capacidad;
    private String maquinas;
    //FK
    @ManyToOne
    @JoinColumn(name="idservicio", referencedColumnName = "idservicio")
    private Servicio idservicio;


    // Constructor
    public Gimnasio(Integer capacidad, String maquinas, Servicio idservicio, Integer idgimnasio)
    {
        this.capacidad = capacidad;
        this.maquinas = maquinas;
        this.idservicio = idservicio;
        this.idgimnasio = idgimnasio;
    }


    public Gimnasio(){;}


    // Getters
    public Integer getIdgimnasio()
    {
        return this.idgimnasio;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public String getMaquinas() {
        return maquinas;
    }

    public Servicio getIdservicio(){
        return idservicio;
    }


    // Setters
    public void setIdgimnasio(Integer idgimnasio)
    {
        this.idgimnasio = idgimnasio;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setMaquinas(String maquinas) {
        this.maquinas = maquinas;
    }

    public void setIdservicio(Servicio idservicio)
    {
        this.idservicio = idservicio;
    }
}
