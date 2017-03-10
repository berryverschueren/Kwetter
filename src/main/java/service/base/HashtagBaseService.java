package service.base;

import dao.interfaces.HashtagDAO;
import dao.implementations.memory.HashtagDAOImp;
import model.memory.Hashtag;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class HashtagBaseService {
    @Inject
    private HashtagDAO hashtagDao; // = new HashtagDAOImp();

    public List<Hashtag> getHashtags() {
        return hashtagDao.getAll();
    }

    public Hashtag getHashtag(long id) {
        return hashtagDao.get(id);
    }

    public Hashtag insertHashtag(String inhoud) {
        if (getExactlyMatchingHashtag(inhoud) != null)
            return getExactlyMatchingHashtag(inhoud);

        Hashtag hashtag = new Hashtag();
        hashtag.setInhoud(inhoud);
        return hashtagDao.save(hashtag);
    }

    public Hashtag updateHashtag(long id, String inhoud) {
        Hashtag hashtag = getHashtag(id);
        hashtag.setInhoud(inhoud);
        return hashtagDao.save(hashtag);
    }

    public boolean deleteHashtag(long id) {
        return hashtagDao.delete(id);
    }

    public Hashtag getExactlyMatchingHashtag(String inhoud) {
        return hashtagDao.getByInhoud(inhoud);
    }

    public List<Hashtag> getMatchingHashtags(String inhoud) {
        return hashtagDao.getMatchesByInhoud(inhoud);
    }
}
