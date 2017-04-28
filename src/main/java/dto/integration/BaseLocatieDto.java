package dto.integration;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

/**
 * Created by Berry-PC on 28/04/2017.
 */
public class BaseLocatieDto {

    @NotNull
    @FormParam("profielNaam")
    private String plaatsNaam;

    @NotNull
    @FormParam("latitude")
    private double latitude;

    @NotNull
    @FormParam("longitude")
    private double longitude;

    public BaseLocatieDto() {
    }

    public BaseLocatieDto(String plaatsNaam, double latitude, double longitude) {
        this.plaatsNaam = plaatsNaam;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPlaatsNaam() {
        return plaatsNaam;
    }

    public void setPlaatsNaam(String plaatsNaam) {
        this.plaatsNaam = plaatsNaam;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
