package dto.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import model.Kwetteraar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 28/04/2017.
 */
@JsonIgnoreProperties({"plaatsNaam"})
@JsonPropertyOrder({"id", "profielNaam", "profielFoto", "bio", "website", "rol", "_locatie", "_volgers", "_leiders", "_links"})
public class HateoasDetailedKwetteraarDto extends HateoasKwetteraarDto {

    private HateoasLocatieDto locatie;
    private List<HateoasKwetteraarDto> volgers;
    private List<HateoasKwetteraarDto> leiders;
//    private List<HateoasKweetDto> kweets;
//    private List<HateoasKweetDto> hartjes;
//    private List<HateoasKweetDto> mentions;

    public HateoasDetailedKwetteraarDto() {
        volgers = new ArrayList<>();
        leiders = new ArrayList<>();
//        kweets = new ArrayList<>();
//        hartjes = new ArrayList<>();
//        mentions = new ArrayList<>();
    }

    public HateoasDetailedKwetteraarDto(Kwetteraar kwetteraar, List<HateoasKwetteraarDto> volgers, List<HateoasKwetteraarDto> leiders, HateoasLocatieDto locatie) {
        super(kwetteraar);
        this.volgers = volgers;
        this.leiders = leiders;
        this.locatie = locatie;
    }

    @JsonProperty("_locatie")
    public HateoasLocatieDto getLocatie() {
        return locatie;
    }

    public void setLocatie(HateoasLocatieDto locatie) {
        this.locatie = locatie;
    }

    @JsonProperty("_volgers")
    public List<HateoasKwetteraarDto> getVolgers() {
        return volgers;
    }

    public void setVolgers(List<HateoasKwetteraarDto> volgers) {
        this.volgers = volgers;
    }

    @JsonProperty("_leiders")
    public List<HateoasKwetteraarDto> getLeiders() {
        return leiders;
    }

    public void setLeiders(List<HateoasKwetteraarDto> leiders) {
        this.leiders = leiders;
    }

//    public List<HateoasKweetDto> getKweets() {
//        return kweets;
//    }
//
//    public void setKweets(List<HateoasKweetDto> kweets) {
//        this.kweets = kweets;
//    }
//
//    public List<HateoasKweetDto> getHartjes() {
//        return hartjes;
//    }
//
//    public void setHartjes(List<HateoasKweetDto> hartjes) {
//        this.hartjes = hartjes;
//    }
//
//    public List<HateoasKweetDto> getMentions() {
//        return mentions;
//    }
//
//    public void setMentions(List<HateoasKweetDto> mentions) {
//        this.mentions = mentions;
//    }
}
