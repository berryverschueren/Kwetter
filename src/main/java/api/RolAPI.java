package api;

import dto.DTOConverter;
import dto.DetailedRolDTO;
import dto.RolDTO;
import model.Rol;
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

@Path("/rol")
public class RolAPI {
    KwetterService kwetterService;

    @Inject
    public RolAPI (KwetterService ks) {
        kwetterService = ks;
    }
    @GET
    @Path("/get/more")
    @Produces(APPLICATION_JSON)
    public List<RolDTO> getAllRollen(@Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        return rolListToDTO(kwetterService.getRolBaseService().getRollen());
    }

    @GET
    @Path("/get/one/id/{id}")
    @Produces(APPLICATION_JSON)
    public DetailedRolDTO getRolById(@PathParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Rol rol = kwetterService.getRolBaseService().getRol(id);
        DetailedRolDTO rdto = new DetailedRolDTO();
        if (rol != null)
            rdto.fromRol(rol);
        return rdto;
    }

    @GET
    @Path("/get/one/name/{name}")
    @Produces(APPLICATION_JSON)
    public DetailedRolDTO getRolByName(@PathParam("name") String name, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Rol rol = kwetterService.getRolBaseService().getExactlyMatchingRol(name);
        DetailedRolDTO rdto = new DetailedRolDTO();
        if (rol != null)
            rdto.fromRol(rol);
        return rdto;
    }

    @POST
    @Path("/post/insert")
    @Produces(APPLICATION_JSON)
    public DetailedRolDTO createRol(@FormParam("name") String name, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Rol rol =  kwetterService.getRolBaseService().insertRol(name);
        DetailedRolDTO rdto = new DetailedRolDTO();
        if (rol != null)
            rdto.fromRol(rol);
        return rdto;
    }

    @POST
    @Path("/post/update")
    @Produces(APPLICATION_JSON)
    public DetailedRolDTO updateRol(@FormParam("id") long id, @FormParam("name") String name, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        Rol rol =  kwetterService.getRolBaseService().updateRol(id, name);
        DetailedRolDTO rdto = new DetailedRolDTO();
        if (rol != null)
            rdto.fromRol(rol);
        return rdto;
    }

    @POST
    @Path("/post/delete")
    @Produces(APPLICATION_JSON)
    public List<RolDTO> deleteRol(@FormParam("id") long id, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin" , "*");
        kwetterService.getRolBaseService().deleteRol(id);
        return rolListToDTO(kwetterService.getRolBaseService().getRollen());
    }

    public List<RolDTO> rolListToDTO(List<Rol> kweetList) {
        List<RolDTO> kweetDTOList = new ArrayList<>();
        DTOConverter.toRolDTOList(kweetList, kweetDTOList);
        return kweetDTOList;
    }
}
