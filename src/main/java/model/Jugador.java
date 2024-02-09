package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Table(name = "Jugadors")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJugador")
    private int idJugador;

    @Column(name = "nom")
    private String nom;

    @Column(name = "color")
    private String color;

    @Column(name = "victories")
    private int victories;

    @OneToMany(mappedBy = "jugador")
    private Set<Fitxa> fitxes;

    // Constructors
    public Jugador() {
    }

    public Jugador(String nom, String color) {
        this.nom = nom;
        this.color = color;
        this.victories = 0; // Inicialment, el jugador no té victòries
    }

    // Getters i Setters
    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
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

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public Set<Fitxa> getFitxes() {
        return fitxes;
    }

    public void setFitxes(Set<Fitxa> fitxes) {
        this.fitxes = fitxes;
    }

}
