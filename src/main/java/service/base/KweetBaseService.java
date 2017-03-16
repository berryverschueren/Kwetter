package service.base;

import dao.interfaces.jpa.KweetDAO;
import model.Kweet;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class KweetBaseService {
    private KweetDAO kweetDao;

    @Inject
    public KweetBaseService (KweetDAO kd) {
        kweetDao = kd;
    }

    public List<Kweet> getKweets() {
        return kweetDao.getAll();
    }

    public Kweet getKweet(long id) {
        return kweetDao.find(id);
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

    public List<Kweet> getRecenteEigenKweetsByKwetteraarId(long id) {
        return kweetDao.getRecenteEigenKweetsByKwetteraarId(id);
    }

    public void deleteKweet(long id) {
        kweetDao.delete(id);
    }

    public Kweet saveKweet(Kweet kweet) {
        return kweetDao.create(kweet);
    }

    public Kweet updateKweet(Kweet kweet) {
        return kweetDao.update(kweet);
    }

    public List<Kweet> getKweetsByKwetteraarId(long id) {
        return kweetDao.getKweetsByKwetteraarId(id);
    }
}
