package daoimpl;

import dao.PartidaDao;
import model.Partida;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class PartidaDaoImpl implements PartidaDao {

    private SessionFactory sessionFactory;

    // Constructor: Assigna la sessionFactory per a operacions de base de dades.
    public PartidaDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Troba una partida per ID. 
    public Partida findById(int id) {
        Session session = sessionFactory.openSession();
        Partida partida = session.get(Partida.class, id);
        session.close();
        return partida;
    }

    // Guarda una nova partida. 
    @Override
    public void save(Partida partida) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(partida);
        tx.commit();
        session.close();
    }

    // Actualitza l'estat d'una partida existent.
    public void update(Partida partida) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(partida);
        tx.commit();
        session.close();
    }

    // Esborra una partida. 
    @Override
    public void delete(Partida partida) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(partida);
        tx.commit();
        session.close();
    }

    // Retorna totes les partides. 
    @SuppressWarnings("unchecked")
    @Override
    public List<Partida> findAll() {
        Session session = sessionFactory.openSession();
        List<Partida> partidas = session.createQuery("from Partida").list();
        session.close();
        return partidas;
    }

    // Retorna les partides que estan en curs. 
    @SuppressWarnings("unchecked")
    @Override
    public List<Partida> findEnCurso() {
        Session session = sessionFactory.openSession();
        List<Partida> partidas = session.createQuery("from Partida where enCurso = true").list();
        session.close();
        return partidas;
    }
}
