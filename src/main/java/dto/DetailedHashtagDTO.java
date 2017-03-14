package dto;

import model.Hashtag;
import model.Kweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 14/03/2017.
 */
public class DetailedHashtagDTO extends HashtagDTO {

    private List<KweetDTO> kweetDTOList;

    public DetailedHashtagDTO() {
        this.kweetDTOList = new ArrayList<>();
    }

    public void fromHashtag(Hashtag hashtag) {
        super.fromHashtag(hashtag);
        kwetteraarListToDTO(hashtag.getKweets(), kweetDTOList);
    }

    public void kwetteraarListToDTO(List<Kweet> kweetList, List<KweetDTO> kweetTargetList) {
        if (kweetList != null) {
            kweetList.forEach(k -> {
                KweetDTO kdto = new KweetDTO();
                kdto.fromKweet(k);
                kweetTargetList.add(kdto);
            });
        }
    }
}
