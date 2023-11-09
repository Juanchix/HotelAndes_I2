package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name="lavados")

public class Lavado {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idlavado;

    private Integer prendas;
    private String tipo;
    private Integer zapatos;
    //FK
    @ManyToOne
    @JoinColumn(name="idservicio", referencedColumnName = "idservicio")
    private Servicio idservicio;


    // Constructor
    public Lavado(Integer prendas, String tipo, Integer zapatos, Servicio idservicio, Integer idlavado)
    {
        this.prendas = prendas;
        this.tipo = tipo;
        this.zapatos = zapatos;
        this.idservicio = idservicio;
        this.idlavado = idlavado;
    }


    public Lavado(){;}


    // Getters
    public Integer getPrendas() {
        return prendas;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getZapatos() {
        return zapatos;
    }

    public Servicio getIdservicio(){
        return idservicio;
    }

    public Integer getIdlavado(){
        return idlavado;
    }


    // Setters
    public void setPrendas(Integer prendas) {
        this.prendas = prendas;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setZapatos(Integer zapatos) {
        this.zapatos = zapatos;
    }
    
    public void setIdservicio(Servicio idservicio)
    {
        this.idservicio = idservicio;
    }

    public void setIdlavado(Integer idlavado)
    {
        this.idlavado = idlavado;
    }
}
