package dto.integration;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 28/04/2017.
 */
public class HateoasKwetteraarListDto {

    private List<HateoasKwetteraarDto> kwetteraars;
    private List<Link> links;

    public HateoasKwetteraarListDto() {
        kwetteraars = new ArrayList<>();
        links = new ArrayList<>();
    }

    public HateoasKwetteraarListDto(List<HateoasKwetteraarDto> kwetteraars) {
        this.kwetteraars = kwetteraars;
        links = new ArrayList<>();
    }

    @JsonProperty("_kwetteraars")
    public List<HateoasKwetteraarDto> getKwetteraars() {
        return kwetteraars;
    }

    public void setKwetteraars(List<HateoasKwetteraarDto> kwetteraars) {
        this.kwetteraars = kwetteraars;
    }

    @JsonProperty("_links")
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
