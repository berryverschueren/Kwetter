package dto;

import model.Hashtag;

/**
 * Created by Berry-PC on 14/03/2017.
 */
public class HashtagDTO {

    private long id;
    private String inhoud;

    public HashtagDTO() {
    }

    public void fromHashtag(Hashtag hashtag) {
        id = hashtag.getId();
        inhoud = hashtag.getInhoud();
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
}
