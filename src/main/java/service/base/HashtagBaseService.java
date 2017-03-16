package service.base;

import dao.interfaces.jpa.HashtagDAO;
import model.Hashtag;

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
        return hashtagDao.find(id);
    }

    public Hashtag insertHashtag(String inhoud) {
        if (getExactlyMatchingHashtag(inhoud) != null)
            return getExactlyMatchingHashtag(inhoud);

        Hashtag hashtag = new Hashtag();
        hashtag.setInhoud(inhoud);
        return hashtagDao.create(hashtag);
    }

    public Hashtag updateHashtag(long id, String inhoud) {
        Hashtag hashtag = getHashtag(id);
        hashtag.setInhoud(inhoud);
        return hashtagDao.update(hashtag);
    }

    public void deleteHashtag(long id) {
        hashtagDao.delete(id);
    }

    public Hashtag getExactlyMatchingHashtag(String inhoud) {
        return hashtagDao.getByInhoud(inhoud);
    }

    public List<Hashtag> getMatchingHashtags(String inhoud) {
        return hashtagDao.getMatchesByInhoud(inhoud);
    }
}
