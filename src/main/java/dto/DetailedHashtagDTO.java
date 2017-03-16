package dto;

import model.Hashtag;

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
        DTOConverter.toKweetDTOList(hashtag.getKweets(), kweetDTOList);
    }
}
