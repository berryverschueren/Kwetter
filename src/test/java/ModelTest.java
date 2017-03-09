import static org.junit.Assert.assertEquals;

import model.memory.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 24/02/2017.
 */
public class ModelTest {

    @Test
    public void testGettersAndSetters() {
        double delta = 0.01;

        long id = 1;
        String titel = "regulier";

        Rol rol = new Rol();
        rol.setId(id);
        rol.setTitel(titel);

        assertEquals(id, rol.getId());
        assertEquals(titel, rol.getTitel());

        double latitude = 0.1;
        double longitude = 0.1;
        String plaatsNaam = "Eindhoven";

        Locatie locatie = new Locatie();
        locatie.setId(id);
        locatie.setLatitude(latitude);
        locatie.setLongitude(longitude);
        locatie.setPlaatsNaam(plaatsNaam);

        assertEquals(id, locatie.getId());
        assertEquals(latitude, locatie.getLatitude(), delta);
        assertEquals(longitude, locatie.getLongitude(), delta);
        assertEquals(plaatsNaam, locatie.getPlaatsNaam());

        String profielNaam = "Berry";
        String profielFoto = "foto";
        String bio = "bio";
        String website = "website";
        String wachtwoord = "wachtwoord";

        Kwetteraar kwetteraar = new Kwetteraar();
        kwetteraar.setId(id);
        kwetteraar.setProfielNaam(profielNaam);
        kwetteraar.setProfielFoto(profielFoto);
        kwetteraar.setBio(bio);
        kwetteraar.setWebsite(website);
        kwetteraar.setRol(rol);
        kwetteraar.setLocatie(locatie);
        kwetteraar.setWachtwoord(wachtwoord);

        assertEquals(id, kwetteraar.getId());
        assertEquals(profielNaam, kwetteraar.getProfielNaam());
        assertEquals(profielFoto, kwetteraar.getProfielFoto());
        assertEquals(bio, kwetteraar.getBio());
        assertEquals(website, kwetteraar.getWebsite());
        assertEquals(wachtwoord, kwetteraar.getWachtwoord());
        assertEquals(rol, kwetteraar.getRol());
        assertEquals(locatie, kwetteraar.getLocatie());

        String inhoud = "inhoud";

        Hashtag hashtag = new Hashtag();
        hashtag.setId(id);
        hashtag.setInhoud(inhoud);

        assertEquals(id, hashtag.getId());
        assertEquals(inhoud, hashtag.getInhoud());

        LocalDateTime datum = LocalDateTime.now();

        Kweet kweet = new Kweet();
        kweet.setId(id);
        kweet.setInhoud(inhoud);
        kweet.setDatum(datum);
        kweet.setEigenaar(kwetteraar);

        assertEquals(id, kweet.getId());
        assertEquals(inhoud, kweet.getInhoud());
        assertEquals(datum, kweet.getDatum());
        assertEquals(kwetteraar, kweet.getEigenaar());

        List<Kweet> kweetList = new ArrayList<>();
        List<Kwetteraar> kwetteraarList = new ArrayList<>();
        List<Hashtag> hashtagList = new ArrayList<>();

        hashtagList.add(hashtag);
        kweetList.add(kweet);
        kwetteraarList.add(kwetteraar);

        kweet.setHartjes(kwetteraarList);
        kweet.setMentions(kwetteraarList);
        kweet.setHashtags(hashtagList);

        assertEquals(hashtagList, kweet.getHashtags());
        assertEquals(kwetteraarList, kweet.getHartjes());
        assertEquals(kwetteraarList, kweet.getMentions());

        kwetteraar.setKweets(kweetList);
        kwetteraar.setHartjes(kweetList);
        kwetteraar.setMentions(kweetList);
        kwetteraar.setVolgers(kwetteraarList);
        kwetteraar.setLeiders(kwetteraarList);

        assertEquals(kweetList, kwetteraar.getKweets());
        assertEquals(kweetList, kwetteraar.getHartjes());
        assertEquals(kweetList, kwetteraar.getMentions());
        assertEquals(kwetteraarList, kwetteraar.getVolgers());
        assertEquals(kwetteraarList, kwetteraar.getLeiders());
    }

    @Test
    public void testAdders() {
        Kweet k1 = new Kweet();
        Kweet k2 = new Kweet();
        Kwetteraar r1 = new Kwetteraar();
        Kwetteraar r2 = new Kwetteraar();
        Hashtag h1 = new Hashtag();

        k1.addHartje(r1);
        k1.addMention(r1);
        k1.addHashtag(h1);

        r1.addLeider(r1);
        r1.addMention(k1);
        r1.addKweet(k1);
        r1.addHartje(k1);

        r2.addVolger(r1);
        r2.addHartje(k2);
        r2.addMention(k2);

        assertEquals(r1, k1.getHartjes().get(0));
        assertEquals(r1, k1.getMentions().get(0));
        assertEquals(h1, k1.getHashtags().get(0));

        assertEquals(r1, r1.getLeiders().get(0));
        assertEquals(r1, r1.getVolgers().get(0));
        assertEquals(k1, r1.getMentions().get(0));
        assertEquals(k1, r1.getKweets().get(0));
        assertEquals(k1, r1.getHartjes().get(0));

        assertEquals(r1, r2.getVolgers().get(0));
        assertEquals(k2, r2.getHartjes().get(0));
        assertEquals(k2, r2.getMentions().get(0));
    }

}
