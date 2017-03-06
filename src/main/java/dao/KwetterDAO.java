package dao;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 05/03/2017.
 */
public class KwetterDAO {
    private List<Kweet> kweets;
    private List<Hashtag> hashtags;
    private List<Kwetteraar> kwetteraars;
    private List<Locatie> locaties;
    private List<Rol> rollen;

    public KwetterDAO() {
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

        Rol r1 = new Rol();
        r1.setId(1);
        r1.setTitel("Regulier");

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

        //Fix biolateral relations between objects,
        //Automate addition for adding kweets / owners to add in both ways and have a check?
        //Complete dummy setup in the dal layer.
        //Create unit test for dal layer.

        Kweet k1 = new Kweet();
        k1.setId(1);
        k1.setInhoud("Kweet 1 inhoud.");
        k1.setDatum(LocalDate.now());

        Kweet k2 = new Kweet();
        k2.setId(2);
        k2.setInhoud("Kweet 2 inhoud.");
        k2.setDatum(LocalDate.now());

        Kweet k3 = new Kweet();
        k3.setId(3);
        k3.setInhoud("Kweet 3 inhoud.");
        k3.setDatum(LocalDate.now());
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
