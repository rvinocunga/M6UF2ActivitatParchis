package m6uf2activitatparchis;

import dao.CasillaDao;
import dao.FitxaDao;
import dao.JugadorDao;
import dao.PartidaDao;
import daoimpl.CasillaDaoImpl;
import daoimpl.FitxaDaoImpl;
import daoimpl.JugadorDaoImpl;
import daoimpl.PartidaDaoImpl;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Casilla;
import model.Fitxa;
import model.Jugador;
import model.Partida;

public class testMain {

    public static void main(String[] args) throws Exception{
        Partida partida = new Partida(LocalDateTime.now());
        PartidaDao DAOPartida = new PartidaDaoImpl(PersistenciaConfig.getEntityManager());
        DAOPartida.insertar(partida);
        
        Jugador j1;
        JugadorDao DAOJugador = new JugadorDaoImpl(PersistenciaConfig.getEntityManager());
        j1 = DAOJugador.findById(1);
        
        Fitxa f1 = new Fitxa(0, true, j1, partida);
        FitxaDao DAOFitxa = new FitxaDaoImpl(PersistenciaConfig.getEntityManager());
        DAOFitxa.insertar(f1);
    }

}
