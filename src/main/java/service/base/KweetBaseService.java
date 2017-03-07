package service.base;

import dao.implementations.KweetDAOImp;
import dao.interfaces.KweetDAO;
import model.Kweet;

import java.util.List;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class KweetBaseService {
    private KweetDAO kweetDao = new KweetDAOImp();

    public List<Kweet> getKweets() {
        return kweetDao.getAll();
    }

    public Kweet getKweet(long id) {
        return kweetDao.get(id);
    }

    public List<Kweet> getMatchesByInhoud(String inhoud) {
        return kweetDao.getMatchesByInhoud(inhoud);
    }

    public List<Kweet> getKweetByHashtagId(long id) {
        return kweetDao.getKweetByHashtagId(id);
    }

    public List<Kweet> getKweetsByMentionId(long id) {
        return kweetDao.getKweetsByMentionId(id);
    }

    public List<Kweet> getRecenteKweetsByKwetteraarId(long id) {
        return kweetDao.getRecenteKweetsByKwetteraarId(id);
    }

    public boolean deleteKweet(long id) {
        return kweetDao.delete(id);
    }
}
