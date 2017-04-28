package dto.integration;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

/**
 * Created by Berry-PC on 28/04/2017.
 */
public class BaseKwetteraarDto {

    @NotNull
    @FormParam("profielNaam")
    private String profielNaam;

    @NotNull
    @FormParam("profielFoto")
    private String profielFoto;

    @NotNull
    @FormParam("bio")
    private String bio;

    @NotNull
    @FormParam("website")
    private String website;

    @NotNull
    @FormParam("rol")
    private String rol;

    @NotNull
    @FormParam("plaatsNaam")
    private String plaatsNaam;

    public BaseKwetteraarDto() {
    }

    public BaseKwetteraarDto(String profielNaam, String profielFoto, String bio, String website, String rol, String plaatsNaam) {
        this.profielNaam = profielNaam;
        this.profielFoto = profielFoto;
        this.bio = bio;
        this.website = website;
        this.rol = rol;
        this.plaatsNaam = plaatsNaam;
    }

    public String getProfielNaam() {
        return profielNaam;
    }

    public void setProfielNaam(String profielNaam) {
        this.profielNaam = profielNaam;
    }

    public String getProfielFoto() {
        return profielFoto;
    }

    public void setProfielFoto(String profielFoto) {
        this.profielFoto = profielFoto;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPlaatsNaam() {
        return plaatsNaam;
    }

    public void setPlaatsNaam(String plaatsNaam) {
        this.plaatsNaam = plaatsNaam;
    }
}
