package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name="restaurantes")

public class Restaurante{

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idrestaurante;

    private String estilo;
    //FK
    @ManyToOne
    @JoinColumn(name="idservicio", referencedColumnName = "idservicio")
    private Servicio idservicio;


    // Constructor
    public Restaurante(String estilo, Servicio idservicio, Integer idrestaurante)
    {
        this.estilo = estilo;
        this.idservicio = idservicio;
        this.idrestaurante = idrestaurante;
    }


    public Restaurante(){;}


    // Getters
    public String getEstilo()
    {
        return this.estilo;
    }

    public Servicio getIdservicio(){
        return idservicio;
    }   

    public Integer getIdrestaurante(){
        return idrestaurante;
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

    public void setIdrestaurante(Integer idrestaurante)
    {
        this.idrestaurante = idrestaurante;
    }
    
}
