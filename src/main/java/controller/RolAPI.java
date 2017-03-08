package controller;

import model.Rol;
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

@Path("/rol")
public class RolAPI {
    KwetterService kwetterService = new KwetterService();

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<Rol> getAllRollen() { return kwetterService.getRolBaseService().getRollen(); }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public Rol getRolById(@PathParam("id") long id) {
        return kwetterService.getRolBaseService().getRol(id);
    }

    @GET
    @Path("/get/one/name/{name}")
    @Produces(APPLICATION_JSON)
    public Rol getRolByName(@PathParam("name") String name) {
        return kwetterService.getRolBaseService().getExactlyMatchingRol(name);
    }
}
