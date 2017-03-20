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
    private Kwetteraar kwetteraar;

    public ProfileController() {
        // Empty constructor for dependency injection purposes.
    }

    @PostConstruct
    public void postContructor() {
        try {
            kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }
        catch (Exception x) {
            System.out.println(x);
        }
    }

    @Inject
    public void setKwetterService(KwetterService ks) {
        kwetterService = ks;
    }

    public KwetterService getKwetterService() {
        return kwetterService;
    }

    public Kwetteraar getKwetteraar() {
        return kwetteraar;
    }

    public void setKwetteraar(Kwetteraar kwetteraar) {
        this.kwetteraar = kwetteraar;
    }
}
