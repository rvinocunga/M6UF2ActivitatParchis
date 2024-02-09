package service;

import java.util.Set;

import model.Jugador;
import model.Partida;

public interface JocService {
    Partida iniciarPartida(Set<Jugador> jugadors);
	void lanzarDados(Jugador jugador);
    void moverFicha(int idFitxa, int quantitat);
    void capturarFicha(int idFitxa);
    boolean verificarCasaSegura(int posicion);
    void finalizarRecorrido(int idFitxa);
    boolean verificarVictoria(Jugador jugador);
}
