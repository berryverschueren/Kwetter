package model;

import javax.persistence.*;

/**
 * Created by Berry-PC on 24/02/2017.
 */
@Entity
@Table(name="t_hashtag")
public class Hashtag {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "inhoud", nullable = false, unique = true)
    private String inhoud;

    public Hashtag() {}

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
