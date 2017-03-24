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
    private List<Kweet> kweetList;

    public ProfileController() {
        // Empty constructor for dependency injection purposes.
    }

    @PostConstruct
    public void postContructor() {
        try {
            kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            getTimeline();
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

    public void getTimeline() {
        kweetList = kwetterService.getEigenEnLeiderKweets(kwetteraar.getId());
    }

    public void getMentionKweetList() {
        kweetList = kwetterService.getKweetBaseService().getKweetsByMentionId(kwetteraar.getId());
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

    public List<Kweet> getKweetList() {
        return kweetList;
    }

    public void setKweetList(List<Kweet> kweetList) {
        this.kweetList = kweetList;
    }
}
