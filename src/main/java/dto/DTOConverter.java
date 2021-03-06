package dto;

import model.Hashtag;
import model.Kweet;
import model.Kwetteraar;

import java.util.List;

/**
 * Created by Berry-PC on 16/03/2017.
 */
public class DTOConverter {

    private DTOConverter() {
        // Empty private constructor to prevent java from auto generating a public one.
        // Utility classes should not be instantiated, hence this prevention method.
    }

    public static void toKwetteraarDTOList(List<Kwetteraar> kwetteraarList, List<KwetteraarDTO> kwetteraarTargetList) {
        if (kwetteraarList != null) {
            kwetteraarList.forEach(k -> {
                KwetteraarDTO kdto = new KwetteraarDTO();
                kdto.fromKwetteraar(k);
                kwetteraarTargetList.add(kdto);
            });
        }
    }

    public static void toKweetDTOList(List<Kweet> kweetList, List<KweetDTO> kweetTargetList) {
        if (kweetList != null) {
            kweetList.forEach(k -> {
                KweetDTO kdto = new KweetDTO();
                kdto.fromKweet(k);
                kweetTargetList.add(kdto);
            });
        }
    }

    public static void toHashtagDTOList(List<Hashtag> hashtagList, List<HashtagDTO> hashtagTargetList) {
        if (hashtagList != null) {
            hashtagList.forEach(h -> {
                HashtagDTO hdto = new HashtagDTO();
                hdto.fromHashtag(h);
                hashtagTargetList.add(hdto);
            });
        }
    }
}
