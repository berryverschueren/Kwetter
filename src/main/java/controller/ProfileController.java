package controller;

import model.Kweet;
import model.Kwetteraar;
import model.Locatie;
import service.KwetterService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Berry-PC on 15/03/2017.
 */

@ManagedBean(name = "profileController", eager = true)
public class ProfileController {

    private KwetterService kwetterService;

    private String profielFoto;
    private String profielNaam;
    private String bio;
    private String website;
    private Locatie locatie;
    private List<Kweet> kweets;
    private List<Kwetteraar> volgers;
    private List<Kwetteraar> leiders;

    public ProfileController() {
        // Empty constructor for dependency injection purposes.
    }

    @PostConstruct
    public void postContructor() {
        try {
            setProfielNaam(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(getProfielNaam());
            setProfielFoto(kwetteraar.getProfielFoto());
            setBio(kwetteraar.getBio());
            setKweets(kwetteraar.getKweets());
            setVolgers(kwetteraar.getVolgers());
            setLeiders(kwetteraar.getLeiders());
            setWebsite(kwetteraar.getWebsite());
            setLocatie(kwetteraar.getLocatie());
        }
        catch (Exception x) {
            System.out.println(x);
        }
    }

    @Inject
    public void setKwetterService(KwetterService ks) {
        kwetterService = ks;
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

    public String getProfielFoto() {
        return profielFoto;
    }

    public void setProfielFoto(String profielFoto) {
        this.profielFoto = profielFoto;
    }

    public String getProfielNaam() {
        return profielNaam;
    }

    public void setProfielNaam(String profielNaam) {
        this.profielNaam = profielNaam;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
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
}
