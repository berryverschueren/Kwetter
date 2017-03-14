package controller;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

/**
 * Created by Berry-PC on 09/03/2017.
 */

@Path("/test")
@RolesAllowed({"user", "admin"})
public class TestAPI {

    @GET
    @Path("/xmlbye")
    @Produces("text/plain;charset=UTF-8")
    public void sayBye(@Context HttpServletRequest request) {
        try {
            request.logout();
            request.getSession().invalidate();
        }
        catch (Exception x) {
            System.out.println(x);
        }
    }

    @GET
    @Path("/xmlhello")
    @Produces("text/plain;charset=UTF-8")
    public String sayHelloXml(@Context HttpServletRequest request, @Context Response response) {
        String username = request.getRemoteUser();
        return "HELLO WORLD! Welcome, " + username;
    }
}
