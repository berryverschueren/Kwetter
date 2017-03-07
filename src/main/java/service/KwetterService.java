package service;

import dao.*;
import model.Hashtag;
import model.Kweet;
import model.Kwetteraar;
import model.Rol;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public class KwetterService {

    private KwetteraarDAO kwetteraarDao = new KwetteraarDAOImp();
    private KweetDAO kweetDao = new KweetDAOImp();
    private HashtagDAO hashtagDao = new HashtagDAOImp();
    private RolDAO rolDao = new RolDAOImp();

    //profielfoto toevoegen . wijzigen
    public void wijzigProfielfoto(long id, String profielfoto) {
        Kwetteraar kwetteraar = kwetteraarDao.get(id);
        kwetteraar.setProfielFoto(profielfoto);
        kwetteraarDao.save(kwetteraar);
    }

    //profielnaam toevoegen . wijzigen
    public void wijzigProfielnaam(long id, String profielnaam) {
        Kwetteraar kwetteraar = kwetteraarDao.get(id);
        kwetteraar.setProfielNaam(profielnaam);
        kwetteraarDao.save(kwetteraar);
    }

    //detailgegevens toevoegen . wijzigen
    public void wijzigDetailgegevens(long id, String detailgegevens) {
        Kwetteraar kwetteraar = kwetteraarDao.get(id);
        kwetteraar.setBio(detailgegevens);
        kwetteraarDao.save(kwetteraar);
    }

    //recente eigen kweets zien
    public List<Kweet> getRecenteEigenTweets(long id) {
        Kwetteraar kwetteraar = kwetteraarDao.get(id);
        int count = kwetteraar.getKweets().size();
        if (count > 10)
            count = 10;
        return kwetteraar.getKweets().subList(0, count);
    }

    //volgers zien
    public List<Kwetteraar> getVolgers(long id) {
        Kwetteraar kwetteraar = kwetteraarDao.get(id);
        return kwetteraar.getVolgers();
    }

    //leiders zien
    public List<Kwetteraar> getLeiders(long id) {
        Kwetteraar kwetteraar = kwetteraarDao.get(id);
        return kwetteraar.getLeiders();
    }

    //hartjes geven
    public void geefHartje(long id, long kweetId) {
        Kweet kweet = kweetDao.get(kweetId);
        if (kweet.getHartjes().stream().filter(k->k.getId() == id).count() == 0)
            kweet.addHartje(kwetteraarDao.get(id));
        kweetDao.save(kweet);
    }

    //kweet zoeken
    public Kweet zoekKweet(String zoekterm) {
        return kweetDao.getAll().stream().filter(k->k.getInhoud().contains(zoekterm)).findAny().orElse(null);
    }

    //kweet sturen
    public void stuurKweet(long id, String inhoud) {
        Kwetteraar kwetteraar = kwetteraarDao.get(id);
        Kweet kweet = new Kweet();
        kweet.setInhoud(inhoud);
        kweet.setHashtags(getHashtags(inhoud));
        kweet.setEigenaar(kwetteraar);
        kweet.setMentions(getMentions(inhoud));
        kweet.setDatum(LocalDateTime.now());
        kweetDao.save(kweet);
    }

    //samenvatting van recente kweets van mij en mijn leiders zien
    public List<Kweet> getEigenEnLeiderKweets(long id) {
        Kwetteraar kwetteraar = kwetteraarDao.get(id);
        List<Kweet> kweets = new ArrayList<>();
        kweets.addAll(kwetteraar.getKweets());
        kwetteraar.getLeiders().forEach(l->kweets.addAll(l.getKweets()));
        int count = kweets.size();
        if (count > 50)
            count = 50;
        return kweets.subList(0, count);
    }

    //kweets zien die mij mentionnen
    public List<Kweet> getMentionedKweets(long id) {
        List<Kweet> kweets = new ArrayList<>();
        kweetDao.getAll().forEach(k->{
            if (k.getMentions().stream().filter(m->m.getId() == id).count() > 0)
                kweets.add(k);
        });
        return kweets;
    }

    //volgen van trends
    public List<Kweet> getTrends(String hashtagInhoud) {
        List<Kweet> kweets = new ArrayList<>();
        Hashtag hashtag = hashtagDao.getAll().stream().filter(h->h.getInhoud().equals(hashtagInhoud)).findAny().orElse(null);
        if (hashtag != null) {
            kweetDao.getAll().forEach(k->{
                if (k.getHashtags().stream().filter(h->h.getId() == hashtag.getId()).count() > 0)
                    kweets.add(k);
            });
        }
        return kweets;
    }

    //kweet verwijderen
    public void verwijderKweet(long kweetId) {
        //Temp since no cascading relations are available with in memory methods.
        kwetteraarDao.get(kweetDao.get(kweetId).getEigenaar().getId()).getKweets().remove(kweetDao.get(kweetId));
        kweetDao.delete(kweetId);
    }

    //lijst van kwetteraars opvragen
    public List<Kwetteraar> getKwetteraars() {
        return kwetteraarDao.getAll();
    }

    //kwetteraars rol wijzigen
    public void wijzigRol(long id, long rolId) {
        Rol rol = rolDao.get(rolId);
        Kwetteraar kwetteraar = kwetteraarDao.get(id);
        kwetteraar.setRol(rol);
        kwetteraarDao.save(kwetteraar);
    }

    //in en uitloggen
    public boolean inloggen(String profielnaam, String wachtwoord) {
        Kwetteraar kwetteraar = kwetteraarDao.getAll().stream().filter(k->k.getProfielNaam().equals(profielnaam) && k.getWachtwoord().equals(wachtwoord)).findAny().orElse(null);
        if (kwetteraar != null)
            return true;
        return false;
    }

    public void uitloggen() {
        //uitloggen.
    }

    //registreren
    public void registreren(String profielnaam, String wachtwoord) {
        Kwetteraar kwetteraar = new Kwetteraar();
        kwetteraar.setProfielNaam(profielnaam);
        kwetteraar.setWachtwoord(wachtwoord);
        kwetteraarDao.save(kwetteraar);
    }



    public List<Hashtag> getHashtags(String inhoud) {
        List<Hashtag> hashtags = new ArrayList<>();
        int count = inhoud.length() - inhoud.replace("#", "").length();
        for (int i = 0; i < count; i++) {
            if (inhoud.contains("#")) {
                int startPos = inhoud.indexOf("#");
                inhoud = inhoud.substring(startPos);
                int endPos = inhoud.substring(1).indexOf(" ");
                String hashtagInhoud;
                if (endPos > -1) {
                    hashtagInhoud = inhoud.substring(0, endPos + 1);
                    inhoud = inhoud.substring(endPos + 1);
                } else {
                    hashtagInhoud = inhoud;
                    inhoud = "";
                }
                Hashtag hashtag = new Hashtag();
                hashtag.setInhoud(hashtagInhoud);
                if (hashtagDao.getAll().stream().filter(h->h.getInhoud().equals(hashtagInhoud)).count() == 0)
                    hashtagDao.save(hashtag);
                else
                    hashtag = hashtagDao.getAll().stream().filter(h->h.getInhoud().equals(hashtagInhoud)).findAny().orElse(null);
                if (hashtag != null && hashtags.stream().filter(h->h.getInhoud().equals(hashtagInhoud)).count() == 0)
                    hashtags.add(hashtag);
            }
        }
        return hashtags;
    }

    public List<Kwetteraar> getMentions(String inhoud) {
        List<Kwetteraar> mentions = new ArrayList<>();
        int count = inhoud.length() - inhoud.replace("@", "").length();
        for (int i = 0; i < count; i++) {
            if (inhoud.contains("@")) {
                int startPos = inhoud.indexOf("@");
                inhoud = inhoud.substring(startPos);
                int endPos = inhoud.substring(1).indexOf(" ");
                String mentionNaam;
                if (endPos > -1) {
                    mentionNaam = inhoud.substring(0, endPos + 1);
                    inhoud = inhoud.substring(endPos + 1);
                } else {
                    mentionNaam = inhoud;
                    inhoud = "";
                }
                Kwetteraar kwetteraar = kwetteraarDao.getAll().stream().filter(k->k.getProfielNaam().equals(mentionNaam.substring(1))).findAny().orElse(null);
                if (kwetteraar != null && mentions.stream().filter(k->k.getId() == kwetteraar.getId()).count() == 0)
                    mentions.add(kwetteraar);
            }
        }
        return mentions;
    }

    public Rol createRol(String titel) {
        Rol rol = new Rol();
        rol.setTitel(titel);
        rolDao.save(rol);
        return rol;
    }

    public void volgKwetteraar(long id, long idLeider) {
        Kwetteraar volger = kwetteraarDao.get(id);
        Kwetteraar leider = kwetteraarDao.get(idLeider);
        leider.addVolger(volger);
        kwetteraarDao.save(volger);
        kwetteraarDao.save(leider);
    }

    public Kwetteraar getKwetteraar(long id) {
        return kwetteraarDao.get(id);
    }

    public void clearMemory() {
        InMemoryCleaner imc = new InMemoryCleaner();
        imc.clearMemory();
    }
}
