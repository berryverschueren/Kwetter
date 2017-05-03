package hateoas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.messaging.jmq.io.Status;
import dto.integration.HateoasDetailedKwetteraarDto;
import dto.integration.HateoasKwetteraarDto;
import dto.integration.HateoasKwetteraarListDto;
import dto.integration.HateoasLocatieDto;
import model.Kwetteraar;
import model.Locatie;
import serializer.CustomObjectMapper;
import service.KwetterService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 26/04/2017.
 */

@Path("/hateoas")
public class HATEOAS {
    private KwetterService kwetterService;
    private static final String LINK_SELF = "self";
    private static final String LINK_SELF_BY_ID = "selfById";
    private static final String LINK_SELF_BY_NAME = "selfByName";
    private static final CustomObjectMapper OBJECT_MAPPER = new CustomObjectMapper();

    @Inject
    public HATEOAS(KwetterService ks) {
        kwetterService = ks;
    }

    @GET
    @Path("/kwetteraars")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKwetteraars(@Context UriInfo uriInfo) {
        // Get from database.
        List<Kwetteraar> kwetteraars = kwetterService.getKwetteraarBaseService().getKwetteraars();

        // Check if null + respond appropriately.
        if (kwetteraars == null)
            return Response
                    .status(404)
                    .entity("List of kwetteraars == null.")
                    .build();

        // Convert to DTO and add links.
        List<HateoasKwetteraarDto> wrappedKwetteraars = buildHateoasKwetteraarDtoList(uriInfo, kwetteraars);

        // Wrap in list.
        HateoasKwetteraarListDto hateoasKwetteraarListDto = new HateoasKwetteraarListDto(wrappedKwetteraars);
        Link link = buildCustomLink(uriInfo, "/kwetteraars");
        hateoasKwetteraarListDto.getLinks().add(link);

        // Respond.
        try {
            String json = OBJECT_MAPPER.writeValueAsString(hateoasKwetteraarListDto);
            return Response
                    .status(Status.OK)
                    .entity(json)
                    .build();
        } catch (JsonProcessingException ex) {
            return Response
                    .status(Status.ERROR)
                    .entity("Exception occurred: " + ex.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/kwetteraars/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKwetteraar(@Context UriInfo uriInfo, @PathParam("id") long id) {
        // Get from database.
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraar(id);

        // Check if null + respond appropriately.
        if (kwetteraar == null)
            return Response
                    .status(404)
                    .entity("kwetteraar == null.")
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();

        // Convert to DTO and add links.
        HateoasDetailedKwetteraarDto wrappedKwetteraar = buildHateoasDetailedKwetteraarDto(uriInfo, kwetteraar);

        // Respond.
        try {
            String json = OBJECT_MAPPER.writeValueAsString(wrappedKwetteraar);
            return Response
                    .status(Status.OK)
                    .entity(json)
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();
        } catch (JsonProcessingException ex) {
            return Response
                    .status(Status.ERROR)
                    .entity("Exception occurred: " + ex.getMessage())
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();
        }
    }

    @GET
    @Path("/kwetteraars/profielNaam/{profielNaam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKwetteraar(@Context UriInfo uriInfo, @PathParam("profielNaam") String profielNaam) {
        // Get from database.
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(profielNaam);

        // Check if null + respond appropriately.
        if (kwetteraar == null)
            return Response
                    .status(404)
                    .entity("kwetteraar == null.")
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();

        // Convert to DTO and add links.
        HateoasDetailedKwetteraarDto wrappedKwetteraar = buildHateoasDetailedKwetteraarDto(uriInfo, kwetteraar);

        // Respond.
        try {
            String json = OBJECT_MAPPER.writeValueAsString(wrappedKwetteraar);
            return Response
                    .status(Status.OK)
                    .entity(json)
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();
        } catch (JsonProcessingException ex) {
            return Response
                    .status(Status.ERROR)
                    .entity("Exception occurred: " + ex.getMessage())
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();
        }
    }

    @GET
    @Path("/locaties/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocatie(@Context UriInfo uriInfo, @PathParam("id") long id) {
        // Get from database.
        Locatie locatie = kwetterService.getLocatieBaseService().getLocatie(id);

        // Check if null + respond appropriately.
        if (locatie == null)
            return Response
                    .status(404)
                    .entity("locatie == null.")
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();

        // Convert to DTO and add links.
        HateoasLocatieDto wrappedLocatie = buildHateoasLocatieDto(uriInfo, locatie);

        // Respond.
        try {
            String json = OBJECT_MAPPER.writeValueAsString(wrappedLocatie);
            return Response
                    .status(Status.OK)
                    .entity(json)
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();
        } catch (JsonProcessingException ex) {
            return Response
                    .status(Status.ERROR)
                    .entity("Exception occurred: " + ex.getMessage())
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();
        }
    }

    @GET
    @Path("/locaties/plaatsNaam/{plaatsNaam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocatie(@Context UriInfo uriInfo, @PathParam("plaatsNaam") String plaatsNaam) {
        // Get from database.
        Locatie locatie = kwetterService.getLocatieBaseService().getExactlyMatchingLocatie(plaatsNaam);

        // Check if null + respond appropriately.
        if (locatie == null)
            return Response
                    .status(404)
                    .entity("locatie == null.")
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();

        // Convert to DTO and add links.
        HateoasLocatieDto wrappedLocatie = buildHateoasLocatieDto(uriInfo, locatie);

        // Respond.
        try {
            String json = OBJECT_MAPPER.writeValueAsString(wrappedLocatie);
            return Response
                    .status(Status.OK)
                    .entity(json)
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();
        } catch (JsonProcessingException ex) {
            return Response
                    .status(Status.ERROR)
                    .entity("Exception occurred: " + ex.getMessage())
                    .header("Access-Control-Allow-Origin" , "*")
                    .build();
        }
    }

    private List<HateoasKwetteraarDto> buildHateoasKwetteraarDtoList(UriInfo uriInfo, List<Kwetteraar> kwetteraars) {
        List<HateoasKwetteraarDto> wrappedKwetteraars = new ArrayList<>();
        for (Kwetteraar kwetteraar : kwetteraars) {
            HateoasKwetteraarDto wrappedKwetteraar = buildHateoasKwetteraarDto(uriInfo, kwetteraar);
            wrappedKwetteraars.add(wrappedKwetteraar);
        }
        return wrappedKwetteraars;
    }

    private HateoasKwetteraarDto buildHateoasKwetteraarDto(UriInfo uriInfo, Kwetteraar kwetteraar) {
        HateoasKwetteraarDto wrappedKwetteraar = new HateoasKwetteraarDto(kwetteraar);
        Link link3 = buildKwetteraarSelfLinkById(uriInfo, wrappedKwetteraar.getId());
        Link link4 = buildKwetteraarSelfLinkByName(uriInfo, wrappedKwetteraar.getProfielNaam());
        wrappedKwetteraar.getLinks().add(link3);
        wrappedKwetteraar.getLinks().add(link4);
        return wrappedKwetteraar;
    }

    private HateoasDetailedKwetteraarDto buildHateoasDetailedKwetteraarDto(UriInfo uriInfo, Kwetteraar kwetteraar) {
        List<HateoasKwetteraarDto> wrappedVolgers = buildHateoasKwetteraarDtoList(uriInfo, kwetteraar.getVolgers());
        List<HateoasKwetteraarDto> wrappedLeiders = buildHateoasKwetteraarDtoList(uriInfo, kwetteraar.getLeiders());
        HateoasLocatieDto wrappedLocatie = buildHateoasLocatieDto(uriInfo, kwetteraar.getLocatie());
        HateoasDetailedKwetteraarDto wrappedKwetteraar = new HateoasDetailedKwetteraarDto(kwetteraar, wrappedVolgers, wrappedLeiders, wrappedLocatie);
        Link link3 = buildKwetteraarSelfLinkById(uriInfo, wrappedKwetteraar.getId());
        Link link4 = buildKwetteraarSelfLinkByName(uriInfo, wrappedKwetteraar.getProfielNaam());
        wrappedKwetteraar.getLinks().add(link3);
        wrappedKwetteraar.getLinks().add(link4);
        return wrappedKwetteraar;
    }

    private HateoasLocatieDto buildHateoasLocatieDto(UriInfo uriInfo, Locatie locatie) {
        HateoasLocatieDto wrappedLocatie = new HateoasLocatieDto(locatie);
        Link link1 = buildLocatieSelfLinkById(uriInfo, wrappedLocatie.getId());
        Link link2 = buildLocatieSelfLinkByName(uriInfo, wrappedLocatie.getPlaatsNaam());
        wrappedLocatie.getLinks().add(link1);
        wrappedLocatie.getLinks().add(link2);
        return wrappedLocatie;
    }

    private Link buildCustomLink(UriInfo uriInfo, String customAddition) {
        URI uri = uriInfo.getBaseUriBuilder().path(HATEOAS.class).path(customAddition).build();
        Link link = Link.fromUri(uri).rel(LINK_SELF).type(MediaType.APPLICATION_JSON).build();
        return link;
    }

    private Link buildKwetteraarSelfLinkById(UriInfo uriInfo, long id) {
        URI uri = uriInfo.getBaseUriBuilder().path(HATEOAS.class).path("/kwetteraars/id/").path("" + id).build();
        Link link = Link.fromUri(uri).rel(LINK_SELF_BY_ID).type(MediaType.APPLICATION_JSON).build();
        return link;
    }

    private Link buildKwetteraarSelfLinkByName(UriInfo uriInfo, String profielNaam) {
        URI uri = uriInfo.getBaseUriBuilder().path(HATEOAS.class).path("/kwetteraars/profielNaam/").path("" + profielNaam).build();
        Link link = Link.fromUri(uri).rel(LINK_SELF_BY_NAME).type(MediaType.APPLICATION_JSON).build();
        return link;
    }

    private Link buildLocatieSelfLinkById(UriInfo uriInfo, long id) {
        URI uri = uriInfo.getBaseUriBuilder().path(HATEOAS.class).path("/locaties/id/").path("" + id).build();
        Link link = Link.fromUri(uri).rel(LINK_SELF_BY_ID).type(MediaType.APPLICATION_JSON).build();
        return link;
    }

    private Link buildLocatieSelfLinkByName(UriInfo uriInfo, String plaatsNaam) {
        URI uri = uriInfo.getBaseUriBuilder().path(HATEOAS.class).path("/locaties/plaatsNaam/").path("" + plaatsNaam).build();
        Link link = Link.fromUri(uri).rel(LINK_SELF_BY_NAME).type(MediaType.APPLICATION_JSON).build();
        return link;
    }

}
