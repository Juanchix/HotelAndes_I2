package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name="bares")

public class Bar {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idbar;

    private String estilo;
    //FK
    @ManyToOne
    @JoinColumn(name="idservicio", referencedColumnName = "idservicio")
    private Servicio idservicio;


    // Constructor
    public Bar(String estilo, Servicio idservicio, Integer idbar)
    {
        this.estilo = estilo;
        this.idservicio = idservicio;
        this.idbar = idbar;
    }


    public Bar(){;}


    // Getters
    public String getEstilo()
    {
        return this.estilo;
    }

    public Servicio getIdservicio(){
        return idservicio;
    }

    public Integer getIdbar(){
        return idbar;
    }


    // Setters
    public void setEstilo(String estilo)
    {
        this.estilo = estilo;
    }

    public void setIdservicio(Servicio idservicio)
    {
        this.idservicio = idservicio;
    }

    public void setIdbar(Integer idbar)
    {
        this.idbar = idbar;
    }

}
