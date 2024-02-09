package daoimpl;

import dao.JugadorDao;
import model.Jugador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class JugadorDaoImpl implements JugadorDao {

    private EntityManager em;

    // Constructors
    public JugadorDaoImpl() {
    }

    public JugadorDaoImpl(EntityManager em) {
        this.em = em;
    }

    // Troba un jugador per ID. 
    @Override
    public Jugador findById(int id) {
        return em.find(Jugador.class, id);
    }

    // Guarda un nou jugador a la base de dades. 
    @Override
    public void insertar(Jugador jugador) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(jugador);
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void actualizar(Jugador jugador) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(jugador);
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void eliminar(Jugador jugador) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(jugador) ? jugador : em.merge(jugador));
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<Jugador> listar() throws Exception {
        return em.createQuery("SELECT j FROM Jugador j", Jugador.class).getResultList();
    }

}
