package controller;

import logger.Logger;
import model.Hashtag;
import model.Kweet;
import model.Kwetteraar;
import service.KwetterService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;

/**
 * Created by Berry-PC on 15/03/2017.
 */

@ManagedBean(name = "profileController", eager = true)
@ViewScoped
public class ProfileController {

    private KwetterService kwetterService;
    private Kwetteraar kwetteraar;
    private String newKweetContent;
    private List<Kweet> kweetList;
    private String kweetSearchString;
    private String getRequestKwetteraarNaam;
    private String getRequestKwetteraarId;

    public ProfileController() {
        // Empty constructor for dependency injection purposes.
    }

    @PostConstruct
    public void postContructor() {
        //dd
    }

    public void onload() {
        try {
            if (getRequestKwetteraarId != null && !getRequestKwetteraarId.isEmpty()) {
                long kwetteraarId = Long.parseLong(getRequestKwetteraarId);
                kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraar(kwetteraarId);
            }
            if (getRequestKwetteraarNaam != null && !getRequestKwetteraarNaam.isEmpty()) {
                kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(getRequestKwetteraarNaam);
            }
            if (kwetteraar == null) {
                kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
                getTimeline();
            }
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

    public void likeKweet() {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String kweetIdString = params.get("kweetId");
        long kweetId = Long.parseLong(kweetIdString);
        kwetterService.geefHartje(kwetteraar.getId(), kweetId);
    }

    public void followKwetteraar() {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String kwetteraarIdString = params.get("kwetteraarId");
        long kkk = Long.parseLong(kwetteraarIdString);
        Kwetteraar loggedInkwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        kwetterService.getKwetteraarBaseService().addVolger(loggedInkwetteraar.getId(), kkk);
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

    public String getKweetSearchString() {
        return kweetSearchString;
    }

    public void setKweetSearchString(String kweetSearchString) {
        this.kweetSearchString = kweetSearchString;
    }

    public void searchKweets() {
        List<Kweet> kweets = kwetterService.getKweetBaseService().getMatchesByInhoud(kweetSearchString);
        setKweetSearchString(null);
        kweetList = kweets;
    }

    public String getGetRequestKwetteraarNaam() {
        return getRequestKwetteraarNaam;
    }

    public void setGetRequestKwetteraarNaam(String getRequestKwetteraarNaam) {
        this.getRequestKwetteraarNaam = getRequestKwetteraarNaam;
    }

    public String getGetRequestKwetteraarId() {
        return getRequestKwetteraarId;
    }

    public void setGetRequestKwetteraarId(String getRequestKwetteraarId) {
        this.getRequestKwetteraarId = getRequestKwetteraarId;
    }
}
