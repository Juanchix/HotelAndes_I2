package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="productos")

public class Producto {

    // Atributos
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idproducto;

    private String nombre;
    private Integer precio;
    //FK
    @ManyToOne
    @JoinColumn(name="idbar", referencedColumnName = "idservicio")
    private Bar idbar;
    @ManyToOne
    @JoinColumn(name="idrestaurante", referencedColumnName = "idservicio")
    private Restaurante idrestaurante;
    @ManyToOne
    @JoinColumn(name="idtienda", referencedColumnName = "idservicio")
    private Tienda idtienda;


    // Constructor
    public Producto(String nombre, Integer precio, Bar idbar, Restaurante idrestaurante, Tienda idtienda)
    {
        this.nombre = nombre;
        this.precio = precio;
        this.idbar = idbar;
        this.idrestaurante = idrestaurante;
        this.idtienda = idtienda;
    }


    public Producto(){;}


    // Getters
    public Integer getIdproducto(){
        return idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public Bar getIdbar() {
        return idbar;
    }

    public Restaurante getIdrestaurante() {
        return idrestaurante;
    }

    public Tienda getIdtienda() {
        return idtienda;
    }


    // Setters
    public void setIdproducto(Integer idproducto){
        this.idproducto = idproducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public void setIdbar(Bar idbar) {
        this.idbar = idbar;
    }

    public void setIdrestaurante(Restaurante idrestaurante) {
        this.idrestaurante = idrestaurante;
    }

    public void setIdtienda(Tienda idtienda) {
        this.idtienda = idtienda;
    }

}
