package dao.implementations;

import dao.interfaces.HashtagDAO;
import model.Hashtag;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
@RequestScoped
public class HashtagDAOImp implements HashtagDAO {

    InMemoryCollectionObject im = InMemoryCollectionObject.getInstance();

    public HashtagDAOImp () {}

    @Override
    public Hashtag save(Hashtag hashtag) {
        //Invalid hashtag, return.
        if (hashtag == null || hashtag.getInhoud() == null || hashtag.getInhoud().isEmpty())
            return null;

        //Non existing hashtag, add id.
        if (hashtag.getId() == 0L)
                hashtag.setId(im.useHashtagId());

        //Existing hashtag, update.
        if (hashtag.getId() > 0L) {
            if (im.getHashtags().stream().filter(h->h.getId() == hashtag.getId()).count() == 0) {
                im.getHashtags().add(hashtag);
                return hashtag;
            }
        }

        //Failed.
        return null;
    }

    @Override
    public boolean delete(long id) {
        //Invalid id, return.
        if (id < 0L || id == 0L)
            return false;

        Hashtag hashtag = im.getHashtags().stream().filter(h->h.getId() == id).findAny().orElse(null);
        if (hashtag != null)
            im.getHashtags().remove(hashtag);

        return true;
    }

    @Override
    public Hashtag get(long id) {
        //Invalid id, return.
        if (id < 0L || id == 0L)
            return null;

        //Search and return, else return null.
        return im.getHashtags().stream().filter(h->h.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Hashtag> getAll() {
        //Return all.
        return im.getHashtags();
    }

    @Override
    public Hashtag getByInhoud(String inhoud) {
        return im.getHashtags().stream().filter(h->h.getInhoud().equals(inhoud)).findAny().orElse(null);
    }

    @Override
    public List<Hashtag> getMatchesByInhoud(String inhoud) {
        List<Hashtag> hashtags = new ArrayList<>();
        im.getHashtags().forEach(h->{
            if (h.getInhoud().contains(inhoud))
                hashtags.add(h);
        });
        return hashtags;
    }
}
