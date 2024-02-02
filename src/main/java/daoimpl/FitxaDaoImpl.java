package daoimpl;

import dao.FitxaDao;
import model.Fitxa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class FitxaDaoImpl implements FitxaDao {

    private SessionFactory sessionFactory;

    // Constructor que inicialitza la sessionFactory, necessària per a crear sessions de Hibernate.
    
    public FitxaDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Troba una fitxa per ID, 
    public Fitxa findById(int id) {
        Session session = sessionFactory.openSession();
        Fitxa fitxa = session.get(Fitxa.class, id);
        session.close();
        return fitxa;
    }

    // Guarda una nova fitxa a la base de dades.
    public void save(Fitxa fitxa) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(fitxa);
        tx.commit();
        session.close();
    }

    // Actualitza l'estat o la posició d'una fitxa existent.
    @Override
    public void update(Fitxa fitxa) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(fitxa);
        tx.commit();
        session.close();
    }

    // Esborra una fitxa de la base de dades.
    @Override
    public void delete(Fitxa fitxa) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(fitxa);
        tx.commit();
        session.close();
    }

    // Retorna totes les fitxes.
    @SuppressWarnings("unchecked")
    @Override
    public List<Fitxa> findAll() {
        Session session = sessionFactory.openSession();
        List<Fitxa> fitxes = session.createQuery("from Fitxa").list();
        session.close();
        return fitxes;
    }

    // Troba totes les fitxes associades a un jugador i partida específics.
    @SuppressWarnings("unchecked")
    @Override
    public List<Fitxa> findByJugadorAndPartida(int idJugador, int idPartida) {
        Session session = sessionFactory.openSession();
        List<Fitxa> fitxes = session.createQuery("from Fitxa where jugador.idJugador = :idJugador and partida.idPartida = :idPartida")
                                    .setParameter("idJugador", idJugador)
                                    .setParameter("idPartida", idPartida)
                                    .list();
        session.close();
        return fitxes;
    }
}
