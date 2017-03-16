package dao.interfaces.jpa;

import model.Hashtag;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface HashtagDAO extends GenericDao<Hashtag> {
    Hashtag getByInhoud(String inhoud);
    List<Hashtag> getMatchesByInhoud(String inhoud);
}
