package controller;

import dto.DetailedKwetteraarDTO;
import dto.KwetteraarDTO;
import model.Kwetteraar;
import model.Locatie;
import model.Rol;
import service.KwetterService;

import javax.inject.Inject;
import javax.ws.rs.*;

import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Berry-PC on 07/03/2017.
 */

@Path("/kwetteraar")
public class KwetteraarAPI {
    @Inject
    KwetterService kwetterService; // = new KwetterService();

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<KwetteraarDTO> getAllKwetteraars() {
        return kwetteraarListToDTO(kwetterService.getKwetteraarBaseService().getKwetteraars());
    }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO getKwetteraarById(@PathParam("id") long id) {
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraar(id);
        DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
        if (kwetteraar != null)
            kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }

    @GET
    @Path("/get/one/name/{name}")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO getKwetteraarByName(@PathParam("name") String name) {
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(name);
        DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
        if (kwetteraar != null)
            kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }

    @GET
    @Path("/get/more/kwetteraarid/leiders/{id}")
    @Produces(APPLICATION_JSON)
    public List<KwetteraarDTO> getKwetteraarLeidersById(@PathParam("id") long id) {
        return kwetteraarListToDTO(kwetterService.getKwetteraarBaseService().getLeiders(id));
    }

    @GET
    @Path("/get/more/kwetteraarid/volgers/{id}")
    @Produces(APPLICATION_JSON)
    public List<KwetteraarDTO> getKwetteraarVolgersById(@PathParam("id") long id) {
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
            , @FormParam("wachtwoord") String wachtwoord) {
        Kwetteraar kwetteraar = new Kwetteraar();
        kwetteraar.setProfielNaam(name);
        kwetteraar.setProfielFoto(foto);
        kwetteraar.setBio(bio);
        kwetteraar.setWebsite(website);
        kwetteraar.setWachtwoord(wachtwoord);
        Rol rol = kwetterService.getRolBaseService().insertRol(rolTitel);
        kwetteraar.addRol(rol);
        Locatie locatie = kwetterService.getLocatieBaseService().insertLocatie(locatieNaam);
        kwetteraar.setLocatie(locatie);
        kwetteraar = kwetterService.getKwetteraarBaseService().saveKwetteraar(kwetteraar);
        DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
        if (kwetteraar != null)
            kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }

    @POST
    @Path("/post/volg")
    @Produces(APPLICATION_JSON)
    public DetailedKwetteraarDTO addVolger(@FormParam("idVolger") long idVolger, @FormParam("idLeider") long idLeider) {
        kwetterService.getKwetteraarBaseService().addVolger(idVolger, idLeider);
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraar(idVolger);
        DetailedKwetteraarDTO kdto = new DetailedKwetteraarDTO();
        if (kwetteraar != null)
            kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }

    public List<KwetteraarDTO> kwetteraarListToDTO(List<Kwetteraar> kwetteraarList) {
        List<KwetteraarDTO> kwetteraarDTOList = new ArrayList<>();
        DetailedKwetteraarDTO dto = new DetailedKwetteraarDTO();
        dto.kwetteraarListToDTO(kwetteraarList, kwetteraarDTOList);
        return kwetteraarDTOList;
    }
}
