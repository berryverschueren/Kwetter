package controller;

import logger.Logger;
import model.Hashtag;
import model.Kweet;
import model.Kwetteraar;
import service.KwetterService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Created by Berry-PC on 15/03/2017.
 */

@ManagedBean(name = "profileController", eager = true)
public class ProfileController {

    private KwetterService kwetterService;
    private Kwetteraar kwetteraar;
    private String newKweetContent;

    public ProfileController() {
        // Empty constructor for dependency injection purposes.
    }

    @PostConstruct
    public void postContructor() {
        try {
            kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }
        catch (Exception x) {
            Logger.log(x);
        }
    }

    @Inject
    public void setKwetterService(KwetterService ks) {
        kwetterService = ks;
    }

    public Kwetteraar getKwetteraar() {
        return kwetteraar;
    }

    public void setKwetteraar(Kwetteraar kwetteraar) {
        this.kwetteraar = kwetteraar;
    }

    public String getNewKweetContent() {
        return newKweetContent;
    }

    public void setNewKweetContent(String newKweetContent) {
        this.newKweetContent = newKweetContent;
    }

    public Kweet getLastKweet() {
        List<Kweet> kweets = kwetteraar.getKweets();
        kweets.sort(comparing(Kweet::getDatum));
        return kweets.get(kweets.size()-1);
    }

    public List<Kweet> getTimeline() {
        List<Kweet> kweets = kwetterService.getEigenEnLeiderKweets(kwetteraar.getId());
        return kweets;
    }

    public List<Hashtag> getAllTrends() {
        List<Hashtag> hashtags = kwetterService.getHashtagBaseService().getHashtags();
        return hashtags;
    }

    public void stuurKweet() {
        kwetterService.stuurKweet(kwetteraar.getId(), newKweetContent);
        setNewKweetContent(null);
    }

    public List<Kweet> getRecenteKweets() {
        List<Kweet> kweets = kwetterService.getKweetBaseService().getRecenteEigenKweetsByKwetteraarId(kwetteraar.getId());
        return kweets;
    }
}
