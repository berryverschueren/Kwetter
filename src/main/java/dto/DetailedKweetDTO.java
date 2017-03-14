package dto;

import model.Hashtag;
import model.Kweet;
import model.Kwetteraar;

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
        kwetteraarListToDTO(kweet.getMentions(), mentions);
        kwetteraarListToDTO(kweet.getHartjes(), hartjes);
        hashtagListToDTO(kweet.getHashtags(), hashtags);
    }

    public void kwetteraarListToDTO(List<Kwetteraar> kwetteraarList, List<KwetteraarDTO> kwetteraarTargetList) {
        if (kwetteraarList != null) {
            kwetteraarList.forEach(k -> {
                KwetteraarDTO kdto = new KwetteraarDTO();
                kdto.fromKwetteraar(k);
                kwetteraarTargetList.add(kdto);
            });
        }
    }

    public void hashtagListToDTO(List<Hashtag> hashtagList, List<HashtagDTO> hashtagTargetList) {
        if (hashtagList != null) {
            hashtagList.forEach(h -> {
                HashtagDTO hdto = new HashtagDTO();
                hdto.fromHashtag(h);
                hashtagTargetList.add(hdto);
            });
        }
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
