package controller;

import model.Hashtag;
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

@Path("/hashtag")
public class HashtagAPI {
    KwetterService kwetterService = new KwetterService();

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<Hashtag> getAllHashtags() { return kwetterService.getHashtagBaseService().getHashtags(); }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public Hashtag getHashtagById(@PathParam("id") long id) {
        return kwetterService.getHashtagBaseService().getHashtag(id);
    }

    @GET
    @Path("/get/one/content/{name}")
    @Produces(APPLICATION_JSON)
    public Hashtag getHashtagByName(@PathParam("name") String name) {
        return kwetterService.getHashtagBaseService().getExactlyMatchingHashtag(name);
    }

    @GET
    @Path("/get/more/content/{name}")
    @Produces(APPLICATION_JSON)
    public List<Hashtag> getHashtagsByName(@PathParam("name") String name) {
        return kwetterService.getHashtagBaseService().getMatchingHashtags(name);
    }
}
