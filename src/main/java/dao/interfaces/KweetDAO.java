package dao.interfaces;

import model.Kweet;
import model.Kwetteraar;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface KweetDAO {
    Kweet save(Kweet kweet);
    boolean delete(long id);
    Kweet get(long id);
    List<Kweet> getAll();
    List<Kweet> getMatchesByInhoud(String inhoud);
    List<Kweet> getKweetByHashtagId(long id);
    List<Kweet> getKweetsByMentionId(long id);
    List<Kweet> getKweetsByKwetteraarId(long id);
    List<Kweet> getRecenteEigenKweetsByKwetteraarId(long id);
}
