import static org.junit.Assert.assertEquals;

import model.*;
import org.junit.Test;
import service.KwetterService;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class ServiceLayerTests {

    private KwetterService ks = new KwetterService();

    @Test
    public void testServiceLayerImplementatie() {
        String gebruikersnaam = "Berry";
        String wachtwoord = "Test";

        ks.registreren(gebruikersnaam, wachtwoord);

        Kwetteraar kwetteraar = ks.getKwetteraars().stream().filter(k->k.getProfielNaam().equals(gebruikersnaam)).findAny().orElse(null);

        assertEquals(gebruikersnaam, kwetteraar.getProfielNaam());

        boolean result = ks.inloggen(gebruikersnaam, wachtwoord);

        assertEquals(true, result);

        Rol rol = ks.createRol("Regulier");
        ks.wijzigRol(kwetteraar.getId(), rol.getId());

        assertEquals("Regulier", kwetteraar.getRol().getTitel());

        ks.wijzigProfielnaam(kwetteraar.getId(), "Yva");
        ks.wijzigProfielfoto(kwetteraar.getId(), "Foto");
        ks.wijzigDetailgegevens(kwetteraar.getId(), "Details");

        assertEquals("Yva", kwetteraar.getProfielNaam());
        assertEquals("Foto", kwetteraar.getProfielFoto());
        assertEquals("Details", kwetteraar.getBio());

        String kweetInhoud = "Dit is mijn nieuwe kweet, met een mention naar mijzelf @Yva en een hashtag trend #Trending #YouKnowIt#";
        ks.stuurKweet(kwetteraar.getId(), kweetInhoud);

        assertEquals(1, ks.getRecenteEigenTweets(kwetteraar.getId()).size());

        Kweet k1 = ks.zoekKweet("met een mention");
        assertEquals(kweetInhoud, k1.getInhoud());

        ks.registreren(gebruikersnaam, wachtwoord);
        Kwetteraar kwetteraar1 = ks.getKwetteraars().stream().filter(k->k.getProfielNaam().equals(gebruikersnaam)).findAny().orElse(null);

        ks.volgKwetteraar(kwetteraar.getId(), kwetteraar1.getId());

        assertEquals(1, ks.getVolgers(kwetteraar1.getId()).size());
        assertEquals(0, ks.getVolgers(kwetteraar.getId()).size());
        assertEquals(1, ks.getLeiders(kwetteraar.getId()).size());
        assertEquals(0, ks.getLeiders(kwetteraar1.getId()).size());

        ks.geefHartje(kwetteraar1.getId(), k1.getId());

        assertEquals(1, k1.getHartjes().size());

        ks.stuurKweet(kwetteraar.getId(), "Dit is de tweede kweet van kwetteraar. #COOL");
        ks.stuurKweet(kwetteraar1.getId(), "Dit is mijn eerste kweet, leuk he. #Leuk #COOL @Yva");

        assertEquals(3, ks.getEigenEnLeiderKweets(kwetteraar.getId()).size());

        assertEquals(2, ks.getMentionedKweets(kwetteraar.getId()));

        assertEquals(2, ks.getTrends("#COOL").size());
    }
}
