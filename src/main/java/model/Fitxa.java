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
public class Fitxa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFitxa;

    private int posicio;
    private boolean activa;

    @ManyToOne()
    @JoinColumn(name = "IDJUGADOR", nullable = false) // Define la clave foránea a Jugador
    private Jugador jugador;

    @ManyToOne()
    @JoinColumn(name = "IDPARTIDA", nullable = false) // Define la clave foránea a Partida
    private Partida partida;

    // Constructores
    public Fitxa() {
    }

    public Fitxa(int posicio, boolean activa, Jugador jugador, Partida partida) {
        this.posicio = posicio;
        this.activa = activa;
        this.jugador = jugador;
        this.partida = partida;
    }

    // Getters y Setters
    public int getIdFitxa() {
        return idFitxa;
    }

    public void setIdFitxa(int idFitxa) {
        this.idFitxa = idFitxa;
    }

    public int getPosicio() {
        return posicio;
    }

    public void setPosicio(int posicio) {
        this.posicio = posicio;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
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
        return "Fitxa{" + "idFitxa=" + idFitxa + ", posicio=" + posicio + ", activa=" + activa + ", jugador=" + jugador + ", partida=" + partida + '}';
    }

}
