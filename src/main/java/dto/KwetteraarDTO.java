package dto;

import model.Kwetteraar;
import model.Locatie;
import model.Rol;

import java.util.List;

/**
 * Created by Berry-PC on 08/03/2017.
 */
public class KwetteraarDTO {
    private long id;
    private String profielNaam;
    private String profielFoto;
    private String bio;
    private String website;
    private Locatie locatie;

    public KwetteraarDTO() {}

    public void fromKwetteraar(Kwetteraar kwetteraar) {
        id = kwetteraar.getId();
        profielNaam = kwetteraar.getProfielNaam();
        profielFoto = kwetteraar.getProfielFoto();
        bio = kwetteraar.getBio();
        website = kwetteraar.getWebsite();
        locatie = kwetteraar.getLocatie();
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

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }
}
