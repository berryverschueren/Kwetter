package dto;

import model.Rol;

import java.util.List;

/**
 * Created by Berry-PC on 13/03/2017.
 */
public class DetailedRolDTO extends RolDTO {
    private List<KwetteraarDTO> kwetteraars;

    public DetailedRolDTO() {}

    @Override
    public void fromRol(Rol rol) {
        super.fromRol(rol);
        DTOConverter.toKwetteraarDTOList(rol.getKwetteraars(), kwetteraars);
    }

    public List<KwetteraarDTO> getKwetteraars() {
        return kwetteraars;
    }

    public void setKwetteraars(List<KwetteraarDTO> kwetteraars) {
        this.kwetteraars = kwetteraars;
    }
}
