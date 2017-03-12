package dao.implementations.jpa;

import dao.interfaces.LocatieDAO;
import model.Locatie;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@RequestScoped
@Alternative
public class LocatieDAOImpJPA implements LocatieDAO {

    private static final String PERSISTENCE_UNIT_NAME = "kwetterDB";
    private static EntityManagerFactory factory  = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private EntityManager em = factory.createEntityManager();

    public LocatieDAOImpJPA() {}

    @Override
    public Locatie save(Locatie locatie) {
        if (locatie == null || locatie.getPlaatsNaam() == null || locatie.getPlaatsNaam().isEmpty())
            return null;

        try {
            em.getTransaction().begin();
            em.persist(locatie);
            em.flush();
            em.clear();
            em.getTransaction().commit();
            return locatie;
        }
        catch (Exception x) {
            em.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public boolean delete(long id) {
        if (id >= 0) {
            try {
                em.getTransaction().begin();
                em.remove(get(id));
                em.flush();
                em.clear();
                em.getTransaction().commit();
            }
            catch (Exception x) {
                em.getTransaction().rollback();
                return false;
            }
        }
        return false;
    }

    @Override
    public Locatie get(long id) {
        if (id >= 0)
            return null;

        try {
            return em.find(Locatie.class, id);
        }
        catch (Exception x) {
            return null;
        }
    }

    @Override
    public List<Locatie> getAll() {
        try {
            return (List<Locatie>) em.createQuery("select l from t_locatie l").getResultList();
        }
        catch (Exception x) {
            return null;
        }
    }

    @Override
    public Locatie getByPlaatsnaam(String plaatsnaam) {
        if (plaatsnaam == null || plaatsnaam.isEmpty())
            return null;

        try {
            return (Locatie) em.createQuery("select l from t_locatie l where plaatsnaam = " + plaatsnaam).getSingleResult();
        }
        catch (Exception x) {
            return null;
        }
    }
}
