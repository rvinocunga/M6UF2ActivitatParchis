package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPartida;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    @ManyToOne
    private Jugador ganador;

    private boolean enCurso;

    // Constructors

    public Partida() {
    }

    public Partida(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
        this.enCurso = true;
    }    
    
    // Getters y Setters

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    /*
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
     */
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public boolean isEnCurso() {
        return enCurso;
    }

    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }
    
    // toString

    @Override
    public String toString() {
        return "Partida{" + "idPartida=" + idPartida + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", ganador=" + ganador + ", enCurso=" + enCurso + '}';
    }
    
}
