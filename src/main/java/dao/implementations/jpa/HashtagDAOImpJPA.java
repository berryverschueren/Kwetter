package dao.implementations.jpa;

import dao.interfaces.jpa.HashtagDAO;
import model.Hashtag;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@Stateless
@Alternative
public class HashtagDAOImpJPA extends GenericDaoImpJPA<Hashtag> implements HashtagDAO {

    public HashtagDAOImpJPA() {}

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
