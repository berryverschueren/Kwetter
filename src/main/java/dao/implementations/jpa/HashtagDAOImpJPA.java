package dao.implementations.jpa;

import dao.interfaces.HashtagDAO;
import model.Hashtag;

import javax.annotation.PreDestroy;
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
public class HashtagDAOImpJPA implements HashtagDAO {

    //private static final String PERSISTENCE_UNIT_NAME = "kwetterDB";
    //private static EntityManagerFactory factory  = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    //private EntityManager em = factory.createEntityManager();

    @PersistenceContext
    private EntityManager em;

    public HashtagDAOImpJPA() {}

    @Override
    public Hashtag save(Hashtag hashtag) {
        if (hashtag == null || hashtag.getInhoud() == null || hashtag.getInhoud().isEmpty())
            return null;

        //EntityTransaction et = em.getTransaction();
        try {
            if (hashtag.getId() <= 0)
                em.persist(hashtag);

            else
                em.merge(hashtag);

            return hashtag;
        }
        catch (Exception x) {
            //if (et.isActive())
            //    et.rollback();
            return null;
        }
    }

    @Override
    public boolean delete(long id) {
        if (id >= 0) {
           // EntityTransaction et = em.getTransaction();
            try {
              //  et.begin();
                em.remove(get(id));
              //  et.commit();
                return true;
            }
            catch (Exception x) {
              //  if (et.isActive())
              //      et.rollback();
                return false;
            }
        }
        return false;
    }

    @Override
    public Hashtag get(long id) {
        if (id >= 0) {
            try {
                return em.find(Hashtag.class, id);
            }
            catch (Exception x) {
                return null;
            }
        }

        return null;
    }

    @Override
    public List<Hashtag> getAll() {
        try {
            return (List<Hashtag>) em.createQuery("select h from Hashtag h").getResultList();
        }
        catch (Exception x) {
            return null;
        }
    }

    @Override
    public Hashtag getByInhoud(String inhoud) {
        if (inhoud == null || inhoud.isEmpty())
            return null;

        try {
            return (Hashtag) em.createQuery("select h from Hashtag h where h.inhoud = '" + inhoud + "'").getSingleResult();
        }
        catch (Exception x) {
            return null;
        }
    }

    @Override
    public List<Hashtag> getMatchesByInhoud(String inhoud) {
        if (inhoud == null || inhoud.isEmpty())
            return null;

        try {
            return (List<Hashtag>) em.createQuery("select h from Hashtag h where h.inhoud like '%" + inhoud + "%'").getResultList();
        }
        catch (Exception x) {
            return null;
        }
    }
}
