package api;

import dto.DTOConverter;
import dto.DetailedKwetteraarDTO;
import dto.KwetteraarDTO;
import model.Kwetteraar;
import model.Locatie;
import service.KwetterService;
import websocket.SessionLister;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Berry-PC on 07/03/2017.
 */

@Path("/kwetteraar")
public class KwetteraarAPI {
    KwetterService kwetterService;

    @Inject
    public KwetteraarAPI (KwetterService ks) {
        kwetterService = ks;
    }

    @GET
    @Path("/get/rolforuser/{name}")
    @Produces(APPLICATION_JSON)
    public String getHighestRol(@PathParam("name") String name, @Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(name);
        return kwetteraar.getRol();
    }

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<KwetteraarDTO> getAllKwetteraars(@Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kwetteraarListToDTO(kwetterService.getKwetteraarBaseService().getKwetteraars());
    }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO getKwetteraarById(@PathParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraar(id);
        DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
        if (kwetteraar != null)
            kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }

    @GET
    @Path("/get/one/name/{name}")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO getKwetteraarByName(@PathParam("name") String name, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(name);
        DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
        if (kwetteraar != null)
            kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }

    @GET
    @Path("/get/more/kwetteraarid/leiders/{id}")
    @Produces(APPLICATION_JSON)
    public List<KwetteraarDTO> getKwetteraarLeidersById(@PathParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kwetteraarListToDTO(kwetterService.getKwetteraarBaseService().getLeiders(id));
    }

    @GET
    @Path("/get/more/kwetteraarid/volgers/{id}")
    @Produces(APPLICATION_JSON)
    public List<KwetteraarDTO> getKwetteraarVolgersById(@PathParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kwetteraarListToDTO(kwetterService.getKwetteraarBaseService().getVolgers(id));
    }

    @POST
    @Path("/post/insert")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO insertKwetteraar(
            @FormParam("name") String name
            , @FormParam("foto") String foto
            , @FormParam("bio") String bio
            , @FormParam("website") String website
            , @FormParam("rol") String rolTitel
            , @FormParam("locatie") String locatieNaam
            , @FormParam("wachtwoord") String wachtwoord, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar kwetteraar = new Kwetteraar();
        kwetteraar.setProfielNaam(name);
        kwetteraar.setProfielFoto(foto);
        kwetteraar.setBio(bio);
        kwetteraar.setWebsite(website);
        kwetteraar.setWachtwoord(wachtwoord);
        kwetteraar.setRol(rolTitel);
        Locatie locatie = kwetterService.getLocatieBaseService().insertLocatie(locatieNaam);
        kwetteraar.setLocatie(locatie);
        kwetteraar = kwetterService.getKwetteraarBaseService().saveKwetteraar(kwetteraar);
        DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
        if (kwetteraar != null)
            kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }

    @POST
    @Path("/post/switchRole")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO switchRole(
            @FormParam("name") String name, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(name);

        if (kwetteraar != null) {
            if (kwetteraar.getRol().equalsIgnoreCase("admin")) {
                kwetteraar.setRol("user");
            } else {
                kwetteraar.setRol("admin");
            }
            kwetterService.getKwetteraarBaseService().updateKwetteraar(kwetteraar);
        }

        DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
        if (kwetteraar != null)
            kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }

    @POST
    @Path("/post/volg")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO addVolger(@FormParam("naamVolger") String naamVolger, @FormParam("naamLeider") String naamLeider, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar volger = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(naamVolger);
        Kwetteraar leider = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(naamLeider);
        if (volger != null && leider != null) {
            kwetterService.getKwetteraarBaseService().addVolger(volger.getId(), leider.getId());
            DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
            kdto.fromKwetteraar(volger);
            return kdto;
        }
        return new DetailedKwetteraarDTO();
    }

    @POST
    @Path("/post/stopvolg")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO stopVolger(@FormParam("naamVolger") String naamVolger, @FormParam("naamLeider") String naamLeider, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar volger = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(naamVolger);
        Kwetteraar leider = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(naamLeider);
        if (volger != null && leider != null) {
            kwetterService.getKwetteraarBaseService().removeVolger(volger.getId(), leider.getId());
            DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
            kdto.fromKwetteraar(volger);
            return kdto;
        }
        return new DetailedKwetteraarDTO();
    }


    @POST
    @Path("/post/wijzigprofielfoto")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO wijzigProfielfoto(@FormParam("id") int id, @FormParam("nieuweFoto") String foto, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraar(id);
        if (kwetteraar != null) {
            kwetterService.wijzigProfielfoto(id, foto);
            DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
            kdto.fromKwetteraar(kwetteraar);
            return kdto;
        }
        return new DetailedKwetteraarDTO();
    }

    @POST
    @Path("/post/wijzigbio")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO wijzigBio(@FormParam("id") int id, @FormParam("nieuweBio") String bio, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraar(id);
        if (kwetteraar != null) {
            kwetterService.wijzigDetailgegevens(id, bio);
            DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
            kdto.fromKwetteraar(kwetteraar);
            return kdto;
        }
        return new DetailedKwetteraarDTO();
    }

    @POST
    @Path("/post/login")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO inloggen(@FormParam("name") String name, @FormParam("wachtwoord") String wachtwoord, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        String hashstring = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(wachtwoord.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            hashstring = hexString.toString();
        }
        catch (Exception x) {
            System.out.println(x);
        }
        String hashedPassword = (hashstring == null || hashstring.isEmpty()) ? wachtwoord : hashstring;
        if(kwetterService.getKwetteraarBaseService().inloggen(name, hashedPassword)){
            Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(name);

            if (!SessionLister.getInstance().getActiveUsers().contains(name))
                SessionLister.getInstance().getActiveUsers().add(name);

            DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
            if (kwetteraar != null)
                kdto.fromKwetteraar(kwetteraar);
            return kdto;
        }
        return null;
    }

    @POST
    @Path("/post/logout")
    @Produces(APPLICATION_JSON)
    public void uitloggen(@FormParam("name") String name, @Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(name);
        if (kwetteraar != null && SessionLister.getInstance().getActiveUsers().contains(name)) {
            SessionLister.getInstance().getActiveUsers().remove(name);
        }
    }



    public List<KwetteraarDTO> kwetteraarListToDTO(List<Kwetteraar> kwetteraarList) {
        List<KwetteraarDTO> kwetteraarDTOList = new ArrayList<>();
        DTOConverter.toKwetteraarDTOList(kwetteraarList, kwetteraarDTOList);
        return kwetteraarDTOList;
    }
}
