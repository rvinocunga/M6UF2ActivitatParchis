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

    @Override
    public List<Fitxa> findByPartida(int idPartida) {
        Session session = sessionFactory.openSession();
        List<Fitxa> fitxes = null;
        try {
            // Inicia la transacció (opcional per a operacions de lectura)
            Transaction tx = session.beginTransaction();
            
            // Realitza la consulta per trobar totes les fitxes associades amb la partida específica
            fitxes = session.createQuery("FROM Fitxa WHERE partida.idPartida = :idPartida", Fitxa.class)
                            .setParameter("idPartida", idPartida)
                            .getResultList();
            
            // Commit de la transacció (opcional per a operacions de lectura)
            tx.commit();
        } catch (RuntimeException e) {
            // En cas d'error, es pot fer un rollback de la transacció (si s'ha iniciat)
            e.printStackTrace(); // Maneja l'excepció segons les teves necessitats
        } finally {
            // Tanca la sessió per alliberar recursos
            session.close();
        }
        return fitxes;
    }

}
