package dto;

import model.Kweet;
import model.Kwetteraar;
import java.util.Date;

/**
 * Created by Berry-PC on 08/03/2017.
 */
public class KweetDTO {

    private long id;
    private String inhoud;
    private Date datum;
    private KwetteraarDTO eigenaar;

    public KweetDTO() {}

    public void fromKweet(Kweet kweet) {
        id = kweet.getId();
        inhoud = kweet.getInhoud();
        datum = kweet.getDatum();
        Kwetteraar kwetteraar = kweet.getEigenaar();
        KwetteraarDTO kdto = new KwetteraarDTO();
        kdto.fromKwetteraar(kwetteraar);
        eigenaar = kdto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public KwetteraarDTO getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(KwetteraarDTO eigenaar) {
        this.eigenaar = eigenaar;
    }
}
