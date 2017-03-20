package controller;

import logger.Logger;
import model.Kwetteraar;
import service.KwetterService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Created by Berry-PC on 20/03/2017.
 */

@ManagedBean(name = "startController", eager = true)
public class StartController {

    private KwetterService kwetterService;
    private Kwetteraar kwetteraar;

    public StartController() {
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
}
