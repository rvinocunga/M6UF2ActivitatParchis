package daoimpl;

import dao.CasillaDao;
import model.Casilla;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class CasillaDaoImpl implements CasillaDao {

    private SessionFactory sessionFactory;

    // Constructor que requereix una sessionFactory per a crear i gestionar sessions de Hibernate.
    public CasillaDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Troba una casilla per l'ID proporcionat.
    @Override
    public Casilla findById(int id) {
        Session session = sessionFactory.openSession();
        Casilla casilla = session.get(Casilla.class, id);
        session.close();  
        return casilla;
    }

    // Guarda una casilla nova a la base de dades. 
    public void save(Casilla casilla) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();  // Inicia una transacció.
        session.save(casilla);  // Guarda l'objecte casilla.
        tx.commit();  
        session.close(); 
    }

    // Actualitza una casilla existent, com ara canviar el seu tipus o associació amb una partida.
    @Override
    public void update(Casilla casilla) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(casilla);
        tx.commit();
        session.close();
    }

    // Esborra una casilla de la base de dades.
    public void delete(Casilla casilla) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(casilla);
        tx.commit();
        session.close();
    }

    // Retorna totes les caselles.
    @SuppressWarnings("unchecked")
    @Override
    public List<Casilla> findAll() {
        Session session = sessionFactory.openSession();
        List<Casilla> casillas = session.createQuery("from Casilla").list();
        session.close();
        return casillas;
    }

    // Retorna les caselles associades a una partida específica.
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
