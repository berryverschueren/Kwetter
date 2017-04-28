package dto.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import model.Kwetteraar;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 28/04/2017.
 */
@JsonIgnoreProperties({"profielFoto", "bio", "website", "plaatsNaam"})
@JsonPropertyOrder({"id", "profielNaam", "rol", "_links"})
public class HateoasKwetteraarDto extends BaseKwetteraarDto {
    private long id;
    private List<Link> links;

    public HateoasKwetteraarDto() {
        links = new ArrayList<>();
    }

    public HateoasKwetteraarDto(Kwetteraar kwetteraar) {
        super(kwetteraar.getProfielNaam(), kwetteraar.getProfielFoto(), kwetteraar.getBio(), kwetteraar.getWebsite(), kwetteraar.getRol(), kwetteraar.getLocatie().getPlaatsNaam());
        this.id = kwetteraar.getId();
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
