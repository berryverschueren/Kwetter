package dao;

import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 05/03/2017.
 */
public class InMemoryCollectionObject {
    private List<Kweet> kweets;
    private List<Hashtag> hashtags;
    private List<Kwetteraar> kwetteraars;
    private List<Locatie> locaties;
    private List<Rol> rollen;

    public InMemoryCollectionObject() {
        kweets = new ArrayList<>();
        hashtags = new ArrayList<>();
        kwetteraars = new ArrayList<>();
        locaties = new ArrayList<>();
        rollen = new ArrayList<>();

        Locatie l1 = new Locatie();
        l1.setId(1);
        l1.setPlaatsNaam("Eindhoven");
        l1.setLatitude(51.5);
        l1.setLongitude(1.5);

        Locatie l2 = new Locatie();
        l2.setId(2);
        l2.setPlaatsNaam("Roosendaal");
        l2.setLatitude(52.5);
        l2.setLongitude(1.5);

        locaties.add(l1);
        locaties.add(l2);

        Rol r1 = new Rol();
        r1.setId(1);
        r1.setTitel("Regulier");

        rollen.add(r1);

        Kwetteraar berry = new Kwetteraar();
        berry.setId(1);
        berry.setProfielNaam("Berry");
        berry.setProfielFoto("Dummy Foto");
        berry.setBio("Berrys bio..");
        berry.setWebsite("www.weblikesoftware.nl");
        berry.setWachtwoord("test123");
        berry.setLocatie(l1);
        berry.setRol(r1);

        Kwetteraar yva = new Kwetteraar();
        yva.setId(2);
        yva.setProfielNaam("Yva");
        yva.setProfielFoto("Dummy Foto");
        yva.setBio("Yvas bio..");
        yva.setWebsite("www.google.nl");
        yva.setWachtwoord("test123");
        yva.setLocatie(l2);
        yva.setRol(r1);

        kwetteraars.add(berry);
        kwetteraars.add(yva);

        Hashtag h1 = new Hashtag();
        h1.setId(1);
        h1.setInhoud("#TAG1");

        Hashtag h2 = new Hashtag();
        h2.setId(2);
        h2.setInhoud("#TAG2");

        Hashtag h3 = new Hashtag();
        h3.setId(3);
        h3.setInhoud("#TAG3");

        hashtags.add(h1);
        hashtags.add(h2);
        hashtags.add(h3);

        Kweet k1 = new Kweet();
        k1.setId(1);
        k1.setInhoud("Kweet 1 inhoud.");
        k1.setDatum(LocalDateTime.now());
        k1.addHashtag(h1);

        Kweet k2 = new Kweet();
        k2.setId(2);
        k2.setInhoud("Kweet 2 inhoud.");
        k2.setDatum(LocalDateTime.now());
        k2.addHashtag(h2);

        Kweet k3 = new Kweet();
        k3.setId(3);
        k3.setInhoud("Kweet 3 inhoud.");
        k3.setDatum(LocalDateTime.now());
        k3.addHashtag(h3);

        kweets.add(k1);
        kweets.add(k2);
        kweets.add(k3);

        berry.addKweet(k1);
        berry.addKweet(k2);

        yva.addKweet(k3);

        berry.addLeider(yva);

        yva.addLeider(berry);
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public List<Kwetteraar> getKwetteraars() {
        return kwetteraars;
    }

    public void setKwetteraars(List<Kwetteraar> kwetteraars) {
        this.kwetteraars = kwetteraars;
    }

    public List<Locatie> getLocaties() {
        return locaties;
    }

    public void setLocaties(List<Locatie> locaties) {
        this.locaties = locaties;
    }

    public List<Rol> getRollen() {
        return rollen;
    }

    public void setRollen(List<Rol> rollen) {
        this.rollen = rollen;
    }
}
