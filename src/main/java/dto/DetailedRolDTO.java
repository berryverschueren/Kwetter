package dto;

import model.Kwetteraar;
import model.Rol;

import java.util.List;

/**
 * Created by Berry-PC on 13/03/2017.
 */
public class DetailedRolDTO extends RolDTO {
    private List<KwetteraarDTO> kwetteraars;

    public DetailedRolDTO() {}

    public void fromRol(Rol rol) {
        super.fromRol(rol);
        kwetteraarListToDTO(rol.getKwetteraars(), kwetteraars);
    }

    public void kwetteraarListToDTO(List<Kwetteraar> kwetteraarList, List<KwetteraarDTO> kwetteraarTargetList) {
        if (kwetteraarList != null) {
            kwetteraarList.forEach(k -> {
                KwetteraarDTO kdto = new KwetteraarDTO();
                kdto.fromKwetteraar(k);
                kwetteraarTargetList.add(kdto);
            });
        }
    }

    public List<KwetteraarDTO> getKwetteraars() {
        return kwetteraars;
    }

    public void setKwetteraars(List<KwetteraarDTO> kwetteraars) {
        this.kwetteraars = kwetteraars;
    }
}
