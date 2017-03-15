package dao.implementations.jpa;

import dao.interfaces.LocatieDAO;
import model.Locatie;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@Stateless
@Alternative
public class LocatieDAOImpJPA implements LocatieDAO {

    //private static final String PERSISTENCE_UNIT_NAME = "kwetterDB";
    //private static EntityManagerFactory factory  = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    //private EntityManager em = factory.createEntityManager();

    @PersistenceContext
    private EntityManager em;

    public LocatieDAOImpJPA() {}

    @Override
    public Locatie save(Locatie locatie) {
        if (locatie == null || locatie.getPlaatsNaam() == null || locatie.getPlaatsNaam().isEmpty())
            return null;

       // EntityTransaction et = em.getTransaction();
        try {
            if (locatie.getId() <= 0)
                em.persist(locatie);

            else
                em.merge(locatie);

            return locatie;
        }
        catch (Exception x) {
           // if (et.isActive())
           //     et.rollback();
            return null;
        }
    }

    @Override
    public boolean delete(long id) {
        if (id >= 0) {
          //  EntityTransaction et = em.getTransaction();
            try {
            //    et.begin();
                em.remove(get(id));
            //    et.commit();
                return true;
            }
            catch (Exception x) {
             //   if (et.isActive())
             //      et.rollback();
                return false;
            }
        }
        return false;
    }

    @Override
    public Locatie get(long id) {
        if (id >= 0) {
            try {
                return em.find(Locatie.class, id);
            }
            catch (Exception x) {
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Locatie> getAll() {
        try {
            return (List<Locatie>) em.createQuery("select l from Locatie l").getResultList();
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
            return (Locatie) em.createQuery("select l from Locatie l where l.plaatsnaam = '" + plaatsnaam + "'").getSingleResult();
        }
        catch (Exception x) {
            return null;
        }
    }
}
