package model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Berry-PC on 24/02/2017.
 */
@Entity
@Table(name="t_kweet")
public class Kweet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "inhoud", nullable = false, unique = true)
    private String inhoud;

    @Column(name = "datum", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;

    @ManyToMany
    @JoinTable(name = "t_kweet_hashtag"
            , joinColumns = @JoinColumn(name = "kweet_hashtag_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "hashtag_hashtag_id", referencedColumnName = "id"))
    private List<Hashtag> hashtags;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "eigenaar_id", referencedColumnName = "id")
    private Kwetteraar eigenaar;

    @ManyToMany
    @JoinTable(name = "t_kweet_kwetteraar_mentions"
            , joinColumns = @JoinColumn(name = "kweet_hartje_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "kwetteraar_hartje_id", referencedColumnName = "id"))
    private List<Kwetteraar> mentions;

    @ManyToMany
    @JoinTable(name = "t_kweet_kwetteraar_hartjes"
            , joinColumns = @JoinColumn(name = "kweet_mention_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "kwetteraar_mention_id", referencedColumnName = "id"))
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
        if (!eigenaar.getKweets().contains(this))
            eigenaar.addKweet(this);
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
        if (hashtag != null && hashtags != null && !hashtags.contains(hashtag)) {
            hashtags.add(hashtag);
            if (!hashtag.getKweets().contains(this))
                hashtag.addKweet(this);
        }
    }

    public void addMention(Kwetteraar mention) {
        if (mention != null && mentions != null && !mentions.contains(mention)) {
            mentions.add(mention);
            if (!mention.getMentions().contains(this))
                mention.addMention(this);
        }
    }

    public void addHartje(Kwetteraar hartje) {
        if (hartje != null && hartjes != null && !hartjes.contains(hartje)) {
            hartjes.add(hartje);
            if (!hartje.getKweets().contains(this))
                hartje.addHartje(this);
        }
    }
}
