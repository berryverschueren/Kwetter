package dto;

import model.Kweet;
import model.Kwetteraar;
import model.Locatie;
import model.Rol;

import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
public class SingleKwetteraarDTO {
    private long id;
    private String profielNaam;
    private String profielFoto;
    private String bio;
    private String website;
    private Rol rol;
    private Locatie locatie;
    private List<KwetteraarDTO> volgers;
    private List<KwetteraarDTO> leiders;
    private List<KweetDTO> kweets;
    private List<KweetDTO> hartjes;
    private List<KweetDTO> mentions;

    public SingleKwetteraarDTO() {}

    public void fromKwetteraar(Kwetteraar kwetteraar) {
        id = kwetteraar.getId();
        profielNaam = kwetteraar.getProfielNaam();
        profielFoto = kwetteraar.getProfielFoto();
        bio = kwetteraar.getBio();
        website = kwetteraar.getWebsite();
        rol = kwetteraar.getRol();
        locatie = kwetteraar.getLocatie();
        List<Kwetteraar> kwetteraarList = kwetteraar.getVolgers();
        kwetteraarList.forEach(k->{
            KwetteraarDTO kdto = new KwetteraarDTO();
            kdto.fromKwetteraar(k);
            volgers.add(kdto);
        });
        kwetteraarList = kwetteraar.getLeiders();
        kwetteraarList.forEach(k->{
            KwetteraarDTO kdto = new KwetteraarDTO();
            kdto.fromKwetteraar(k);
            leiders.add(kdto);
        });
        List<Kweet> kweetList = kwetteraar.getKweets();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            kweets.add(kdto);
        });
        kweetList = kwetteraar.getHartjes();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            hartjes.add(kdto);
        });
        kweetList = kwetteraar.getMentions();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            mentions.add(kdto);
        });
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

    public List<KwetteraarDTO> getVolgers() {
        return volgers;
    }

    public void setVolgers(List<KwetteraarDTO> volgers) {
        this.volgers = volgers;
    }

    public List<KwetteraarDTO> getLeiders() {
        return leiders;
    }

    public void setLeiders(List<KwetteraarDTO> leiders) {
        this.leiders = leiders;
    }

    public List<KweetDTO> getKweets() {
        return kweets;
    }

    public void setKweets(List<KweetDTO> kweets) {
        this.kweets = kweets;
    }

    public List<KweetDTO> getHartjes() {
        return hartjes;
    }

    public void setHartjes(List<KweetDTO> hartjes) {
        this.hartjes = hartjes;
    }

    public List<KweetDTO> getMentions() {
        return mentions;
    }

    public void setMentions(List<KweetDTO> mentions) {
        this.mentions = mentions;
    }
}
