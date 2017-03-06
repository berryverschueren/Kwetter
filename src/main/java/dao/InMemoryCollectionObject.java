package dao;

import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 05/03/2017.
 */
public class InMemoryCollectionObject {

    private static InMemoryCollectionObject im = new InMemoryCollectionObject();
    protected List<Kweet> kweets;
    protected List<Hashtag> hashtags;
    protected List<Kwetteraar> kwetteraars;
    protected List<Locatie> locaties;
    protected List<Rol> rollen;
    protected long kweetId;
    protected long hashtagId;
    protected long kwetteraarId;
    protected long locatieId;
    protected long rollenId;

    private InMemoryCollectionObject () {
        kweets = new ArrayList<>();
        hashtags = new ArrayList<>();
        kwetteraars = new ArrayList<>();
        locaties = new ArrayList<>();
        rollen = new ArrayList<>();
        kweetId = 0;
        hashtagId = 0;
        kwetteraarId = 0;
        locatieId = 0;
        rollenId = 0;
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

    protected List<Rol> getRollen() {
        return rollen;
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

    protected long useRollenId() {
        long temp = rollenId;
        rollenId++;
        return temp;
    }
}
