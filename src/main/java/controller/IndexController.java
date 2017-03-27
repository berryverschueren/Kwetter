package controller;

import logger.Logger;
import model.Kwetteraar;
import service.KwetterService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Berry-PC on 15/03/2017.
 */

@ManagedBean(name = "indexController", eager = true)
public class IndexController {
    private String username;
    private String password;
    private String originalURL;
    private KwetterService kwetterService;

    public IndexController() {
        //Empty constructor for dependency injection purposes.
    }

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/profile.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }

    @Inject
    public void setKwetterService(KwetterService ks) {
        kwetterService = ks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() throws IOException {
        try {
            //gdasgsdgasdgf
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

            try {
                request.login(username, password);
                Kwetteraar kwetteraar = null;
                if (kwetterService.getKwetteraarBaseService().inloggen(username, password))
                    kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(username);
                externalContext.getSessionMap().put("user", kwetteraar);
                externalContext.redirect(originalURL);
            } catch (ServletException e) {
                // Handle unknown username/password in request.login().
                Logger.log(e);
                context.addMessage(null, new FacesMessage("Unknown login"));
            }
        }
        catch (Exception x) {
            Logger.log(x);
        }
    }

    public void logout(){
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.invalidateSession();
            externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        }
        catch (Exception x) {
            Logger.log(x);
        }
    }

}
