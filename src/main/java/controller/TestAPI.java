package controller;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Context;

/**
 * Created by Berry-PC on 09/03/2017.
 */

@Path("/test")
@RolesAllowed({"user", "admin"})
public class TestAPI {

    @GET
    @Path("/contexthello")
    @Produces("text/plain;charset=UTF-8")
    public String sayHello(@Context SecurityContext sc) {
        if (sc.isUserInRole("admin")) return "HELLO WORLD!";
        throw new SecurityException("User is unauthorized.");
    }

    @GET
    @Path("/annotatedhello")
    @Produces("text/plain;charset=UTF-8")
    @RolesAllowed("admin")
    public String sayHelloAnnotated() {
        return "HELLO WORLD!";
    }

    @GET
    @Path("/xmlhello")
    @Produces("text/plain;charset=UTF-8")
    public String sayHelloXml() {
        return "HELLO WORLD!";
    }
}
