package controller;

import dto.DetailedHashtagDTO;
import dto.DetailedKweetDTO;
import dto.HashtagDTO;
import model.Hashtag;
import service.KwetterService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.xml.soap.Detail;

import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Berry-PC on 08/03/2017.
 */

@Path("/hashtag")
public class HashtagAPI {
    @Inject
    KwetterService kwetterService; // = new KwetterService();

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<HashtagDTO> getAllHashtags() { return hashtagListToDTO(kwetterService.getHashtagBaseService().getHashtags()); }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public HashtagDTO getHashtagById(@PathParam("id") long id) {
        Hashtag hashtag = kwetterService.getHashtagBaseService().getHashtag(id);
        DetailedHashtagDTO kdto = new DetailedHashtagDTO();
        if (hashtag != null)
            kdto.fromHashtag(hashtag);
        return kdto;
    }

    @GET
    @Path("/get/one/content/{name}")
    @Produces(APPLICATION_JSON)
    public HashtagDTO getHashtagByName(@PathParam("name") String name) {
        Hashtag hashtag = kwetterService.getHashtagBaseService().getExactlyMatchingHashtag(name);
        DetailedHashtagDTO kdto = new DetailedHashtagDTO();
        if (hashtag != null)
            kdto.fromHashtag(hashtag);
        return kdto;
    }

    @GET
    @Path("/get/more/content/{name}")
    @Produces(APPLICATION_JSON)
    public List<HashtagDTO> getHashtagsByName(@PathParam("name") String name) {
        return hashtagListToDTO(kwetterService.getHashtagBaseService().getMatchingHashtags(name));
    }

    @POST
    @Path("/post/insert")
    @Produces(APPLICATION_JSON)
    public HashtagDTO createHashtag(@FormParam("name") String name) {
        Hashtag hashtag = kwetterService.getHashtagBaseService().insertHashtag(name);
        DetailedHashtagDTO kdto = new DetailedHashtagDTO();
        if (hashtag != null)
            kdto.fromHashtag(hashtag);
        return kdto;
    }

    @POST
    @Path("/post/update")
    @Produces(APPLICATION_JSON)
    public HashtagDTO updateHashtag(@FormParam("id") long id, @FormParam("name") String name) {
        Hashtag hashtag = kwetterService.getHashtagBaseService().updateHashtag(id, name);
        DetailedHashtagDTO kdto = new DetailedHashtagDTO();
        if (hashtag != null)
            kdto.fromHashtag(hashtag);
        return kdto;
    }

    @POST
    @Path("/post/delete")
    @Produces(APPLICATION_JSON)
    public List<HashtagDTO> deleteHashtag(@FormParam("id") long id) {
        kwetterService.getHashtagBaseService().deleteHashtag(id);
        return hashtagListToDTO(kwetterService.getHashtagBaseService().getHashtags());
    }

    public List<HashtagDTO> hashtagListToDTO(List<Hashtag> hashtagList) {
        List<HashtagDTO> hashtagDTOList = new ArrayList<>();
        DetailedKweetDTO dto = new DetailedKweetDTO();
        dto.hashtagListToDTO(hashtagList, hashtagDTOList);
        return hashtagDTOList;
    }
}
