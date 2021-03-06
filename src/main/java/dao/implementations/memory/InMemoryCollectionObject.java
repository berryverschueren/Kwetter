package dao.implementations.memory;

import model.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 05/03/2017.
 */
@ApplicationScoped
public class InMemoryCollectionObject {

    private static InMemoryCollectionObject im = new InMemoryCollectionObject();
    protected List<Kweet> kweets;
    protected List<Hashtag> hashtags;
    protected List<Kwetteraar> kwetteraars;
    protected List<Locatie> locaties;
    protected long kweetId;
    protected long hashtagId;
    protected long kwetteraarId;
    protected long locatieId;

    private InMemoryCollectionObject () {
        clearMemory();
    }

    public static InMemoryCollectionObject getInstance() {
        return im;
    }

    protected List<Kweet> getKweets() {
        return kweets;
    }

    protected List<Hashtag> getHashtags() {
        return hashtags;
    }

    protected List<Kwetteraar> getKwetteraars() {
        return kwetteraars;
    }

    protected List<Locatie> getLocaties() {
        return locaties;
    }

    protected long useHashtagId() {
        long temp = hashtagId;
        hashtagId++;
        return temp;
    }

    protected long useKweetId() {
        long temp = kweetId;
        kweetId++;
        return temp;
    }

    protected long useKwetteraarId() {
        long temp = kwetteraarId;
        kwetteraarId++;
        return temp;
    }

    protected long useLocatieId() {
        long temp = locatieId;
        locatieId++;
        return temp;
    }

    protected void clearMemory() {
        kweets = new ArrayList<>();
        hashtags = new ArrayList<>();
        kwetteraars = new ArrayList<>();
        locaties = new ArrayList<>();
        kweetId = 1;
        hashtagId = 1;
        kwetteraarId = 1;
        locatieId = 1;
    }
}
