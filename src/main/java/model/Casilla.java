package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Casilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCasilla;

    //tipo de casilla (inicio, normal, segura, etc...)
    private String tipoCasilla;
    private int posicion;

    @ManyToOne
    private int IdPartida;
    
    // Constructor por defecto necesario para JPA
    public Casilla() {
    }

    // Constructor con argumentos
    public Casilla(String tipoCasilla, int posicion, int IdPartida) {
    	this.tipoCasilla = tipoCasilla;
    	this.posicion = posicion;
    	this.IdPartida = IdPartida;
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

    public int getPartida() {
        return IdPartida;
    }

    public void setPartida(int IdPartida) {
        this.IdPartida = IdPartida;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "Casilla: " + idCasilla + " tipo de casilla: "+ tipoCasilla + ", posicion: " + posicion + ", idPartida: " + IdPartida;
    }
}
