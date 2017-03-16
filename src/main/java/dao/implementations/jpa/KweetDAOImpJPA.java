package dao.implementations.jpa;

import dao.interfaces.KweetDAO;
import model.Kweet;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@Stateless
@Alternative
public class KweetDAOImpJPA implements KweetDAO {

    @PersistenceContext
    private EntityManager em;

    public KweetDAOImpJPA() {}

    @Override
    public Kweet save(Kweet kweet) {
        if (kweet == null || kweet.getInhoud() == null || kweet.getInhoud().isEmpty() || kweet.getEigenaar() == null)
            return null;
        try {
            if (kweet.getId() <= 0)
                em.persist(kweet);
            else
                em.merge(kweet);
            return kweet;
        }
        catch (Exception x) {
            return null;
        }
    }

    @Override
    public boolean delete(long id) {
        if (id >= 0) {
            try {
                em.remove(get(id));
                return true;
            }
            catch (Exception x) {
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
            return (List<Kweet>) em.createQuery("select k from Kweet k").getResultList();
        }
        catch (Exception x) {
            return null;
        }
    }

    @Override
    public List<Kweet> getMatchesByInhoud(String inhoud) {
        if (inhoud == null || inhoud.isEmpty())
            return null;

        return getListByQuery("select k from Kweet k where k.inhoud = '%" + inhoud + "%'");
    }

    @Override
    public List<Kweet> getKweetByHashtagId(long id) {
        if (id >= 0)
            return getListByQuery("select k from Kweet k where k.id = (select x from t_kweet_hashtag x where x.hashtag_id = " + id + ")");
        return null;
    }

    @Override
    public List<Kweet> getKweetsByMentionId(long id) {
        if (id >= 0)
            return getListByQuery("select k from Kweet k where k.id = (select x from t_kweet_kwetteraar_mention x where x.kwetteraar_id = " + id + ")");
        return null;
    }

    @Override
    public List<Kweet> getKweetsByKwetteraarId(long id) {
        if (id >= 0)
            return getListByQuery("select k from Kweet k where k.eigenaar_id = " + id);
        return null;
    }

    @Override
    public List<Kweet> getRecenteEigenKweetsByKwetteraarId(long id) {
        if (id >= 0)
            return getListByQuery("select k from Kweet k where k.eigenaar_id = " + id + " and k.datum between " + LocalDateTime.now() + " and " + LocalDateTime.now().minusDays(10) + " order by k.datum desc");
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
