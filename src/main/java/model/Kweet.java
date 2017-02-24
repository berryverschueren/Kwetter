package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Berry-PC on 24/02/2017.
 */
public class Kweet {

    private long id;
    private String inhoud;
    private Date datum;
    private List<Hashtag> hashtags;
    private Kwetteraar eigenaar;
    private List<Kwetteraar> mentions;

    public Kweet() {
        hashtags = new ArrayList<Hashtag>();
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
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

    public void addHashtag(Hashtag hashtag) {
        if (hashtag != null && hashtags != null) {
            hashtags.add(hashtag);
        }
    }

    public void addMention(Kwetteraar kwetteraar) {
        if (kwetteraar != null && mentions != null) {
            mentions.add(kwetteraar);
        }
    }
}
