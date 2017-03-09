package model.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 24/02/2017.
 */
@Entity
@Table(name="t_kwetteraar")
public class Kwetteraar {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "profielnaam", nullable = false, unique = true)
    private String profielNaam;

    @Column(name = "profielfoto")
    private String profielFoto;

    @Column(name = "bio")
    private String bio;

    @Column(name = "website")
    private String website;

    @Column(name = "wachtwoord", nullable = false, unique = true)
    private String wachtwoord;

    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private Rol rol;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "locatie_id", referencedColumnName = "id")
    private Locatie locatie;

    @OneToMany(mappedBy = "eigenaar")
    private List<Kweet> kweets;

    @ManyToMany(mappedBy="hartjes")
    private List<Kweet> hartjes;

    @ManyToMany(mappedBy="mentions")
    private List<Kweet> mentions;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "t_kwetteraar_kwetteraar"
            , joinColumns = @JoinColumn(name = "volger_id", referencedColumnName = "id", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "leider_id", referencedColumnName = "id", nullable = false))
    private List<Kwetteraar> volgers;

    @ManyToMany(mappedBy = "volgers")
    private List<Kwetteraar> leiders;

    public Kwetteraar() {
        kweets = new ArrayList<>();
        hartjes = new ArrayList<>();
        volgers = new ArrayList<>();
        leiders = new ArrayList<>();
        mentions = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfielNaam() {
        return profielNaam;
    }

    public void setProfielNaam(String profielNaam) {
        this.profielNaam = profielNaam;
    }

    public String getProfielFoto() {
        return profielFoto;
    }

    public void setProfielFoto(String profielFoto) {
        this.profielFoto = profielFoto;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public List<Kweet> getHartjes() {
        return hartjes;
    }

    public void setHartjes(List<Kweet> hartjes) {
        this.hartjes = hartjes;
    }

    public List<Kwetteraar> getVolgers() {
        return volgers;
    }

    public void setVolgers(List<Kwetteraar> volgers) {
        this.volgers = volgers;
    }

    public List<Kwetteraar> getLeiders() {
        return leiders;
    }

    public void setLeiders(List<Kwetteraar> leiders) {
        this.leiders = leiders;
    }

    public List<Kweet> getMentions() {
        return mentions;
    }

    public void setMentions(List<Kweet> mentions) {
        this.mentions = mentions;
    }

    public void addKweet(Kweet kweet) {
        if (kweet != null && kweets != null && !kweets.contains(kweet)) {
            kweets.add(kweet);
            if (kweet.getEigenaar() != this)
                kweet.setEigenaar(this);
        }
    }

    public void addHartje(Kweet hartje) {
        if (hartje != null && hartjes != null && !hartjes.contains(hartje)) {
            hartjes.add(hartje);
            if (!hartje.getHartjes().contains(this))
                hartje.addHartje(this);
        }
    }

    public void addVolger(Kwetteraar volger) {
        if (volger != null && volgers != null && !volgers.contains(volger)) {
            volgers.add(volger);
            if (!volger.getLeiders().contains(this))
                volger.addLeider(this);
        }
    }

    public void addLeider(Kwetteraar leider) {
        if (leider != null && leiders != null && !leiders.contains(leider)) {
            leiders.add(leider);
            if (!leider.getVolgers().contains(this))
                leider.addVolger(this);
        }
    }

    public void addMention(Kweet mention) {
        if (mention != null && mentions != null && !mentions.contains(mention)) {
            mentions.add(mention);
            if (!mention.getMentions().contains(this))
                mention.addMention(this);
        }
    }
}
