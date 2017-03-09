package controller;

import dto.DetailedKweetDTO;
import dto.DetailedKwetteraarDTO;
import dto.KweetDTO;
import model.Kweet;
import service.KwetterService;

import javax.inject.Inject;
import javax.ws.rs.*;

import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Berry-PC on 08/03/2017.
 */

@Path("/kweet")
public class KweetAPI {
    @Inject
    KwetterService kwetterService; // = new KwetterService();

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getAllKweets() {
        return kweetListToDTO(kwetterService.getKweetBaseService().getKweets());
    }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public DetailedKweetDTO getKweetById(@PathParam("id") long id) {
        Kweet kweet = kwetterService.getKweetBaseService().getKweet(id);
        DetailedKweetDTO kdto = new DetailedKweetDTO();
        if (kweet != null)
            kdto.fromKweet(kweet);
        return kdto;
    }

    @GET
    @Path("/get/more/content/{content}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByContent(@PathParam("content") String content) {
        return kweetListToDTO(kwetterService.getKweetBaseService().getMatchesByInhoud(content));
    }

    @GET
    @Path("/get/more/hashtagid/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByHashtagId(@PathParam("id") long id) {
        return kweetListToDTO(kwetterService.getKweetBaseService().getKweetByHashtagId(id));
    }

    @GET
    @Path("/get/more/mentionid/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByMentionId(@PathParam("id") long id) {
        return kweetListToDTO(kwetterService.getKweetBaseService().getKweetsByMentionId(id));
    }

    @GET
    @Path("/get/more/kwetteraarid/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByKwetteraarId(@PathParam("id") long id) {
        return kweetListToDTO(kwetterService.getKweetBaseService().getKweetsByKwetteraarId(id));
    }

    @GET
    @Path("/get/more/kwetteraarid/recent/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getRecenteKweetsByKwetteraarId(@PathParam("id") long id) {
        return kweetListToDTO(kwetterService.getKweetBaseService().getRecenteEigenKweetsByKwetteraarId(id));
    }

    @GET
    @Path("/get/more/kwetteraarid/timeline/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getRecenteLeiderKweetsByKwetteraarId(@PathParam("id") long id) {
        return kweetListToDTO(kwetterService.getEigenEnLeiderKweets(id));
    }

    @GET
    @Path("/get/more/hashtagcontent/{content}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getTrends(@PathParam("content") String content) {
        return kweetListToDTO(kwetterService.getTrends(content));
    }

    @POST
    @Path("/post/insert")
    @Produces(APPLICATION_JSON)
    public DetailedKweetDTO insertKweet(@FormParam("id") long id, @FormParam("content") String content) {
        Kweet kweet = kwetterService.stuurKweet(id, content);
        DetailedKweetDTO kdto = new DetailedKweetDTO();
        if (kweet != null)
            kdto.fromKweet(kweet);
        return kdto;
    }

    @POST
    @Path("/post/delete")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> deleteKweet(@FormParam("id") long id) {
        kwetterService.getKweetBaseService().deleteKweet(id);
        return kweetListToDTO(kwetterService.getKweetBaseService().getKweets());
    }

    public List<KweetDTO> kweetListToDTO(List<Kweet> kweetList) {
        List<KweetDTO> kweetDTOList = new ArrayList<>();
        DetailedKwetteraarDTO dto = new DetailedKwetteraarDTO();
        dto.kweetListToDTO(kweetList, kweetDTOList);
        return kweetDTOList;
    }
}
