package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 24/02/2017.
 */
@Entity
@Table(name="t_hashtag")
public class Hashtag {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "inhoud", nullable = false, unique = true)
    private String inhoud;

    @ManyToMany(mappedBy="hashtags", cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    private List<Kweet> kweets;

    public Hashtag() {
        kweets = new ArrayList<>();
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

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public void addKweet(Kweet kweet) {
        if (kweet != null && kweets != null && !kweets.contains(kweet)) {
            kweets.add(kweet);
            if (!kweet.getHashtags().contains(this))
                kweet.addHashtag(this);
        }
    }
}
