package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJugador;
    private String nom;
    private String color;
    private int victories;

    // Constructor por defecto necesario para JPA
    public Jugador() {
    }

    // Constructor con argumentos
    public Jugador(int idJugador, String nom, String color, int victories) {
        this.idJugador = idJugador;
        this.nom = nom;
        this.color = color;
        this.victories = victories;
    }

    // Getters y Setters

    public int getId() {
        return idJugador;
    }

    // Este setter podría no ser necesario si no necesitas cambiar el ID después de la creación del objeto
    /*
    public void setId(int id) {
        this.idJugador = id;
    }
    */

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getColor() {
        return color;
    }
    //no cambiará de color
    /*
    public void setColor(String color) {
        this.color = color;
    }
    */

    public int getVictories() {
        return victories;
    }

    // Este setter podría no ser necesario si no necesitas cambiar las victorias después de la creación del objeto
    /*
    public void setVictories(int victories) {
        this.victories = victories;
    }
    */

    // Método toString
    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + idJugador +
                ", nom='" + nom + '\'' +
                ", color='" + color + '\'' +
                ", victories=" + victories +
                '}';
    }
}
