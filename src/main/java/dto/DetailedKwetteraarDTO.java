package dto;

import model.Kweet;
import model.Kwetteraar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
public class DetailedKwetteraarDTO extends KwetteraarDTO {
    private List<KwetteraarDTO> volgers;
    private List<KwetteraarDTO> leiders;
    private List<KweetDTO> kweets;
    private List<KweetDTO> hartjes;
    private List<KweetDTO> mentions;

    public DetailedKwetteraarDTO() {
        volgers = new ArrayList<>();
        leiders = new ArrayList<>();
        kweets = new ArrayList<>();
        hartjes = new ArrayList<>();
        mentions = new ArrayList<>();
    }

    public void fromKwetteraar(Kwetteraar kwetteraar) {
        super.fromKwetteraar(kwetteraar);
        kwetteraarListToDTO(kwetteraar.getVolgers(), volgers);
        kwetteraarListToDTO(kwetteraar.getLeiders(), leiders);
        kweetListToDTO(kwetteraar.getKweets(), kweets);
        kweetListToDTO(kwetteraar.getHartjes(), hartjes);
        kweetListToDTO(kwetteraar.getMentions(), mentions);
    }

    public void kweetListToDTO(List<Kweet> kweetList, List<KweetDTO> kweetTargetList) {
        if (kweetList != null) {
            kweetList.forEach(k -> {
                KweetDTO kdto = new KweetDTO();
                kdto.fromKweet(k);
                kweetTargetList.add(kdto);
            });
        }
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

    public List<KwetteraarDTO> getVolgers() {
        return volgers;
    }

    public void setVolgers(List<KwetteraarDTO> volgers) {
        this.volgers = volgers;
    }

    public List<KwetteraarDTO> getLeiders() {
        return leiders;
    }

    public void setLeiders(List<KwetteraarDTO> leiders) {
        this.leiders = leiders;
    }

    public List<KweetDTO> getKweets() {
        return kweets;
    }

    public void setKweets(List<KweetDTO> kweets) {
        this.kweets = kweets;
    }

    public List<KweetDTO> getHartjes() {
        return hartjes;
    }

    public void setHartjes(List<KweetDTO> hartjes) {
        this.hartjes = hartjes;
    }

    public List<KweetDTO> getMentions() {
        return mentions;
    }

    public void setMentions(List<KweetDTO> mentions) {
        this.mentions = mentions;
    }
}
