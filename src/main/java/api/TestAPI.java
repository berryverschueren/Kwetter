package api;

import model.*;
import service.KwetterService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */

@Path("/test")
@RolesAllowed({"user", "admin"})
public class TestAPI {
    KwetterService kwetterService;

    @Inject
    public TestAPI (KwetterService ks) {
        kwetterService = ks;
    }

    @GET
    @Path("/xmlhello")
    @Produces("text/plain;charset=UTF-8")
    public String sayHelloXml(@Context HttpServletRequest request, @Context Response response) {
        String username = request.getRemoteUser();

        List<Kwetteraar> kwetteraarList = kwetterService.getKwetteraarBaseService().getKwetteraars();
        List<Kweet> kweetList = kwetterService.getKweetBaseService().getKweets();
        List<Hashtag> hashtagList = kwetterService.getHashtagBaseService().getHashtags();
        List<Locatie> locatieList = kwetterService.getLocatieBaseService().getLocaties();
        List<Rol> rolList = kwetterService.getRolBaseService().getRollen();

        kweetList.forEach(k->kwetterService.getKweetBaseService().deleteKweet(k.getId()));
        kwetteraarList.forEach(k->kwetterService.getKwetteraarBaseService().deleteKwetteraar(k.getId()));
        hashtagList.forEach(h->kwetterService.getHashtagBaseService().deleteHashtag(h.getId()));
        locatieList.forEach(l->kwetterService.getLocatieBaseService().deleteLocatie(l.getId()));
        rolList.forEach(r->kwetterService.getRolBaseService().deleteRol(r.getId()));

        return "Welcome, " + username + ", the entire database was cleared just for you!";
    }
}
