package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.Set;

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
    
    //@OneToMany(mappedBy = "partida", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Jugador> jugadors;

    // Constructors

    public Partida() {
    }

    public Partida(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
        this.enCurso = true;
    }    
    
    // Getters y Setters
    

    public Set<Jugador> getJugadors() {
        return this.jugadors;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
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
   

    public void setJugadors(Set<Jugador> jugadors) {
        this.jugadors = jugadors;
    }
}
