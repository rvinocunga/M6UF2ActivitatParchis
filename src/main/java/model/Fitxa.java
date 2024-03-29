package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Fitxa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFitxa;

    private int posicio;
    private boolean activa;

    @ManyToOne
    private Jugador jugador;

    @ManyToOne
    private Partida partida;

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
}
