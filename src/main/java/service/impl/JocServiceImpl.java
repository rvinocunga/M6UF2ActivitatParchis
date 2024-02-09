package service.impl;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

import dao.*;
import model.*;
import service.JocService;


public class JocServiceImpl implements JocService {

    private JugadorDao jugadorDao;
    private PartidaDao partidaDao;
    private CasillaDao casillaDao;
    private FitxaDao fitxaDao;
    private Random random = new Random();
    private Map<Integer, Integer> tiradesConsecutivesPerJugador = new HashMap<>();

    // Constructor amb injecció de dependències dels DAOs
    public JocServiceImpl(JugadorDao jugadorDao, PartidaDao partidaDao, CasillaDao casillaDao, FitxaDao fitxaDao) {
        this.jugadorDao = jugadorDao;
        this.partidaDao = partidaDao;
        this.casillaDao = casillaDao;
        this.fitxaDao = fitxaDao;
    }

    @Override
    public Partida iniciarPartida(Set<Jugador> jugadors) {
        // Crear nova partida
        Partida partida = new Partida();
        partida.setFechaInicio(LocalDateTime.now());
        partida.setEnCurso(true);

        // Assignar cada jugador a la partida i inicialitzar les seves fitxes
        for (Jugador jugador : jugadors) {
            jugador.getFitxes().forEach(fitxa -> {
                fitxa.setPartida(partida); // Assignar partida a la fitxa
                fitxa.setPosicio(0); // Posició inicial, ajusta segons la teva lògica
                fitxa.setActiva(false); // Les fitxes comencen fora del joc, ajusta segons la teva lògica
                fitxaDao.save(fitxa); // Guardar fitxa
            });
            jugadorDao.save(jugador); // Actualitzar jugador amb les fitxes assignades
        }

        partida.setJugadors(jugadors); // Assignar jugadors a la partida
        partidaDao.save(partida); // Guardar partida

        return partida;
    }

    @Override
    public void lanzarDados(Jugador jugador) {
        int idJugador = jugador.getIdJugador();
        int[] resultats = {random.nextInt(6) + 1, random.nextInt(6) + 1};

        // Comprova si els dos daus tenen el mateix valor
        if (resultats[0] == resultats[1]) {
            // Incrementa el nombre de tirades consecutives
            tiradesConsecutivesPerJugador.merge(idJugador, 1, Integer::sum);

            // Comprova si el jugador ha aconseguit tres tirades consecutives amb parells
            if (tiradesConsecutivesPerJugador.get(idJugador) >= 3) {
                // Implementa la lògica per retornar la fitxa a la casella de sortida
                // i reinicia el comptador de tirades consecutives
                tiradesConsecutivesPerJugador.put(idJugador, 0);
                System.out.println("El jugador " + idJugador + " ha fet tres tirades consecutives de parells. La fitxa retorna a la casella de sortida.");
            } else {
                // Implementa la lògica per una tirada extra aquí
                System.out.println("El jugador " + idJugador + " ha tirat un parell i té una altra tirada.");
            }
        } else {
            // Si els daus no són parells, reinicia el comptador de tirades consecutives
            tiradesConsecutivesPerJugador.put(idJugador, 0);
        }

        // Implementa la lògica per moure la fitxa segons el resultat dels daus
        System.out.println("Resultat dels daus per al jugador " + idJugador + ": " + resultats[0] + ", " + resultats[1]);
    }

    @Override
    public void moverFicha(int idFitxa, int quantitat) {
        // Implementació de lògica per moure una fitxa una certa quantitat de caselles
    }

    @Override
    public void capturarFicha(int idFitxa) {
        // Implementació de lògica per capturar una fitxa enemiga i retornar-la a la base
    }

    @Override
    public boolean verificarCasaSegura(int posicion) {
        // Implementació per verificar si una fitxa està en una casa segura
        return false;
    }

    @Override
    public void finalizarRecorrido(int idFitxa) {
        // Implementació de lògica per finalitzar el recorregut d'una fitxa
    }

    @Override
    public boolean verificarVictoria(Jugador jugador) {
        // Implementació de lògica per comprovar si un jugador ha guanyat
        return false;
    }
}
