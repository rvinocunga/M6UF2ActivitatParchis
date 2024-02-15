package daoimpl;

import dao.FitxaDao;
import model.Fitxa;
import org.hibernate.Session;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class FitxaDaoImpl implements FitxaDao {

    private EntityManager em;

    // Constructor
    public FitxaDaoImpl() {
    }

    public FitxaDaoImpl(EntityManager em) {
        this.em = em;
    }

    // Troba una fitxa per ID, 
    @Override
    public Fitxa findById(int id) {
        return em.find(Fitxa.class, id);
    }

    // Guarda un nou jugador a la base de dades. 
    @Override
    public void insertar(Fitxa fitxa) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(fitxa);
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void actualizar(Fitxa fitxa) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(fitxa);
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void eliminar(Fitxa fitxa) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(fitxa) ? fitxa : em.merge(fitxa));
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<Fitxa> listar() throws Exception {
        return em.createQuery("SELECT f FROM Fitxa f", Fitxa.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Fitxa> listarFichaJugadorEnPartida(int idJugador, int idPartida) {
        return em.createQuery("SELECT f FROM Fitxa f WHERE f.jugador.idJugador = :idJugador AND f.partida.idPartida = :idPartida", Fitxa.class)
                .setParameter("idJugador", idJugador)
                .setParameter("idPartida", idPartida)
                .getResultList();
    }

    @Override
    public List<Fitxa> listarPorPosicionYPartida(int posicio, int idPartida) throws Exception {
        return em.createQuery("SELECT f FROM Fitxa f WHERE f.posicio = :posicio AND f.partida.idPartida = :idPartida", Fitxa.class)
                .setParameter("posicio", posicio)
                .setParameter("idPartida", idPartida)
                .getResultList();
    }

}
