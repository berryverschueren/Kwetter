package controller;

import model.Kweet;
import model.Kwetteraar;
import service.KwetterService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Berry-PC on 15/03/2017.
 */

@ManagedBean(name = "profileController", eager = true)
public class profileController {

    private KwetterService ks = new KwetterService();

    private String profielFoto;
    private String profielNaam;
    private String bio;
    private List<Kweet> kweets;
    private List<Kwetteraar> volgers;
    private List<Kwetteraar> leiders;

    public profileController() {
        try {
            setProfielNaam(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            Kwetteraar kwetteraar = ks.getKwetteraarBaseService().getKwetteraarByProfielnaam(getProfielNaam());
            setProfielFoto(kwetteraar.getProfielFoto());
            setBio(kwetteraar.getBio());
            setKweets(kwetteraar.getKweets());
            setVolgers(kwetteraar.getVolgers());
            setLeiders(kwetteraar.getLeiders());
        }
        catch (Exception x) {
            System.out.println(x);
        }
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
