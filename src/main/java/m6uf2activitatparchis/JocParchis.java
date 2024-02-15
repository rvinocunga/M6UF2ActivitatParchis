package m6uf2activitatparchis;

import dao.*;
import daoimpl.*;
import model.*;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class JocParchis {

    private static Random random = new Random();
    private static final Set<Integer> CASILLES_SEGURES = Collections.unmodifiableSet(new HashSet<Integer>() {
        {
            add(5);
            add(12);
            add(19);
            add(26);
            add(33);
            add(40);
            add(47);
            add(54);
            add(61);
            add(68);
            add(75);
            add(82);
            add(89);
            add(96);
        }
    }
    );

    public static void iniciarPartida(EntityManager em) {

        try {
            em.getTransaction().begin();

            // recuperar o crear jugadores
            Jugador j1 = em.find(Jugador.class, 1);
            Jugador j2 = em.find(Jugador.class, 2);
            Jugador j3 = em.find(Jugador.class, 3);
            Jugador j4 = em.find(Jugador.class, 4);

            // en el constructor tiene enCurso en true
            Partida partida = new Partida(LocalDateTime.now());

            em.persist(partida);

            // en el caso de que se creen nuevos jugadores
            /*
            Jugador j1 = new Jugador(etc....);
            Jugador j2 = new Jugador(etc....);
            Jugador j3 = new Jugador(etc....);
            Jugador j4 = new Jugador(etc....);
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.persist(j4);
             */
            em.getTransaction().commit();

            List<Jugador> jugadors = Arrays.asList(j1, j2, j3, j4);

            partida = em.find(Partida.class, partida.getIdPartida());

            // crear fichas e insertar
            FitxaDao DAOFitxa = new FitxaDaoImpl(em);
            for (Jugador jugador : jugadors) {
                for (int i = 0; i < 4; i++) {
                    Fitxa fitxa = new Fitxa(0, false, jugador, partida);
                    em.persist(fitxa);
                    DAOFitxa.insertar(fitxa);
                }
            }

            //em.getTransaction().commit();
            System.out.println("Partida iniciada amb 4 jugadors!");
        } catch (Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    public static void retornarFitxaInicio(Jugador jugador, EntityManager em) {
        // Suposem que 0 és la posició d'inici per a totes les fitxes
        int posicioDeSortida = 0;

        // Obtenir totes les fitxes actives del jugador que no estan a la posició de sortida
        Set<Fitxa> fitxesNoAInicio = jugador.getFitxes().stream()
                .filter(f -> f.getPosicio() != posicioDeSortida && f.isActiva())
                .collect(Collectors.toSet());

        if (!fitxesNoAInicio.isEmpty()) {
            // Seleccionar la primera fitxa per retornar-la a l'inici (podries implementar una lògica específica per seleccionar quina fitxa retornar)
            Fitxa fitxaARetornar = fitxesNoAInicio.iterator().next();
            fitxaARetornar.setPosicio(posicioDeSortida);

            em.merge(fitxaARetornar);
            System.out.println("La fitxa " + fitxaARetornar.getIdFitxa() + " del jugador " + jugador.getNom() + " retorna a la posició d'inici.");
        } else {
            System.out.println("No hi ha fitxes per retornar a l'inici per al jugador " + jugador.getNom());
        }
    }

    public static void lanzarDados(int idJugador, EntityManager em) {
        try {
            em.getTransaction().begin();
            JugadorDao jugadorDao = new JugadorDaoImpl(em);
            Jugador jugador = jugadorDao.findById(idJugador);
            if (jugador == null) {
                System.out.println("Jugador no trobat amb ID: " + idJugador);
                return;
            }

            int contadorDobles = 0;
            boolean continuarTirant = true;

            while (continuarTirant) {
                int dado1 = random.nextInt(6) + 1;
                int dado2 = random.nextInt(6) + 1;
                System.out.println("El jugador " + jugador.getNom() + " ha tirat els daus: " + dado1 + ", " + dado2);

                // se mueve la ficha
                moverFicha(idJugador, (dado1 + dado2), em);

                if (dado1 == dado2) {
                    contadorDobles++;
                    System.out.println("Dobles! El jugador pot moure una fitxa al tauler o tirar de nou.");
                    // Aquesta és la lògica per moure una fitxa al tauler, simplificada:
                    if (contadorDobles < 3) {
                        entradaAlTablero(jugador, em);
                        continuarTirant = true; // Permet al jugador tirar de nou
                    } else {
                        System.out.println("Tres dobles consecutius! Una fitxa retorna a la casella de sortida.");
                        // Aquesta és la lògica per retornar una fitxa a la casella de sortida, simplificada:
                        retornarFitxaInicio(jugador, em);
                        continuarTirant = false;
                    }
                } else {
                    continuarTirant = false; // El jugador no pot tirar de nou
                }
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

    }

    public static void moverFicha(int idFitxa, int quantitat, EntityManager em) {
        try {
            em.getTransaction().begin();

            FitxaDao fitxaDao = new FitxaDaoImpl(em);
            Fitxa fitxa = fitxaDao.findById(idFitxa);
            if (fitxa == null) {
                System.out.println("Fitxa no trobada amb ID: " + idFitxa);
                return;
            }

            int novaPosicio = fitxa.getPosicio() + quantitat;

            // Ajustar per no superar el límit del tauler si n'hi ha
            if (novaPosicio > 100) { // Suposem que 100 és el límit del tauler
                novaPosicio = 100;
            }

            // Comprovar si la nova posició és una casella segura
            boolean esSegura = CASILLES_SEGURES.contains(novaPosicio);

            // Si la casella no és segura, verificar si hi ha fitxes rivals
            if (!esSegura) {
                List<Fitxa> fitxesEnLaCasella = fitxaDao.listarPorPosicionYPartida(novaPosicio, fitxa.getPartida().getIdPartida());
                for (Fitxa fitxaRival : fitxesEnLaCasella) {
                    if (fitxaRival.getIdFitxa() == (fitxa.getIdFitxa())) {
                        // La fitxa rival es retorna a la posició d'inici
                        fitxaRival.setPosicio(0); // Suposem que 0 és la posició d'inici
                        fitxaDao.actualizar(fitxaRival);
                        System.out.println("Fitxa rival amb ID " + fitxaRival.getIdFitxa() + " retornada a l'inici.");
                    }
                }
            }

            // Actualitzar la posició de la fitxa
            fitxa.setPosicio(novaPosicio);
            fitxaDao.actualizar(fitxa);
            System.out.println("La fitxa amb ID " + idFitxa + " s'ha mogut a la posició " + novaPosicio);
            verificarVictoria(em);

            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    public static void capturarFicha(int idFitxaCapturadora, EntityManager em) {
        try {
            em.getTransaction().begin();

            FitxaDao fitxaDao = new FitxaDaoImpl(em);
            Fitxa fitxaCapturadora = fitxaDao.findById(idFitxaCapturadora);
            if (fitxaCapturadora == null) {
                System.out.println("Fitxa capturadora no trobada amb ID: " + idFitxaCapturadora);
                return;
            }

            List<Fitxa> fitxesEnPosicio = fitxaDao.listarPorPosicionYPartida(idFitxaCapturadora, idFitxaCapturadora);
            for (Fitxa fitxa : fitxesEnPosicio) {
                if (fitxa.getIdFitxa() != idFitxaCapturadora && fitxa.getPartida().getIdPartida() == fitxaCapturadora.getPartida().getIdPartida()) {
                    // Moure la fitxa capturada de tornada a l'àrea d'inici
                    fitxa.setPosicio(0);
                    fitxaDao.actualizar(fitxa);
                    System.out.println("La fitxa amb ID " + fitxa.getIdFitxa() + " ha estat capturada i enviada de tornada a l'inici.");
                }
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        }
    }

    private static void entradaAlTablero(Jugador jugador, EntityManager em) {
        List<Fitxa> fitxes = jugador.getFitxes().stream().filter(f -> f.getPosicio() == 0).collect(Collectors.toList());
        if (!fitxes.isEmpty()) {
            Fitxa fitxaPerEntrar = fitxes.get(0); // Agafem la primera fitxa que estigui a l'àrea d'inici
            fitxaPerEntrar.setPosicio(1); // La posició 1 representa la primera casella del tauler
            em.merge(fitxaPerEntrar);
            System.out.println("Fitxa " + fitxaPerEntrar.getIdFitxa() + " de " + jugador.getNom() + " entra al tauler.");
        } else {
            System.out.println("Totes les fitxes de " + jugador.getNom() + " ja estan en joc.");
        }
    }

    public static boolean verificarCasaSegura(int posicion) {
        // Defineix les posicions de les cases segures
        Set<Integer> posicionsSegures = new HashSet<>(Arrays.asList(5, 12, 17, 22, 29, 34, 39, 46, 51, 56, 63, 68, 73, 80, 85, 90, 97)); // Exemple de posicions segures

        // Comprova si la posició actual és una posició segura
        return posicionsSegures.contains(posicion);
    }

    public static void finalizarRecorrido(int idFitxa, int quantitat) {
        EntityManager em = PersistenciaConfig.getEntityManager();
        try {
            em.getTransaction().begin();

            FitxaDao fitxaDao = new FitxaDaoImpl(em);
            Fitxa fitxa = fitxaDao.findById(idFitxa);
            if (fitxa == null) {
                System.out.println("Fitxa no trobada amb ID: " + idFitxa);
                return;
            }

            // Suposem que la longitud total del recorregut fins a la casella final és de 76 espais
            // i que la posició final desitjada de cada fitxa és el valor 76.
            final int POSICIO_FINAL = 76;
            int novaPosicio = fitxa.getPosicio() + quantitat;

            if (novaPosicio > POSICIO_FINAL) {
                System.out.println("La fitxa amb ID " + idFitxa + " no pot moure's per sobrepassar la casella final.");
            } else if (novaPosicio == POSICIO_FINAL) {
                fitxa.setPosicio(novaPosicio);
                fitxaDao.actualizar(fitxa);
                System.out.println("La fitxa amb ID " + idFitxa + " ha completat el seu recorregut i arriba a la casella final.");
            } else {
                System.out.println("La fitxa amb ID " + idFitxa + " encara no ha completat el seu recorregut.");
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public static void verificarVictoria(EntityManager em) {
        try {
            final int POSICIO_FINALITZACIO = 76; // Suposem que aquesta és la posició de finalització

            // Comença una nova transacció
            em.getTransaction().begin();

            // Obtenim la llista de jugadors
            List<Jugador> jugadors = em.createQuery("SELECT j FROM Jugador j", Jugador.class).getResultList();

            for (Jugador jugador : jugadors) {
                // Comprova si totes les fitxes del jugador estan en la posició final
                long count = em.createQuery("SELECT COUNT(f) FROM Fitxa f WHERE f.jugador.idJugador = :idJugador AND f.posicio = :posicioFinal", Long.class)
                        .setParameter("idJugador", jugador.getIdJugador())
                        .setParameter("posicioFinal", POSICIO_FINALITZACIO)
                        .getSingleResult();

                if (count == 4) { // Suposant que cada jugador té 4 fitxes
                    System.out.println("El jugador " + jugador.getNom() + " ha guanyat la partida!");

                    // Actualitzar les estadístiques del jugador
                    jugador.setVictories(jugador.getVictories() + 1);

                    // Obté la partida en curs
                    Partida partidaActual = em.createQuery("SELECT p FROM Partida p WHERE p.enCurso = true", Partida.class)
                            .getSingleResult();

                    // Actualitza la partida amb la informació del guanyador i la data de finalització
                    partidaActual.setEnCurso(false);
                    partidaActual.setFechaFin(LocalDateTime.now());
                    partidaActual.setGanador(jugador);

                    // Persisteix els canvis
                    em.merge(jugador);
                    em.merge(partidaActual);

                    // Confirma la transacció
                    em.getTransaction().commit();

                    return; // Sortir de la funció després d'haver trobat un guanyador
                }
            }

            // Si no hi ha guanyador, no facis cap canvi i tanca la transacció
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Cap jugador ha guanyat encara.");

        } catch (Exception ex) {
            // En cas d'error, reverteix qualsevol canvi fet durant la transacció
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            // Tanca l'EntityManager si encara està obert
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public static void main(String[] args) {
        EntityManager em = PersistenciaConfig.getEntityManager();
        boolean guanyador = true;
        int turno = 1;
        iniciarPartida(em);

        /*
        try {
            iniciarPartida(em);

            do {
                lanzarDados(1, em); // Simula una tirada de daus per un jugador amb ID 1 ROGER
                //capturarFicha(3, em);
                //verificarVictoria(em); // Aquesta línia comprova la victòria després de les accions de joc
            } while (guanyador);

        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
         */
    }
}
