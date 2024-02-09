package daoimpl;

import dao.CasillaDao;
import model.Casilla;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import javax.persistence.EntityNotFoundException;

public class CasillaDaoImpl implements CasillaDao {

    private EntityManager em;

    // Constructors
    public CasillaDaoImpl() {
    }

    public CasillaDaoImpl(EntityManager em) {
        this.em = em;
    }

    // Encuentra una casilla por el ID proporcionado.
    @Override
    public Casilla findById(int id) {
        return em.find(Casilla.class, id);
    }

    // Guarda una nueva casilla en la base de datos.
    @Override
    public void insertar(Casilla casilla) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(casilla);
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    // Actualiza una casilla existente, como cambiar su tipo o asociación con una partida.
    @Override
    public void actualizar(Casilla casilla) {
        try {
            em.getTransaction().begin();
            em.merge(casilla);
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    // Elimina una casilla de la base de datos.
    @Override
    public void eliminar(Casilla casilla) {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(casilla) ? casilla : em.merge(casilla));
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    // Retorna todas las casillas.
    @SuppressWarnings("unchecked")
    @Override
    public List<Casilla> listar() {
        return em.createQuery("from Casilla").getResultList();
    }

    // Retorna las casillas asociadas a una partida específica.
    @SuppressWarnings("unchecked")
    @Override
    public List<Casilla> listarPorPartida(int idPartida) {
        return em.createQuery("select c from Casilla c where c.partida.idPartida = :idPartida")
                .setParameter("idPartida", idPartida)
                .getResultList();
    }
}
