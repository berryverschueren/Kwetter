package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 24/02/2017.
 */
public class Kwetteraar {

    private long id;
    private String profielNaam;
    private String profielFoto;
    private String bio;
    private String website;
    private String wachtwoord;
    private Rol rol;
    private Locatie locatie;
    private List<Kweet> kweets;
    private List<Kweet> hartjes;
    private List<Kweet> mentions;
    private List<Kwetteraar> volgers;
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
        if (kweet != null && kweets != null)
            kweets.add(kweet);
    }

    public void addHartje(Kweet hartje) {
        if (hartje != null && hartjes != null)
            hartjes.add(hartje);
    }

    public void addVolger(Kwetteraar volger) {
        if (volger != null && volgers != null)
            volgers.add(volger);
    }

    public void addLeider(Kwetteraar leider) {
        if (leider != null && leiders != null)
            leiders.add(leider);
    }

    public void addMention(Kweet mention) {
        if (mention != null && mentions != null)
            mentions.add(mention);
    }
}
