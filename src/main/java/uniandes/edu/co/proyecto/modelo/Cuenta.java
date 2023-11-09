package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cuentas")

public class Cuenta {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idcuenta;

    private Integer netocuenta;
    //FK
    @ManyToOne
    @JoinColumn(name="idalojamiento", referencedColumnName = "idalojamiento")
    private Alojamiento idalojamiento;


    // Constructor
    public Cuenta(Integer netocuenta, Alojamiento idalojamiento)
    {
        this.netocuenta = netocuenta;
        this.idalojamiento = idalojamiento;
    }


    public Cuenta(){;}


    // Getters
    public Integer getIdcuenta(){
        return idcuenta;
    }

    public Integer getNetocuenta() {
        return netocuenta;
    }

    public Alojamiento getIdalojamiento() {
        return idalojamiento;
    }


    // Setters
    public void setIdcuenta(Integer idcuenta){
        this.idcuenta = idcuenta;
    }

    public void setNetocuenta(Integer netocuenta) {
        this.netocuenta = netocuenta;
    }

    public void setIdalojamiento(Alojamiento idalojamiento) {
        this.idalojamiento = idalojamiento;
    }

}
