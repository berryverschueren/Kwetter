package api;

import model.Locatie;
import service.KwetterService;

import javax.inject.Inject;
import javax.ws.rs.*;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Berry-PC on 08/03/2017.
 */

@Path("/locatie")
public class LocatieAPI {
    @Inject
    KwetterService kwetterService; // = new KwetterService();

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<Locatie> getAllLocaties() { return kwetterService.getLocatieBaseService().getLocaties(); }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public Locatie getLocatieById(@PathParam("id") long id) {
        return kwetterService.getLocatieBaseService().getLocatie(id);
    }

    @GET
    @Path("/get/one/name/{name}")
    @Produces(APPLICATION_JSON)
    public Locatie getLocatieByName(@PathParam("name") String name) {
        return kwetterService.getLocatieBaseService().getExactlyMatchingLocatie(name);
    }

    @POST
    @Path("/post/insert")
    @Produces(APPLICATION_JSON)
    public Locatie createLocatie(@FormParam("name") String name) {
        return kwetterService.getLocatieBaseService().insertLocatie(name);
    }

    @POST
    @Path("/post/update")
    @Produces(APPLICATION_JSON)
    public Locatie updateLocatie(@FormParam("id") long id, @FormParam("name") String name) {
        return kwetterService.getLocatieBaseService().updateLocatie(id, name);
    }

    @POST
    @Path("/post/delete")
    @Produces(APPLICATION_JSON)
    public List<Locatie> deleteLocatie(@FormParam("id") long id) {
        kwetterService.getRolBaseService().deleteRol(id);
        return kwetterService.getLocatieBaseService().getLocaties();
    }
}
