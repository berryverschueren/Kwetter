package dto;

import model.Kweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
public class DetailedKweetDTO extends KweetDTO {
    private List<KwetteraarDTO> mentions;
    private List<KwetteraarDTO> hartjes;
    private List<HashtagDTO> hashtags;

    public DetailedKweetDTO() {
        mentions = new ArrayList<>();
        hartjes = new ArrayList<>();
        hashtags = new ArrayList<>();
    }

    public void fromKweet(Kweet kweet) {
        super.fromKweet(kweet);
        DTOConverter.toKwetteraarDTOList(kweet.getMentions(), mentions);
        DTOConverter.toKwetteraarDTOList(kweet.getHartjes(), hartjes);
        DTOConverter.toHashtagDTOList(kweet.getHashtags(), hashtags);
    }

    public List<KwetteraarDTO> getMentions() {
        return mentions;
    }

    public void setMentions(List<KwetteraarDTO> mentions) {
        this.mentions = mentions;
    }

    public List<KwetteraarDTO> getHartjes() {
        return hartjes;
    }

    public void setHartjes(List<KwetteraarDTO> hartjes) {
        this.hartjes = hartjes;
    }
}
