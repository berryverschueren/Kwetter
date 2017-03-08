package controller;

import dto.KwetteraarDTO;
import model.Kwetteraar;
import service.KwetterService;

import javax.ws.rs.*;

import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Berry-PC on 07/03/2017.
 */

@Path("/kwetteraar")
public class KwetteraarAPI {
    KwetterService kwetterService = new KwetterService();

    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<KwetteraarDTO> getAllKwetteraars() {
        List<Kwetteraar> kwetteraarList = kwetterService.getKwetteraarBaseService().getKwetteraars();
        List<KwetteraarDTO> kwetteraarDTOList = new ArrayList<>();
        kwetteraarList.forEach(k->{
            KwetteraarDTO kdto = new KwetteraarDTO();
            kdto.fromKwetteraar(k);
            kwetteraarDTOList.add(kdto);
        });
        return kwetteraarDTOList;
    }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public KwetteraarDTO getKwetteraarById(@PathParam("id") long id) {
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraar(id);
        KwetteraarDTO kdto = new KwetteraarDTO();
        kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }

    @GET
    @Path("/get/one/name/{name}")
    @Produces(APPLICATION_JSON)
    public KwetteraarDTO getKwetteraarByName(@PathParam("name") String name) {
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(name);
        KwetteraarDTO kdto = new KwetteraarDTO();
        kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }

    @GET
    @Path("/get/more/kwetteraarid/leiders/{id}")
    @Produces(APPLICATION_JSON)
    public List<KwetteraarDTO> getKwetteraarLeidersById(@PathParam("id") long id) {
        List<Kwetteraar> kwetteraarList = kwetterService.getKwetteraarBaseService().getLeiders(id);
        List<KwetteraarDTO> kwetteraarDTOList = new ArrayList<>();
        kwetteraarList.forEach(k->{
            KwetteraarDTO kdto = new KwetteraarDTO();
            kdto.fromKwetteraar(k);
            kwetteraarDTOList.add(kdto);
        });
        return kwetteraarDTOList;
    }

    @GET
    @Path("/get/more/kwetteraarid/volgers/{id}")
    @Produces(APPLICATION_JSON)
    public List<KwetteraarDTO> getKwetteraarVolgersById(@PathParam("id") long id) {
        List<Kwetteraar> kwetteraarList = kwetterService.getKwetteraarBaseService().getVolgers(id);
        List<KwetteraarDTO> kwetteraarDTOList = new ArrayList<>();
        kwetteraarList.forEach(k->{
            KwetteraarDTO kdto = new KwetteraarDTO();
            kdto.fromKwetteraar(k);
            kwetteraarDTOList.add(kdto);
        });
        return kwetteraarDTOList;
    }

    @POST
    @Path("/post/insert")
    @Produces(APPLICATION_JSON)
    public KwetteraarDTO insertKwetteraar(@FormParam("name") String name) {
        Kwetteraar kwetteraar = new Kwetteraar();
        kwetteraar.setProfielNaam(name);
        kwetteraar = kwetterService.getKwetteraarBaseService().saveKwetteraar(kwetteraar);
        KwetteraarDTO kdto = new KwetteraarDTO();
        kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }

    @POST
    @Path("/post/volg")
    @Produces(APPLICATION_JSON)
    public KwetteraarDTO addVolger(@FormParam("idVolger") long idVolger, @FormParam("idLeider") long idLeider) {
        kwetterService.getKwetteraarBaseService().addVolger(idVolger, idLeider);
        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraar(idVolger);
        KwetteraarDTO kdto = new KwetteraarDTO();
        kdto.fromKwetteraar(kwetteraar);
        return kdto;
    }
}
