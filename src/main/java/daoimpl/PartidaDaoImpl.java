package daoimpl;

import dao.PartidaDao;
import model.Partida;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.EntityNotFoundException;

public class PartidaDaoImpl implements PartidaDao {

    private EntityManager em;

    // Constructors
    public PartidaDaoImpl() {
    }

    public PartidaDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Partida findById(int id) {
        return em.find(Partida.class, id);
    }

    @Override
    public void insertar(Partida partida) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(partida);
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void actualizar(Partida partida) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(partida);
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void eliminar(Partida partida) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(partida) ? partida : em.merge(partida));
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<Partida> listarTodas() {
        return em.createQuery("SELECT p FROM Partida p", Partida.class).getResultList();
    }

    @Override
    public List<Partida> listarEnCurso() {
        return em.createQuery("SELECT p FROM Partida p WHERE p.estado = 'EN_CURSO'", Partida.class).getResultList();
    }
}
