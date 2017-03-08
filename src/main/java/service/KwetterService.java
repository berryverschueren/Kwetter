package service;

import dao.implementations.*;
import dao.interfaces.HashtagDAO;
import dao.interfaces.KweetDAO;
import dao.interfaces.KwetteraarDAO;
import dao.interfaces.RolDAO;
import model.Hashtag;
import model.Kweet;
import model.Kwetteraar;
import model.Rol;
import service.base.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public class KwetterService {

    private HashtagBaseService hashtagBaseService = new HashtagBaseService();
    private RolBaseService rolBaseService = new RolBaseService();
    private LocatieBaseService locatieBaseService = new LocatieBaseService();
    private KweetBaseService kweetBaseService = new KweetBaseService();
    private KwetteraarBaseService kwetteraarBaseService = new KwetteraarBaseService();

    private KwetteraarDAO kwetteraarDao = new KwetteraarDAOImp();
    private KweetDAO kweetDao = new KweetDAOImp();

    public void uitloggen() {
        //uitloggen.
    }

    //profielfoto toevoegen . wijzigen
    public void wijzigProfielfoto(long id, String profielfoto) {
        kwetteraarBaseService.getKwetteraar(id).setProfielFoto(profielfoto);
    }

    //profielnaam toevoegen . wijzigen
    public void wijzigProfielnaam(long id, String profielnaam) {
        kwetteraarBaseService.getKwetteraar(id).setProfielNaam(profielnaam);
    }

    //detailgegevens toevoegen . wijzigen
    public void wijzigDetailgegevens(long id, String detailgegevens) {
        kwetteraarBaseService.getKwetteraar(id).setBio(detailgegevens);
    }

    //kwetteraars rol wijzigen
    public void wijzigRol(long id, long rolId) {
        kwetteraarBaseService.getKwetteraar(id).setRol(rolBaseService.getRol(rolId));
    }

    //hartjes geven
    public void geefHartje(long id, long kweetId) {
        kweetBaseService.getKweet(kweetId).addHartje(kwetteraarBaseService.getKwetteraar(id));
    }

    //kweet sturen
    public void stuurKweet(long id, String inhoud) {
        Kweet kweet = new Kweet();
        kweet.setInhoud(inhoud);
        kweet.setEigenaar(kwetteraarBaseService.getKwetteraar(id));
        kweet.setDatum(LocalDateTime.now());
        kweet.setHashtags(findHashtags(inhoud));
        kweet.setMentions(findMentions(inhoud));
        kweetBaseService.saveKweet(kweet);
    }

    //samenvatting van recente kweets van mij en mijn leiders zien
    public List<Kweet> getEigenEnLeiderKweets(long id) {
        List<Kweet> kweets = new ArrayList<>();
        getLeiders(id).forEach(l->kweets.addAll(getAlleKweetsByKwetteraarId(l.getId())));
        kweets.addAll(getAlleKweetsByKwetteraarId(id));
        int count = kweets.size();
        if (count > 50)
            count = 50;
        kweets.sort(comparing(k1 -> k1.getDatum()));
        return kweets.subList(0, count);
    }

    //in en uitloggen
    public boolean inloggen(String profielnaam, String wachtwoord) {
        return kwetteraarBaseService.inloggen(profielnaam, wachtwoord);
    }

    //registreren
    public void registreren(String profielnaam, String wachtwoord) {
        kwetteraarBaseService.registreren(profielnaam, wachtwoord);
    }

    public void volgKwetteraar(long id, long idLeider) {
        kwetteraarBaseService.addVolger(id, idLeider);
    }

    public List<Kweet> getAlleKweetsByKwetteraarId(long id) {
        return kweetBaseService.getKweetsByKwetteraarId(id);
    }

    //recente eigen kweets zien
    public List<Kweet> getRecenteEigenTweets(long id) {
        return kweetBaseService.getRecenteEigenKweetsByKwetteraarId(id);
    }

    //volgers zien
    public List<Kwetteraar> getVolgers(long id) {
        return kwetteraarBaseService.getVolgers(id);
    }

    //leiders zien
    public List<Kwetteraar> getLeiders(long id) {
        return kwetteraarBaseService.getLeiders(id);
    }

    //kweet zoeken
    public List<Kweet> zoekKweet(String zoekterm) {
        return kweetBaseService.getMatchesByInhoud(zoekterm);
    }

    //kweets zien die mij mentionnen
    public List<Kweet> getMentionedKweets(long id) {
        return kweetBaseService.getKweetsByMentionId(id);
    }

    //volgen van trends
    public List<Kweet> getTrends(String hashtagInhoud) {
        return kweetBaseService.getKweetByHashtagId(hashtagBaseService.getExactlyMatchingHashtag(hashtagInhoud).getId());
    }

    //kweet verwijderen
    public void verwijderKweet(long kweetId) {
        //Temp since no cascading relations are available with in memory methods.
        kwetteraarBaseService.deleteKweet(kweetBaseService.getKweet(kweetId).getEigenaar().getId(), kweetId);
        kweetBaseService.deleteKweet(kweetId);
    }

    public Rol createRol(String titel) {
        return rolBaseService.insertRol(titel);
    }

    public HashtagBaseService getHashtagBaseService() {
        return hashtagBaseService;
    }

    public RolBaseService getRolBaseService() {
        return rolBaseService;
    }

    public LocatieBaseService getLocatieBaseService() {
        return locatieBaseService;
    }

    public KweetBaseService getKweetBaseService() {
        return kweetBaseService;
    }

    public KwetteraarBaseService getKwetteraarBaseService() {
        return kwetteraarBaseService;
    }

    public void clearMemory() {
        InMemoryCleaner imc = new InMemoryCleaner();
        imc.clearMemory();
    }

    public List<Hashtag> findHashtags(String inhoud) {
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
                if (hashtagBaseService.getExactlyMatchingHashtag(hashtagInhoud) == null)
                    hashtagBaseService.insertHashtag(hashtagInhoud);
                hashtags.add(hashtagBaseService.getExactlyMatchingHashtag(hashtagInhoud));
            }
        }
        return hashtags;
    }

    public List<Kwetteraar> findMentions(String inhoud) {
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
                mentions.add(kwetteraarBaseService.getKwetteraarByProfielnaam(mentionNaam.substring(1)));
            }
        }
        return mentions;
    }
}
