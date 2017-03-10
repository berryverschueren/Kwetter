package dao.implementations.jpa;

import dao.interfaces.KweetDAO;
import model.Kweet;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@RequestScoped
@Alternative
public class KweetDAOImpJPA implements KweetDAO {

    private static final String PERSISTENCE_UNIT_NAME = "kwetterDB";
    private static EntityManagerFactory factory  = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private EntityManager em = factory.createEntityManager();

    public KweetDAOImpJPA() {}

    @Override
    public Kweet save(Kweet kweet) {
        if (kweet == null || kweet.getInhoud() == null || kweet.getInhoud().isEmpty() || kweet.getEigenaar() == null)
            return null;

        try {
            em.getTransaction().begin();
            em.persist(kweet);
            em.getTransaction().commit();
            return kweet;
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
                Kweet kweet = get(id);
                em.remove(kweet);
                em.getTransaction().commit();
                return true;
            }
            catch (Exception x) {
                em.getTransaction().rollback();
                return false;
            }
        }
        return false;
    }

    @Override
    public Kweet get(long id) {
        if (id >= 0) {
            try {
                return em.find(Kweet.class, id);
            }
            catch (Exception x) {
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Kweet> getAll() {
        try {
            return (List<Kweet>) em.createQuery("select k from t_kweet k").getResultList();
        }
        catch (Exception x) {
            return null;
        }
    }

    @Override
    public List<Kweet> getMatchesByInhoud(String inhoud) {
        if (inhoud == null || inhoud.isEmpty())
            return null;

        return getListByQuery("select k from t_kweet k where inhoud = %" + inhoud + "%");
    }

    @Override
    public List<Kweet> getKweetByHashtagId(long id) {
        if (id >= 0)
            return getListByQuery("select k from t_kweet k where hashtag_id = " + id);
        return null;
    }

    @Override
    public List<Kweet> getKweetsByMentionId(long id) {
        if (id >= 0)
            return getListByQuery("select k from t_kweet k where mention_id = " + id);
        return null;
    }

    @Override
    public List<Kweet> getKweetsByKwetteraarId(long id) {
        if (id >= 0)
            return getListByQuery("select k from t_kweet k where eigenaar_id = " + id);
        return null;
    }

    @Override
    public List<Kweet> getRecenteEigenKweetsByKwetteraarId(long id) {
        if (id >= 0)
            return getListByQuery("select top 10 k from t_kweet k where eigenaar_id = " + id + " and datum between " + LocalDateTime.now() + " and " + LocalDateTime.now().minusDays(10) + " order by datum desc");
        return null;
    }

    public List<Kweet> getListByQuery(String query) {
        if (query == null || query.isEmpty())
            return null;

        try {
            return (List<Kweet>) em.createQuery(query).getResultList();
        }
        catch (Exception x) {
            return null;
        }
    }
}
