package dao;

import model.Hashtag;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface HashtagDAO {
    HashtagDAOImp save(HashtagDAOImp hashtag);
    boolean delete(long id);
    HashtagDAOImp get(long id);
    List<HashtagDAOImp> getAll();
}
