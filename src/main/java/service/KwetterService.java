package service;

import dao.implementations.memory.InMemoryCleaner;
import model.Hashtag;
import model.Kweet;
import model.Kwetteraar;
import service.base.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Created by Berry-PC on 06/03/2017.
 */
@Stateless
public class KwetterService {

    private HashtagBaseService hashtagBaseService;
    private LocatieBaseService locatieBaseService;
    private KweetBaseService kweetBaseService;
    private KwetteraarBaseService kwetteraarBaseService;

    @Inject
    public KwetterService(HashtagBaseService hbs, LocatieBaseService lbs, KwetteraarBaseService tbs, KweetBaseService kbs) {
        hashtagBaseService = hbs;
        locatieBaseService = lbs;
        kwetteraarBaseService = tbs;
        kweetBaseService = kbs;
    }

    public KwetterService () {
        // Empty constructor for dependency injection purposes.
    }

    public void uitloggen() {
        //uitloggen..
    }

    //profielfoto toevoegen . wijzigen
    public void wijzigProfielfoto(long id, String profielfoto) {
        Kwetteraar kwetteraar = kwetteraarBaseService.getKwetteraar(id);
        kwetteraar.setProfielFoto(profielfoto);
        kwetteraarBaseService.updateKwetteraar(kwetteraar);
    }

    //profielnaam toevoegen . wijzigen
    public void wijzigProfielnaam(long id, String profielnaam) {
        Kwetteraar kwetteraar = kwetteraarBaseService.getKwetteraar(id);
        kwetteraar.setProfielNaam(profielnaam);
        kwetteraarBaseService.updateKwetteraar(kwetteraar);
    }

    //detailgegevens toevoegen . wijzigen
    public void wijzigDetailgegevens(long id, String detailgegevens) {
        Kwetteraar kwetteraar = kwetteraarBaseService.getKwetteraar(id);
        kwetteraar.setBio(detailgegevens);
        kwetteraarBaseService.updateKwetteraar(kwetteraar);
    }

    //kwetteraars rol wijzigen
    public void wijzigRol(long id, String newRol) {
        Kwetteraar kwetteraar = kwetteraarBaseService.getKwetteraar(id);
        kwetteraar.setRol(newRol);
        kwetteraarBaseService.updateKwetteraar(kwetteraar);
    }

    //hartjes geven
    public void geefHartje(long id, long kweetId) {
        kweetBaseService.getKweet(kweetId).addHartje(kwetteraarBaseService.getKwetteraar(id));
    }

    //kweet sturen
    public Kweet stuurKweet(long id, String inhoud) {
        Kweet kweet = new Kweet();
        kweet.setInhoud(inhoud);
        kweet.setEigenaar(kwetteraarBaseService.getKwetteraar(id));
        kweet.setDatum(new Date());
        kweetBaseService.saveKweet(kweet);
        List<Hashtag> hashtags = findHashtags(inhoud);
        hashtags.forEach(h -> {
            if (h != null)
                kweet.addHashtag(h);
        });
        List<Kwetteraar> mentions = findMentions(inhoud);
        mentions.forEach(m -> {
            if (m != null) {
                kweet.addMention(m);
            }
        });
        return kweetBaseService.updateKweet(kweet);
    }

    //samenvatting van recente kweets van mij en mijn leiders zien
    public List<Kweet> getEigenEnLeiderKweets(long id) {
        List<Kweet> kweets = new ArrayList<>();
        List<Kwetteraar> leiders = kwetteraarBaseService.getLeiders(id);
        for (int i = 0; i < leiders.size(); i++) {
            Kwetteraar kwetteraar = leiders.get(i);
            List<Kweet> kwetKweets = kwetteraar.getKweets();
            kweets.addAll(kwetKweets);
        }
        kweets.addAll(kwetteraarBaseService.getKweets(id));
        int count = kweets.size();
        if (count > 50)
            count = 50;
        kweets.sort(comparing(Kweet::getDatum));
        Collections.reverse(kweets);
        return kweets.subList(0, count);
    }

    //volgen van trends
    public List<Kweet> getTrends(String hashtagInhoud) {
        Hashtag hashtag = hashtagBaseService.getExactlyMatchingHashtag(hashtagInhoud);
        if (hashtag != null)
            return kweetBaseService.getKweetByHashtagId(hashtag.getId());
        return new ArrayList<>();
    }

    //kweet verwijderen
    public void verwijderKweet(long kweetId) {
        //Temp since no cascading relations are available with in memory methods.
        kwetteraarBaseService.deleteKweet(kweetBaseService.getKweet(kweetId).getEigenaar().getId(), kweetId);
        kweetBaseService.deleteKweet(kweetId);
    }

    public HashtagBaseService getHashtagBaseService() {
        return hashtagBaseService;
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
                int startPos = inhoud.indexOf('#');
                inhoud = inhoud.substring(startPos);
                int endPos = inhoud.substring(1).indexOf(' ');
                String hashtagInhoud;
                if (endPos > -1) {
                    hashtagInhoud = inhoud.substring(0, endPos + 1);
                    inhoud = inhoud.substring(endPos + 1);
                } else {
                    hashtagInhoud = inhoud;
                    inhoud = "";
                }
                if (hashtagBaseService.getExactlyMatchingHashtag(hashtagInhoud.substring(1)) == null)
                    hashtagBaseService.insertHashtag(hashtagInhoud.substring(1));

                if (hashtags.stream().filter(h->h.getId() == hashtagBaseService.getExactlyMatchingHashtag(hashtagInhoud.substring(1)).getId()).findAny().orElse(null) == null)
                    hashtags.add(hashtagBaseService.getExactlyMatchingHashtag(hashtagInhoud.substring(1)));

            }
        }
        return hashtags;
    }

    public List<Kwetteraar> findMentions(String inhoud) {
        List<Kwetteraar> mentions = new ArrayList<>();
        int count = inhoud.length() - inhoud.replace("@", "").length();
        for (int i = 0; i < count; i++) {
            if (inhoud.contains("@")) {
                int startPos = inhoud.indexOf('@');
                inhoud = inhoud.substring(startPos);
                int endPos = inhoud.substring(1).indexOf(' ');
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
