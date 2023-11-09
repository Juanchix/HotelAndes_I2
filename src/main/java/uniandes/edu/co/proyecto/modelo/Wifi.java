package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name="wifis")

public class Wifi {

    // Atributos
    @Id //PK
    private Integer idservicio;

    private Integer anchobanda;
    

    // Constructor
    public Wifi(Integer idservicio, Integer anchobanda)
    {
        this.idservicio = idservicio;
        this.anchobanda = anchobanda;
    }


    public Wifi(){;}


    // Getters
    public Integer getIdservicio(){
        return idservicio;
    }

    public Integer getAnchobanda() {
        return anchobanda;
    }


    // Setters
    public void setIdservicio(Integer idservicio)
    {
        this.idservicio = idservicio;
    }

    public void setAnchobanda(Integer anchobanda) {
        this.anchobanda = anchobanda;
    }

}
