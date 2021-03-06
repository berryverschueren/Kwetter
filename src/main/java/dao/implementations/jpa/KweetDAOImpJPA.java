package dao.implementations.jpa;

import dao.interfaces.jpa.KweetDAO;
import model.Kweet;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logger.Logger;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@Stateless
@Default
public class KweetDAOImpJPA extends GenericDaoImpJPA<Kweet> implements KweetDAO {

    public KweetDAOImpJPA() {
        // Empty constructor for dependency injection purposes.
    }

    @Override
    public List<Kweet> getMatchesByInhoud(String inhoud) {
        if (inhoud == null)
            return new ArrayList<>();

        return getListByQuery("select k from Kweet k where k.inhoud LIKE '%" + inhoud + "%'");
    }

    @Override
    public List<Kweet> getKweetByHashtagId(long id) {
        if (id >= 0)
            return getListByQuery("select k from Kweet k where k.id = (select x.kweet_hashtag_id from t_kweet_hashtag x where x.hashtag_hashtag_id = " + id + ")");
        return new ArrayList<>();
    }

    @Override
    public List<Kweet> getKweetsByMentionId(long id) {
        if (id >= 0) {
            //return getListByQuery("select k from Kweet k where k.id = (select x.kweet_mention_id from t_kweet_kwetteraar_mention x where x.kwetteraar_mention_id = " + id + ")");
            return getListByQuery("select k from Kweet k where k.inhoud LIKE '%@ (select kwetteraar.profielNaam from t_kwetteraar kwetteraar where kwetteraar.id = " + id + ") %'");
        }
        return new ArrayList<>();
    }

    @Override
    public List<Kweet> getKweetsByKwetteraarId(long id) {
        if (id >= 0)
            return getListByQuery("select k from Kweet k where k.eigenaar.id = " + id);
        return new ArrayList<>();
    }

    @Override
    public List<Kweet> getRecenteEigenKweetsByKwetteraarId(long id) {
        if (id >= 0)
            return getListByQuery("select k from Kweet k where k.eigenaar.id = " + id + " order by k.datum desc");
        return new ArrayList<>();
    }

    public List<Kweet> getListByQuery(String query) {
        if (query == null || query.isEmpty())
            return new ArrayList<>();

        try {
            return (List<Kweet>) em.createQuery(query).getResultList();
        }
        catch (Exception x) {
            Logger.log(x);
            return new ArrayList<>();
        }
    }
}
