package dao.implementations.jpa;

import dao.interfaces.HashtagDAO;
import model.Hashtag;

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
public class HashtagDAOImpJPA implements HashtagDAO {

    private static final String PERSISTENCE_UNIT_NAME = "kwetterDB";
    private static EntityManagerFactory factory  = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private EntityManager em = factory.createEntityManager();

    public HashtagDAOImpJPA() {}

    @Override
    public Hashtag save(Hashtag hashtag) {
        if (hashtag == null || hashtag.getInhoud() == null || hashtag.getInhoud().isEmpty())
            return null;

        try {
            em.getTransaction().begin();
            em.persist(hashtag);
            em.getTransaction().commit();
        }
        catch (Exception x) {
            em.getTransaction().rollback();
            return null;
        }

        return null;
    }

    @Override
    public boolean delete(long id) {
        if (id >= 0) {
            try {
                em.getTransaction().begin();
                Hashtag hashtag = get(id);
                em.remove(hashtag);
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
        return null;
    }

    @Override
    public Hashtag getByInhoud(String inhoud) {
        return null;
    }

    @Override
    public List<Hashtag> getMatchesByInhoud(String inhoud) {
        return null;
    }
}
