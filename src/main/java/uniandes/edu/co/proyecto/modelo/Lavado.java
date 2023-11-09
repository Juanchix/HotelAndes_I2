package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name="lavados")

public class Lavado {

    // Atributos
    @Id //PK
    private Integer idservicio;

    private Integer prendas;
    private String tipo;
    private Integer zapatos;


    // Constructor
    public Lavado(Integer idservicio, Integer prendas, String tipo, Integer zapatos)
    {
        this.idservicio = idservicio;
        this.prendas = prendas;
        this.tipo = tipo;
        this.zapatos = zapatos;
    }


    public Lavado(){;}


    // Getters
    public Integer getIdservicio(){
        return idservicio;
    }

    public Integer getPrendas() {
        return prendas;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getZapatos() {
        return zapatos;
    }


    // Setters
    public void setIdservicio(Integer idservicio)
    {
        this.idservicio = idservicio;
    }

    public void setPrendas(Integer prendas) {
        this.prendas = prendas;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setZapatos(Integer zapatos) {
        this.zapatos = zapatos;
    }
    
}
