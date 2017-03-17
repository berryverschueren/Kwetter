package dto;

import model.Rol;

/**
 * Created by Berry-PC on 13/03/2017.
 */
public class RolDTO {
    private long id;
    private String titel;

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
