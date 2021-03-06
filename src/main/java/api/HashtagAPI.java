package api;

import dto.DTOConverter;
import dto.DetailedHashtagDTO;
import dto.HashtagDTO;
import model.Hashtag;
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

@Path("/hashtag")
public class HashtagAPI {
    KwetterService kwetterService;

    @Inject
    public HashtagAPI (KwetterService ks) {
        kwetterService = ks;
    }

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<HashtagDTO> getAllHashtags(@Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return hashtagListToDTO(kwetterService.getHashtagBaseService().getHashtags()); }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public HashtagDTO getHashtagById(@PathParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Hashtag hashtag = kwetterService.getHashtagBaseService().getHashtag(id);
        DetailedHashtagDTO kdto = new DetailedHashtagDTO();
        if (hashtag != null)
            kdto.fromHashtag(hashtag);
        return kdto;
    }

    @GET
    @Path("/get/one/content/{name}")
    @Produces(APPLICATION_JSON)
    public HashtagDTO getHashtagByName(@PathParam("name") String name, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Hashtag hashtag = kwetterService.getHashtagBaseService().getExactlyMatchingHashtag(name);
        DetailedHashtagDTO kdto = new DetailedHashtagDTO();
        if (hashtag != null)
            kdto.fromHashtag(hashtag);
        return kdto;
    }

    @GET
    @Path("/get/more/content/{name}")
    @Produces(APPLICATION_JSON)
    public List<HashtagDTO> getHashtagsByName(@PathParam("name") String name, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return hashtagListToDTO(kwetterService.getHashtagBaseService().getMatchingHashtags(name));
    }

    @POST
    @Path("/post/insert")
    @Produces(APPLICATION_JSON)
    public HashtagDTO createHashtag(@FormParam("name") String name, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Hashtag hashtag = kwetterService.getHashtagBaseService().insertHashtag(name);
        DetailedHashtagDTO kdto = new DetailedHashtagDTO();
        if (hashtag != null)
            kdto.fromHashtag(hashtag);
        return kdto;
    }

    @POST
    @Path("/post/update")
    @Produces(APPLICATION_JSON)
    public HashtagDTO updateHashtag(@FormParam("id") long id, @FormParam("name") String name, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Hashtag hashtag = kwetterService.getHashtagBaseService().updateHashtag(id, name);
        DetailedHashtagDTO kdto = new DetailedHashtagDTO();
        if (hashtag != null)
            kdto.fromHashtag(hashtag);
        return kdto;
    }

    @POST
    @Path("/post/delete")
    @Produces(APPLICATION_JSON)
    public List<HashtagDTO> deleteHashtag(@FormParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        kwetterService.getHashtagBaseService().deleteHashtag(id);
        return hashtagListToDTO(kwetterService.getHashtagBaseService().getHashtags());
    }

    public List<HashtagDTO> hashtagListToDTO(List<Hashtag> hashtagList) {
        List<HashtagDTO> hashtagDTOList = new ArrayList<>();
        DTOConverter.toHashtagDTOList(hashtagList, hashtagDTOList);
        return hashtagDTOList;
    }
}
