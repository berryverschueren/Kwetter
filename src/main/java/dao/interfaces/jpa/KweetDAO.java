package dao.interfaces.jpa;

import model.Kweet;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface KweetDAO extends GenericDao<Kweet> {
    List<Kweet> getMatchesByInhoud(String inhoud);
    List<Kweet> getKweetByHashtagId(long id);
    List<Kweet> getKweetsByMentionId(long id);
    List<Kweet> getKweetsByKwetteraarId(long id);
    List<Kweet> getRecenteEigenKweetsByKwetteraarId(long id);
}
