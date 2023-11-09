package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name="utensilios")

public class Utensilio {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idutensilio;

    private String devuelto;
    private String estado;
    //FK
    @ManyToOne
    @JoinColumn(name="idservicio", referencedColumnName = "idservicio")
    private Servicio idservicio;



    // Constructor
    public Utensilio(String devuelto, String estado, Servicio idservicio, Integer idutensilio)
    {
        this.devuelto = devuelto;
        this.estado = estado;
        this.idservicio = idservicio;
        this.idutensilio = idutensilio;
    }

    public Utensilio(){;}


    // Getters
    public String getDevuelto()
    {
        return this.devuelto;
    }

    public String getEstado()
    {
        return this.estado;
    }

    public Servicio getIdservicio(){
        return idservicio;
    }

    public Integer getIdutensilio(){
        return idutensilio;
    }


    // Setters
    public void setDevuelto(String devuelto)
    {
        this.devuelto = devuelto;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public void setIdservicio(Servicio idservicio)
    {
        this.idservicio = idservicio;
    }

    public void setIdutensilio(Integer idutensilio)
    {
        this.idutensilio = idutensilio;
    }

}
