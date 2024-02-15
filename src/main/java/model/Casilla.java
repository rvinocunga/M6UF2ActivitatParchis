package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Casilla")
public class Casilla implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCasilla;

    private String tipoCasilla;
    private int posicion;

    @ManyToOne
    @JoinColumn(name = "IDCASILLA", nullable = false) // Defineix la clau forana per Partida
    private Partida partida;
    
    private int idPartida;

    // Constructors
    
    public Casilla() {
    }

    public Casilla(String tipoCasilla, int posicion, Partida partida) {
        this.tipoCasilla = tipoCasilla;
        this.posicion = posicion;
        this.partida = partida;
    }
    
    // Getters y Setters

    public int getIdCasilla() {
        return idCasilla;
    }

    public void setIdCasilla(int idCasilla) {
        this.idCasilla = idCasilla;
    }

    public String getTipoCasilla() {
        return tipoCasilla;
    }

    public void setTipoCasilla(String tipoCasilla) {
        this.tipoCasilla = tipoCasilla;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    // toString

    @Override
    public String toString() {
        return "Casilla{" + "idCasilla=" + idCasilla + ", tipoCasilla=" + tipoCasilla + ", posicion=" + posicion + ", partida=" + partida + '}';
    }
    
}
