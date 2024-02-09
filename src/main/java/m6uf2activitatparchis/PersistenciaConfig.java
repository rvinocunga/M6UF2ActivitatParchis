package m6uf2activitatparchis;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;

public class PersistenciaConfig {

    /**
     * Obte un entityManager de la Unit Persistence seleccionada
     *
     * @return
     */
    private static EntityManager em = null;

    public static EntityManager getEntityManager() {
        try {
            if (em != null) {
                return em;
            }
            em = Persistence.createEntityManagerFactory("M6UF2ActivitatParchis_PU1").createEntityManager();

        } catch (Exception ex) {
            throw new RuntimeException("Error al crear EntityManeger " + ex.getMessage());
        }
        return em;
    }
}
