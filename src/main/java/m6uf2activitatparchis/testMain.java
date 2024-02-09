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

    public static void main(String[] args) {
        try {
            // Jugador
//            Jugador j1 = new Jugador("prueba", "blau", 11);
//            JugadorDao dao = new JugadorDaoImpl(PersistenciaConfig.getEntityManager());
//            dao.insertar(j1);

            // Partida
//            Partida p1 = new Partida(LocalDateTime.now());
//            PartidaDao parDAO = new PartidaDaoImpl(PersistenciaConfig.getEntityManager());
//            parDAO.insertar(p1);

//             Casilla
//            Casilla c1 = new Casilla("prueba", 22);
//            CasillaDao casDAO = new CasillaDaoImpl(PersistenciaConfig.getEntityManager());
//            casDAO.insertar(c1);
            
            // Fitxa
            Fitxa f1 = new Fitxa(20, true);
            FitxaDao fitDAO = new FitxaDaoImpl(PersistenciaConfig.getEntityManager());
            fitDAO.insertar(f1);
        } catch (Exception ex) {
            Logger.getLogger(testMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
