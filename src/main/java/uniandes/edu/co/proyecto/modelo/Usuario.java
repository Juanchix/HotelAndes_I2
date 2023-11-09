package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")

public class Usuario { 

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer iduser;

    private String nombreuser;
    private String tipodocuser;
    private Long numdocuser;
    private String correouser;
    //FK
    @ManyToOne
    @JoinColumn(name = "idalojamiento", referencedColumnName = "idalojamiento")
    private Alojamiento idalojamiento;


    // Constructor
    public Usuario(String nombreuser, String tipodocuser, Long numdocuser, String correouser, Alojamiento idalojamiento)
    {
        this.nombreuser = nombreuser;
        this.tipodocuser = tipodocuser;
        this.numdocuser = numdocuser;
        this.correouser = correouser;
        this.idalojamiento = idalojamiento;
    }


    public Usuario(){;}


    // Getters
    public Integer getIduser(){
        return iduser;
    }

    public String getNombreuser() {
        return nombreuser;
    }

    public String getTipodocuser() {
        return tipodocuser;
    }

    public Long getNumdocuser() {
        return numdocuser;
    }

    public String getCorreouser() {
        return correouser;
    }

    public Alojamiento getIdalojamiento() {
        return idalojamiento;
    }



    // Setters
    public void setIduser(Integer iduser){
        this.iduser = iduser;
    }

    public void setNombreuser(String nombreuser) {
        this.nombreuser = nombreuser;
    }

    public void setTipodocuser(String tipodocuser) {
        this.tipodocuser = tipodocuser;
    }

    public void setNumdocuser(Long numdocuser) {
        this.numdocuser = numdocuser;
    }

    public void setCorreouser(String correouser) {
        this.correouser = correouser;
    }

    public void setIdalojamiento(Alojamiento idalojamiento) {
        this.idalojamiento = idalojamiento;
    }

}
