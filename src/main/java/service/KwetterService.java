package service;

import dao.implementations.memory.InMemoryCleaner;
import model.memory.Hashtag;
import model.memory.Kweet;
import model.memory.Kwetteraar;
import service.base.*;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public class KwetterService {

    @Inject
    private HashtagBaseService hashtagBaseService;// = new HashtagBaseService();
    @Inject
    private RolBaseService rolBaseService;// = new RolBaseService();
    @Inject
    private LocatieBaseService locatieBaseService;// = new LocatieBaseService();
    @Inject
    private KweetBaseService kweetBaseService;// = new KweetBaseService();
    @Inject
    private KwetteraarBaseService kwetteraarBaseService;// = new KwetteraarBaseService();

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
    public Kweet stuurKweet(long id, String inhoud) {
        Kweet kweet = new Kweet();
        kweet.setInhoud(inhoud);
        kweet.setEigenaar(kwetteraarBaseService.getKwetteraar(id));
        kweet.setDatum(LocalDateTime.now());
        kweet.setHashtags(findHashtags(inhoud));
        kweet.setMentions(findMentions(inhoud));
        kweet.getMentions().forEach(k->k.addMention(kweet));
        return kweetBaseService.saveKweet(kweet);
    }

    //samenvatting van recente kweets van mij en mijn leiders zien
    public List<Kweet> getEigenEnLeiderKweets(long id) {
        List<Kweet> kweets = new ArrayList<>();
        kwetteraarBaseService.getLeiders(id).forEach(l->kweets.addAll(kwetteraarBaseService.getKweets(l.getId())));
        kweets.addAll(kwetteraarBaseService.getKweets(id));
        int count = kweets.size();
        if (count > 50)
            count = 50;
        kweets.sort(comparing(k1 -> k1.getDatum()));
        return kweets.subList(0, count);
    }

    //volgen van trends
    public List<Kweet> getTrends(String hashtagInhoud) {
        Hashtag hashtag = hashtagBaseService.getExactlyMatchingHashtag(hashtagInhoud);
        if (hashtag != null)
            return kweetBaseService.getKweetByHashtagId(hashtag.getId());
        return null;
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
