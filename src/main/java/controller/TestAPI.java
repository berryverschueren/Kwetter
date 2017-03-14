package controller;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.net.URI;

/**
 * Created by Berry-PC on 09/03/2017.
 */

@Path("/test")
@RolesAllowed({"user", "admin"})
public class TestAPI {

    @GET
    @Path("/xmlbye")
    @Produces("text/plain;charset=UTF-8")
    public Response sayBye(@Context HttpServletRequest request, @Context Response response) {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            request.logout();
            session.invalidate();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        URI uri = URI.create(request.getContextPath() + "/rest/kwetteraar/get/more");

        return Response.seeOther(uri).build();
    }

    @GET
    @Path("/xmlhello")
    @Produces("text/plain;charset=UTF-8")
    public Response sayHelloXml(@Context HttpServletRequest request, @Context Response response) {
        CacheControl cc = new CacheControl();
        cc.setNoCache(true);

        String username = request.getRemoteUser();
        String returnString = "HELLO WORLD! Welcome, " + username;

        ResponseBuilder builder = Response.ok(returnString);
        builder.cacheControl(cc);

        return builder.build();
    }
}
