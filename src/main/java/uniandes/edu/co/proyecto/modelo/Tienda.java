package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name="tiendas")

public class Tienda {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idtienda;

    private String tipo;
    //FK
    @ManyToOne
    @JoinColumn(name="idservicio", referencedColumnName = "idservicio")
    private Servicio idservicio;

    

    // Constructor
    public Tienda(String tipo, Servicio idservicio, Integer idtienda)
    {
        this.tipo = tipo;
        this.idservicio = idservicio;
        this.idtienda = idtienda;
    }


    public Tienda(){;}


    // Getters
    public String getTipo()
    {
        return this.tipo;
    }

    public Servicio getIdservicio(){
        return idservicio;
    }

    public Integer getIdtienda(){
        return idtienda;
    }

    // Setters
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public void setIdservicio(Servicio idservicio)
    {
        this.idservicio = idservicio;
    }

    public void setIdtienda(Integer idtienda)
    {
        this.idtienda = idtienda;
    }

}
