package dao.interfaces.memory;

import model.Hashtag;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface HashtagDAO {
    Hashtag save(Hashtag hashtag);
    boolean delete(long id);
    Hashtag get(long id);
    List<Hashtag> getAll();
    Hashtag getByInhoud(String inhoud);
    List<Hashtag> getMatchesByInhoud(String inhoud);
}
