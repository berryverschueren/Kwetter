package dto;

import model.memory.Kweet;
import model.memory.Kwetteraar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
public class DetailedKweetDTO extends KweetDTO {
    private List<KwetteraarDTO> mentions;
    private List<KwetteraarDTO> hartjes;

    public DetailedKweetDTO() {
        mentions = new ArrayList<>();
        hartjes = new ArrayList<>();
    }

    public void fromKweet(Kweet kweet) {
        super.fromKweet(kweet);
        kwetteraarListToDTO(kweet.getMentions(), mentions);
        kwetteraarListToDTO(kweet.getHartjes(), hartjes);
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
