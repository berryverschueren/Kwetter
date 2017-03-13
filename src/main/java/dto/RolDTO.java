package dto;

import model.Kwetteraar;
import model.Rol;

import java.util.List;

/**
 * Created by Berry-PC on 13/03/2017.
 */
public class RolDTO {
    private long id;
    private String titel;

    public RolDTO() {}

    public void fromRol(Rol rol) {
        id = rol.getId();
        titel = rol.getTitel();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
}
