package dao;

import model.Kweet;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public class KweetDAOImp implements KweetDAO {

    InMemoryCollectionObject im = InMemoryCollectionObject.getInstance();

    public KweetDAOImp() {}

    @Override
    public Kweet save(Kweet kweet) {
        //Invalid, return.
        if (kweet == null || kweet.getInhoud() == null || kweet.getInhoud().isEmpty())
            return null;

        //Non existing, add id.
        if (kweet.getId() == 0L)
            kweet.setId(im.useKweetId());

        //Existing, update.
        if (kweet.getId() > 0L) {
            if (im.getKweets().stream().filter(k->k.getId() == kweet.getId()).count() == 0) {
                im.getKweets().add(kweet);
                return kweet;
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

        Kweet kweet = im.getKweets().stream().filter(k->k.getId() == id).findAny().orElse(null);
        if (kweet != null)
            im.getKweets().remove(kweet);

        return true;
    }

    @Override
    public Kweet get(long id) {
        //Invalid id, return.
        if (id < 0L || id == 0L)
            return null;

        //Search and return, else return null.
        return im.getKweets().stream().filter(k->k.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Kweet> getAll() {
        //Return all.
        return im.getKweets();
    }
}
