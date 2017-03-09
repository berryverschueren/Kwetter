package controller;

import dto.KweetDTO;
import model.Kweet;
import service.KwetterService;

import javax.ws.rs.*;

import java.util.ArrayList;
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
    public List<KweetDTO> getAllKweets() {
        List<Kweet> kweetList = kwetterService.getKweetBaseService().getKweets();
        List<KweetDTO> kweetDTOList = new ArrayList<>();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            kweetDTOList.add(kdto);
        });
        return kweetDTOList;
    }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public KweetDTO getKweetById(@PathParam("id") long id) {
        Kweet kweet = kwetterService.getKweetBaseService().getKweet(id);
        KweetDTO kdto = new KweetDTO();
        kdto.fromKweet(kweet);
        return kdto;
    }

    @GET
    @Path("/get/more/content/{content}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByContent(@PathParam("content") String content) {
        List<Kweet> kweetList = kwetterService.getKweetBaseService().getMatchesByInhoud(content);
        List<KweetDTO> kweetDTOList = new ArrayList<>();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            kweetDTOList.add(kdto);
        });
        return kweetDTOList;
    }

    @GET
    @Path("/get/more/hashtagid/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByHashtagId(@PathParam("id") long id) {
        List<Kweet> kweetList = kwetterService.getKweetBaseService().getKweetByHashtagId(id);
        List<KweetDTO> kweetDTOList = new ArrayList<>();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            kweetDTOList.add(kdto);
        });
        return kweetDTOList;
    }

    @GET
    @Path("/get/more/mentionid/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByMentionId(@PathParam("id") long id) {
        List<Kweet> kweetList = kwetterService.getKweetBaseService().getKweetsByMentionId(id);
        List<KweetDTO> kweetDTOList = new ArrayList<>();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            kweetDTOList.add(kdto);
        });
        return kweetDTOList;
    }

    @GET
    @Path("/get/more/kwetteraarid/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByKwetteraarId(@PathParam("id") long id) {
        List<Kweet> kweetList = kwetterService.getKweetBaseService().getKweetsByKwetteraarId(id);
        List<KweetDTO> kweetDTOList = new ArrayList<>();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            kweetDTOList.add(kdto);
        });
        return kweetDTOList;
    }

    @GET
    @Path("/get/more/kwetteraarid/recent/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getRecenteKweetsByKwetteraarId(@PathParam("id") long id) {
        List<Kweet> kweetList = kwetterService.getKweetBaseService().getRecenteEigenKweetsByKwetteraarId(id);
        List<KweetDTO> kweetDTOList = new ArrayList<>();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            kweetDTOList.add(kdto);
        });
        return kweetDTOList;
    }

    @GET
    @Path("/get/more/kwetteraarid/timeline/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getRecenteLeiderKweetsByKwetteraarId(@PathParam("id") long id) {
        List<Kweet> kweetList = kwetterService.getEigenEnLeiderKweets(id);
        List<KweetDTO> kweetDTOList = new ArrayList<>();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            kweetDTOList.add(kdto);
        });
        return kweetDTOList;
    }

    @GET
    @Path("/get/more/hashtagcontent/{content}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getTrends(@PathParam("content") String content) {
        List<Kweet> kweetList = kwetterService.getTrends(content);
        List<KweetDTO> kweetDTOList = new ArrayList<>();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            kweetDTOList.add(kdto);
        });
        return kweetDTOList;
    }

    @POST
    @Path("/post/insert")
    @Produces(APPLICATION_JSON)
    public KweetDTO insertKweet(@FormParam("id") long id, @FormParam("content") String content) {
        Kweet kweet = kwetterService.stuurKweet(id, content);
        KweetDTO kdto = new KweetDTO();
        kdto.fromKweet(kweet);
        return kdto;
    }

    @POST
    @Path("/post/delete")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> deleteKweet(@FormParam("id") long id) {
        kwetterService.getKweetBaseService().deleteKweet(id);
        List<Kweet> kweetList = kwetterService.getKweetBaseService().getKweets();
        List<KweetDTO> kweetDTOList = new ArrayList<>();
        kweetList.forEach(k->{
            KweetDTO kdto = new KweetDTO();
            kdto.fromKweet(k);
            kweetDTOList.add(kdto);
        });
        return kweetDTOList;
    }
}
