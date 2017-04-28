package dto.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.Locatie;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 28/04/2017.
 */
public class HateoasLocatieDto extends BaseLocatieDto {

    private long id;
    private List<Link> links;

    public HateoasLocatieDto() {
        this.links = new ArrayList<>();
    }

    public HateoasLocatieDto(Locatie locatie) {
        super(locatie.getPlaatsNaam(), locatie.getLatitude(), locatie.getLongitude());
        this.id = locatie.getId();
        links = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("_links")
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
