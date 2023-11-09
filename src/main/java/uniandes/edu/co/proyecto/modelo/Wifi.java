package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name="wifis")

public class Wifi {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idwifi;

    private Integer anchobanda;
    //FK
    @ManyToOne
    @JoinColumn(name="idservicio", referencedColumnName = "idservicio")
    private Servicio idservicio;
    

    // Constructor
    public Wifi(Integer anchobanda, Servicio idservicio, Integer idwifi)
    {
        this.anchobanda = anchobanda;
        this.idservicio = idservicio;
        this.idwifi = idwifi;
    }


    public Wifi(){;}


    // Getters
    public Integer getAnchobanda() {
        return anchobanda;
    }

    public Servicio getIdservicio(){
        return idservicio;
    }

    public Integer getIdwifi(){
        return idwifi;
    }

    // Setters
    public void setAnchobanda(Integer anchobanda) {
        this.anchobanda = anchobanda;
    }

    public void setIdservicio(Servicio idservicio) {
        this.idservicio = idservicio;
    }

    public void setIdwifi(Integer idwifi) {
        this.idwifi = idwifi;
    }
}
