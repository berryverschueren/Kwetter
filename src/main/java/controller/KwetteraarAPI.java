package controller;

import model.Kwetteraar;
import service.KwetterService;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Berry-PC on 07/03/2017.
 */

@Path("/kwetteraar")
public class KwetteraarAPI {
    KwetterService kwetterService = new KwetterService();

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<Kwetteraar> getAllKwetteraars() {
        return kwetterService.getKwetteraarBaseService().getKwetteraars();
    }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public Kwetteraar getKwetteraarById(@PathParam("id") long id) {
        return kwetterService.getKwetteraarBaseService().getKwetteraar(id);
    }

    @GET
    @Path("/get/one/name/{name}")
    @Produces(APPLICATION_JSON)
    public Kwetteraar getKwetteraarByName(@PathParam("name") String name) {
        return kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(name);
    }

    @GET
    @Path("/get/more/kwetteraarid/leiders/{id}")
    @Produces(APPLICATION_JSON)
    public List<Kwetteraar> getKwetteraarLeidersById(@PathParam("id") long id) {
        return kwetterService.getKwetteraarBaseService().getLeiders(id);
    }

    @GET
    @Path("/get/more/kwetteraarid/volgers/{id}")
    @Produces(APPLICATION_JSON)
    public List<Kwetteraar> getKwetteraarVolgersById(@PathParam("id") long id) {
        return kwetterService.getKwetteraarBaseService().getVolgers(id);
    }
}
