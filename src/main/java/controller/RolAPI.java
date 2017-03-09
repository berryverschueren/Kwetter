package controller;

import model.Rol;
import service.KwetterService;

import javax.ws.rs.*;
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

    @POST
    @Path("/post/insert")
    @Produces(APPLICATION_JSON)
    public Rol createRol(@FormParam("name") String name) {
        return kwetterService.getRolBaseService().insertRol(name);
    }

    @POST
    @Path("/post/update")
    @Produces(APPLICATION_JSON)
    public Rol updateRol(@FormParam("id") long id, @FormParam("name") String name) {
        return kwetterService.getRolBaseService().updateRol(id, name);
    }

    @POST
    @Path("/post/delete")
    @Produces(APPLICATION_JSON)
    public List<Rol> deleteRol(@FormParam("id") long id) {
        kwetterService.getRolBaseService().deleteRol(id);
        return kwetterService.getRolBaseService().getRollen();
    }
}