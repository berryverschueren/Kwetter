package hateoas;

import dto.KwetteraarDTO;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import service.KwetterService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Berry-PC on 26/04/2017.
 */

@Path("/hateoas")
public class HATEOAS {
    private KwetterService kwetterService;

    @Inject
    public HATEOAS (KwetterService ks) {
        kwetterService = ks;
    }

    @GET
    @Path("/kwetteraars")
    @Produces(APPLICATION_JSON)
    public Response getKwetteraar() {



        return Response.ok().build();
    }

}
