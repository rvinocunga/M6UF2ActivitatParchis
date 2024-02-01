package daoimpl;


import dao.CasillaDao;
import model.Casilla;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class CasillaDaoImpl implements CasillaDao {

    private SessionFactory sessionFactory;

    public CasillaDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Casilla findById(int id) {
        Session session = sessionFactory.openSession();
        Casilla casilla = session.get(Casilla.class, id);
        session.close();
        return casilla;
    }

    @Override
    public void save(Casilla casilla) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(casilla);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Casilla casilla) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(casilla);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Casilla casilla) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(casilla);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Casilla> findAll() {
        Session session = sessionFactory.openSession();
        List<Casilla> casillas = session.createQuery("from Casilla").list();
        session.close();
        return casillas;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Casilla> findByPartida(int idPartida) {
        Session session = sessionFactory.openSession();
        List<Casilla> casillas = session.createQuery("from Casilla where partida.idPartida = :idPartida")
                                        .setParameter("idPartida", idPartida)
                                        .list();
        session.close();
        return casillas;
    }
}
