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

    // TODO Inject through beans.
    KwetterService kwetterService = new KwetterService();

    @GET
    @Path("/get/all")
    @Produces(APPLICATION_JSON)
    public List<Kwetteraar> getAllKwetteraars() {
        return kwetterService.getKwetteraars();
    }

    @GET
    @Path("/get/{id}")
    @Produces(APPLICATION_JSON)
    public Kwetteraar getKwetteraar(@PathParam("id") long id) {
        return kwetterService.getKwetteraar(id);
    }

    @GET
    @Path("/registreer/{gebruikersnaam}")
    public void registreren(@PathParam("gebruikersnaam") String gebruikersnaam) {
        kwetterService.registreren(gebruikersnaam, "test");
    }
}
