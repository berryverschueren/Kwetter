package dto;

import model.Hashtag;
import model.Kweet;
import model.Kwetteraar;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Berry-PC on 08/03/2017.
 */
public class KweetDTO {

    private long id;
    private String inhoud;
    private LocalDateTime datum;
    private List<Hashtag> hashtags;
    private KwetteraarDTO eigenaar;

    public KweetDTO() {}

    public void fromKweet(Kweet kweet) {
        id = kweet.getId();
        inhoud = kweet.getInhoud();
        datum = kweet.getDatum();
        hashtags = kweet.getHashtags();
        Kwetteraar kwetteraar = kweet.getEigenaar();
        KwetteraarDTO kdto = new KwetteraarDTO();
        kdto.fromKwetteraar(kwetteraar);
        eigenaar = kdto;
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

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public KwetteraarDTO getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(KwetteraarDTO eigenaar) {
        this.eigenaar = eigenaar;
    }
}
