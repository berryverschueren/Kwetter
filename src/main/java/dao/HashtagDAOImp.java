package dao;

import model.Hashtag;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
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
            if (im.getHashtags().stream().filter(h->h.getId() == hashtag.getId()).count() > 0) {
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

        //Search and delete.
        im.getHashtags().stream().filter(h->h.getId() == id).forEach(h -> im.getHashtags().remove(h));
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
}
