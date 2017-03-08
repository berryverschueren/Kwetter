package controller;

import model.Kweet;
import service.KwetterService;

import javax.ws.rs.*;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Berry-PC on 08/03/2017.
 */

@Path("/kweet")
public class KweetAPI {
    KwetterService kwetterService = new KwetterService();

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getAllKweets() { return kwetterService.getKweetBaseService().getKweets(); }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public Kweet getKweetById(@PathParam("id") long id) {
        return kwetterService.getKweetBaseService().getKweet(id);
    }

    @GET
    @Path("/get/more/content/{content}")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getKweetsByContent(@PathParam("content") String content) {
        return kwetterService.getKweetBaseService().getMatchesByInhoud(content);
    }

    @GET
    @Path("/get/more/hashtagid/{id}")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getKweetsByHashtagId(@PathParam("id") long id) {
        return kwetterService.getKweetBaseService().getKweetByHashtagId(id);
    }

    @GET
    @Path("/get/more/mentionid/{id}")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getKweetsByMentionId(@PathParam("id") long id) {
        return kwetterService.getKweetBaseService().getKweetsByMentionId(id);
    }

    @GET
    @Path("/get/more/kwetteraarid/{id}")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getKweetsByKwetteraarId(@PathParam("id") long id) {
        return kwetterService.getKweetBaseService().getKweetsByKwetteraarId(id);
    }

    @GET
    @Path("/get/more/kwetteraarid/recent/{id}")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getRecenteKweetsByKwetteraarId(@PathParam("id") long id) {
        return kwetterService.getKweetBaseService().getRecenteEigenKweetsByKwetteraarId(id);
    }

    @GET
    @Path("/get/more/kwetteraarid/timeline/{id}")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getRecenteLeiderKweetsByKwetteraarId(@PathParam("id") long id) {
        return kwetterService.getEigenEnLeiderKweets(id);
    }

    @GET
    @Path("/get/more/hashtagcontent/{content}")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getTrends(@PathParam("content") String content) {
        return kwetterService.getTrends(content);
    }
}
