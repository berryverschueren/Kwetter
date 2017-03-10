package model;

import javax.persistence.*;

/**
 * Created by Berry-PC on 24/02/2017.
 */
@Entity
@Table(name="t_locatie")
public class Locatie {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "latitude", precision = 3)
    private double latitude;

    @Column(name = "longitude", precision = 3)
    private double longitude;

    @Column(name = "plaatsnaam", nullable = false, unique = true)
    private String plaatsNaam;

    public Locatie() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPlaatsNaam() {
        return plaatsNaam;
    }

    public void setPlaatsNaam(String plaatsNaam) {
        this.plaatsNaam = plaatsNaam;
    }
}
