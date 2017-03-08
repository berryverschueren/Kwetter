package controller;

import model.Locatie;
import service.KwetterService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Berry-PC on 08/03/2017.
 */

@Path("/locatie")
public class LocatieAPI {
    KwetterService kwetterService = new KwetterService();

    @GET
    @Path("/get/all")
    @Produces(APPLICATION_JSON)
    public List<Locatie> getAllLocaties() { return kwetterService.getLocatieBaseService().getLocaties(); }

    @GET
    @Path("/get/id/{id}")
    @Produces(APPLICATION_JSON)
    public Locatie getLocatieById(@PathParam("id") long id) {
        return kwetterService.getLocatieBaseService().getLocatie(id);
    }

    @GET
    @Path("/get/name/{name}")
    @Produces(APPLICATION_JSON)
    public Locatie getLocatieByName(@PathParam("name") String name) {
        return kwetterService.getLocatieBaseService().getExactlyMatchingLocatie(name);
    }
}
