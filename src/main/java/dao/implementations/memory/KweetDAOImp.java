package dao.implementations.memory;

import dao.interfaces.memory.KweetDAO;
import model.Kweet;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Created by Berry-PC on 06/03/2017.
 */
@RequestScoped
@Alternative
public class KweetDAOImp implements KweetDAO {

    InMemoryCollectionObject im = InMemoryCollectionObject.getInstance();

    public KweetDAOImp() {
        // Empty constructor for dependency injection purposes.
    }

    @Override
    public Kweet save(Kweet kweet) {
        //Invalid, return.
        if (kweet == null || kweet.getInhoud() == null || kweet.getInhoud().isEmpty())
            return null;

        //Non existing, add id.
        if (kweet.getId() == 0L)
            kweet.setId(im.useKweetId());

        //Existing, update.
        if (kweet.getId() > 0L && im.getKweets().stream().filter(k->k.getId() == kweet.getId()).count() == 0) {
            im.getKweets().add(kweet);
            return kweet;
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

    @Override
    public List<Kweet> getMatchesByInhoud(String inhoud) {
        List<Kweet> kweets = new ArrayList<>();
        im.getKweets().forEach(k->{
            if (k.getInhoud().contains(inhoud))
                kweets.add(k);
        });
        return kweets;
    }

    @Override
    public List<Kweet> getKweetByHashtagId(long id) {
        List<Kweet> kweets = new ArrayList<>();
        getAll().forEach(k->k.getHashtags().forEach(h->{
            if (h.getId() == id && !kweets.contains(k))
                kweets.add(k);
        }));
        return kweets;
    }

    @Override
    public List<Kweet> getKweetsByMentionId(long id) {
        List<Kweet> kweets = new ArrayList<>();
        getAll().forEach(k->k.getMentions().forEach(m->{
            if (m.getId() == id && !kweets.contains(k))
                kweets.add(k);
        }));
        return kweets;
    }

    @Override
    public List<Kweet> getKweetsByKwetteraarId(long id) {
        List<Kweet> kweets = new ArrayList<>();
        getAll().forEach(k->{
            if (k.getEigenaar().getId() == id && !kweets.contains(k))
                kweets.add(k);
        });
        return kweets;
    }

    @Override
    public List<Kweet> getRecenteEigenKweetsByKwetteraarId(long id) {
        List<Kweet> kweets = getKweetsByKwetteraarId(id);
        int count = kweets.size();
        if(count > 10)
            count = 10;
        kweets.sort(comparing(k1 -> k1.getDatum()));
        return kweets.subList(0, count);
    }
}
