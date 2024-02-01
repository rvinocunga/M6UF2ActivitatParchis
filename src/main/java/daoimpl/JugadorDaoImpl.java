package daoimpl;


import dao.JugadorDao;
import model.Jugador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class JugadorDaoImpl implements JugadorDao {

    private final SessionFactory sessionFactory;

    public JugadorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Jugador findById(int id) {
        Session session = sessionFactory.openSession();
        Jugador jugador = session.get(Jugador.class, id);
        session.close();
        return jugador;
    }

    @Override
    public void save(Jugador jugador) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(jugador);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Jugador jugador) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(jugador);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Jugador jugador) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(jugador);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Jugador> findAll() {
        Session session = sessionFactory.openSession();
        List<Jugador> list = session.createQuery("from Jugador").list();
        session.close();
        return list;
    }
}
