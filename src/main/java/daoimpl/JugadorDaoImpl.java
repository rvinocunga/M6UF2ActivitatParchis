package daoimpl;

import dao.JugadorDao;
import model.Jugador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class JugadorDaoImpl implements JugadorDao {

    private SessionFactory sessionFactory;

    // Constructor: necessita una sessionFactory per crear sessions.
    public JugadorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Troba un jugador per ID. 
    @Override
    public Jugador findById(int id) {
        Session session = sessionFactory.openSession();
        Jugador jugador = session.get(Jugador.class, id);
        session.close();
        return jugador;
    }

    // Guarda un nou jugador a la base de dades. 
    @Override
    public void save(Jugador jugador) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(jugador);
        tx.commit();
        session.close();
    }

    // Actualitza un jugador existent. 
    public void update(Jugador jugador) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(jugador);
        tx.commit();
        session.close();
    }

    // Esborra un jugador de la base de dades.
    @Override
    public void delete(Jugador jugador) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(jugador);
        tx.commit();
        session.close();
    }

    // Retorna tots els jugadors. 
    @SuppressWarnings("unchecked")
    @Override
    public List<Jugador> findAll() {
        Session session = sessionFactory.openSession();
        List<Jugador> jugadores = session.createQuery("from Jugador").list();
        session.close();
        return jugadores;
    }
}
