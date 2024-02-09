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
            em = Persistence.createEntityManagerFactory("parchis_PU_1").createEntityManager();

        } catch (Exception ex) {
            throw new RuntimeException("Error al crear EntityManager " + ex.getMessage());
        }
        return em;
    }
}
