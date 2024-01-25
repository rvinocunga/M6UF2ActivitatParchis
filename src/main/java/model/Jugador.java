package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jugador {
	/*
	 * IdJugador (clau primària) 
	 * Nom (nom del jugador) 
	 * Color (color associat al jugador) 
	 * Victòries (nombre de partides guanyades)
	 * 
	 */
	
	@Id
	private int IdJugador;
	
	private String nom;
	
	private String color;
	
	private int victories;
	
	//
	// Constructors
    //
	
	public Jugador(String nom, String color, int victories) {
		this.nom = nom;
		this.color = color;
		this.victories = victories;
    }
	
	//
	// Getters and setters
	//
	
	public int getId() {
        return IdJugador;
    }

	//setter necessary?
    public void setId(int id) {
        this.IdJugador = id;
    }
    
    public String getNom() {
    	return nom;
    }

    public void setNom(String nom) {
    	this.nom = nom;
    }
    
    public String getColor() {
    	return color;
    }

    public void setColor(String color) {
    	this.color = color;
    }
    
    public int getVictories() {
        return victories;
    }

    //setter necessary?
    public void setVictories(int victories) {
        this.victories = victories;
    }

    //
    //toString
    //
    public String toString() {
    	return " - Jugador "+ nom + ": amb ID " + IdJugador + ", color: " + color + ", victories: " + victories + ". ";
    }
    
}
