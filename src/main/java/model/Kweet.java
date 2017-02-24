package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 24/02/2017.
 */
public class Kweet {

    private long id;
    private String inhoud;
    private LocalDate datum;
    private List<Hashtag> hashtags;
    private Kwetteraar eigenaar;
    private List<Kwetteraar> mentions;
    private List<Kwetteraar> hartjes;

    public Kweet() {
        hashtags = new ArrayList<>();
        mentions = new ArrayList<>();
        hartjes = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public Kwetteraar getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(Kwetteraar eigenaar) {
        this.eigenaar = eigenaar;
    }

    public List<Kwetteraar> getMentions() {
        return mentions;
    }

    public void setMentions(List<Kwetteraar> mentions) {
        this.mentions = mentions;
    }

    public List<Kwetteraar> getHartjes() {
        return hartjes;
    }

    public void setHartjes(List<Kwetteraar> hartjes) {
        this.hartjes = hartjes;
    }

    public void addHashtag(Hashtag hashtag) {
        if (hashtag != null && hashtags != null)
            hashtags.add(hashtag);
    }

    public void addMention(Kwetteraar mention) {
        if (mention != null && mentions != null)
            mentions.add(mention);
    }

    public void addHartje(Kwetteraar hartje) {
        if (hartje != null && hartjes != null)
            hartjes.add(hartje);
    }
}
