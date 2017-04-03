package api;

import dto.DTOConverter;
import dto.DetailedKweetDTO;
import dto.KweetDTO;
import model.Kweet;
import model.Kwetteraar;
import service.KwetterService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Berry-PC on 08/03/2017.
 */

@Path("/kweet")
public class KweetAPI {
    KwetterService kwetterService;

    @Inject
    public KweetAPI (KwetterService ks) {
        kwetterService = ks;
    }

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getAllKweets(@Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetListToDTO(kwetterService.getKweetBaseService().getKweets());
    }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public DetailedKweetDTO getKweetById(@PathParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kweet kweet = kwetterService.getKweetBaseService().getKweet(id);
        DetailedKweetDTO kdto = new DetailedKweetDTO();
        if (kweet != null)
            kdto.fromKweet(kweet);
        return kdto;
    }

    @GET
    @Path("/get/more/content/{content}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByContent(@PathParam("content") String content, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetListToDTO(kwetterService.getKweetBaseService().getMatchesByInhoud(content));
    }

    @GET
    @Path("/get/more/hashtagid/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByHashtagId(@PathParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetListToDTO(kwetterService.getKweetBaseService().getKweetByHashtagId(id));
    }

    @GET
    @Path("/get/more/mentionid/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByMentionId(@PathParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetListToDTO(kwetterService.getKweetBaseService().getKweetsByMentionId(id));
    }

    @GET
    @Path("/get/more/kwetteraarid/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getKweetsByKwetteraarId(@PathParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetListToDTO(kwetterService.getKweetBaseService().getKweetsByKwetteraarId(id));
    }

    @GET
    @Path("/get/more/kwetteraarid/recent/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getRecenteKweetsByKwetteraarId(@PathParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetListToDTO(kwetterService.getKweetBaseService().getRecenteEigenKweetsByKwetteraarId(id));
    }

    @GET
    @Path("/get/more/kwetteraarid/timeline/{id}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getRecenteLeiderKweetsByKwetteraarId(@PathParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetListToDTO(kwetterService.getEigenEnLeiderKweets(id));
    }

    @GET
    @Path("/get/more/hashtagcontent/{content}")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> getTrends(@PathParam("content") String content, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetListToDTO(kwetterService.getTrends(content));
    }

    @POST
    @Path("/post/like")
    @Produces(APPLICATION_JSON)
    public DetailedKweetDTO insertKweet(@FormParam("naam") String naam, @FormParam("kweetId") long kweetId, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(naam);
        Kweet kweet = kwetterService.getKweetBaseService().getKweet(kweetId);
        kwetteraar.addHartje(kweet);
        kwetterService.getKwetteraarBaseService().saveKwetteraar(kwetteraar);
        DetailedKweetDTO kdto = new DetailedKweetDTO();
        if (kweet != null)
            kdto.fromKweet(kweet);
        return kdto;
    }

    @POST
    @Path("/post/insert")
    @Produces(APPLICATION_JSON)
    public DetailedKweetDTO insertKweet(@FormParam("naam") String naam, @FormParam("content") String content, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(naam);
        Kweet kweet = kwetterService.stuurKweet(kwetteraar.getId(), content);
        DetailedKweetDTO kdto = new DetailedKweetDTO();
        if (kweet != null)
            kdto.fromKweet(kweet);
        return kdto;
    }

    @POST
    @Path("/post/delete")
    @Produces(APPLICATION_JSON)
    public List<KweetDTO> deleteKweet(@FormParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar kwetteraar = kwetterService.getKweetBaseService().getKweet(id).getEigenaar();
        kwetterService.getKweetBaseService().deleteKweet(id);
        return kweetListToDTO(kwetterService.getKweetBaseService().getKweetsByKwetteraarId(kwetteraar.getId()));
    }

    public List<KweetDTO> kweetListToDTO(List<Kweet> kweetList) {
        List<KweetDTO> kweetDTOList = new ArrayList<>();
        DTOConverter.toKweetDTOList(kweetList, kweetDTOList);
        return kweetDTOList;
    }
}
