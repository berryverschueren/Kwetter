package model;

import javax.persistence.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 24/02/2017.
 */
@Entity
@Table(name="t_kwetteraar")
public class Kwetteraar {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "profielNaam", nullable = false, unique = true)
    private String profielNaam;

    @Column(name = "profielfoto")
    private String profielFoto;

    @Column(name = "bio")
    private String bio;

    @Column(name = "website")
    private String website;

    @Column(name = "wachtwoord", nullable = false)
    private String wachtwoord;

    @Column(name= "rol", nullable = false)
    private String rol;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "locatie_id", referencedColumnName = "id")
    private Locatie locatie;

    @OneToMany(mappedBy = "eigenaar", cascade = CascadeType.MERGE)
    private List<Kweet> kweets;

    @ManyToMany(mappedBy="hartjes", cascade = CascadeType.MERGE)
    private List<Kweet> hartjes;

    @ManyToMany(mappedBy="mentions", cascade = CascadeType.MERGE)
    private List<Kweet> mentions;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "t_kwetteraar_kwetteraar_volgers"
            , joinColumns = @JoinColumn(name = "volger_id", referencedColumnName = "id", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "leider_id", referencedColumnName = "id", nullable = false))
    private List<Kwetteraar> volgers;

//    @ManyToMany(mappedBy = "volgers", cascade = CascadeType.MERGE)

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "t_kwetteraar_kwetteraar_leiders"
            , joinColumns = @JoinColumn(name = "leider_id", referencedColumnName = "id", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "volger_id", referencedColumnName = "id", nullable = false))
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
        String hashstring = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(wachtwoord.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            hashstring = hexString.toString();
        }
        catch (Exception x) {
            System.out.println(x);
        }
        this.wachtwoord = (hashstring == null || hashstring.isEmpty()) ? wachtwoord : hashstring;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
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

    public void removeKweet(Kweet kweet) {
        Kweet f = kweets.stream().filter(k -> k.getId() == kweet.getId()).findAny().orElse(null);
        if (kweet != null && kweets != null && kweets.contains(f)) {
            kweets.remove(f);
            if (f.getEigenaar() != this)
                f.setEigenaar(null);
        }
    }

    public void addHartje(Kweet hartje) {
        if (hartje != null && hartjes != null && !hartjes.contains(hartje)) {
            hartjes.add(hartje);
            if (!hartje.getHartjes().contains(this))
                hartje.addHartje(this);
        }
    }

    public void removeHartje(Kweet hartje) {
        Kweet kweet = hartjes.stream().filter(k -> k.getId() == hartje.getId()).findAny().orElse(null);
        if (hartje != null && hartjes != null && hartjes.contains(kweet)) {
            hartjes.remove(kweet);
            if (kweet.getHartjes().contains(this))
                kweet.removeHartje(this);
        }
    }

    public void addVolger(Kwetteraar volger) {
        if (volger != null && volgers != null && !volgers.contains(volger)) {
            volgers.add(volger);
            if (!volger.getLeiders().contains(this))
                volger.addLeider(this);
        }
    }

    public void removeVolger(Kwetteraar volger) {
        if (volger != null && volgers != null && volgers.contains(volger)) {
            volgers.remove(volger);
            if (volger.getLeiders().contains(this))
                volger.removeLeider(this);
        }
    }

    public void addLeider(Kwetteraar leider) {
        if (leider != null && leiders != null && !leiders.contains(leider)) {
            leiders.add(leider);
            if (!leider.getVolgers().contains(this))
                leider.addVolger(this);
        }
    }

    public void removeLeider(Kwetteraar leider) {
        if (leider != null && leiders != null && leiders.contains(leider)) {
            leiders.remove(leider);
            if (leider.getVolgers().contains(this))
                leider.removeVolger(this);
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
