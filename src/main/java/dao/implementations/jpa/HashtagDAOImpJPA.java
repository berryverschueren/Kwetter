package dao.implementations.jpa;

import dao.interfaces.jpa.HashtagDAO;
import model.Hashtag;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;
import logger.Logger;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@Stateless
@Default
public class HashtagDAOImpJPA extends GenericDaoImpJPA<Hashtag> implements HashtagDAO {

    public HashtagDAOImpJPA() {
        // Empty constructor for dependency injection purposes.
    }

    @Override
    public Hashtag getByInhoud(String inhoud) {
        if (inhoud == null || inhoud.isEmpty())
            return null;

        try {
            return (Hashtag) em.createQuery("select h from Hashtag h where h.inhoud = '" + inhoud + "'").getSingleResult();
        }
        catch (Exception x) {
            Logger.log(x);
            return null;
        }
    }

    @Override
    public List<Hashtag> getMatchesByInhoud(String inhoud) {
        if (inhoud == null || inhoud.isEmpty())
            return new ArrayList<>();

        try {
            return (List<Hashtag>) em.createQuery("select h from Hashtag h where h.inhoud like '%" + inhoud + "%'").getResultList();
        }
        catch (Exception x) {
            Logger.log(x);
            return new ArrayList<>();
        }
    }
}
