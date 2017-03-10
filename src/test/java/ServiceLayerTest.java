import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import model.Kweet;
import model.Kwetteraar;
import model.Rol;
import org.junit.Test;
import service.KwetterService;
import service.base.*;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class ServiceLayerTest {

    //private KwetterService ks = new KwetterService();
    private KwetterService ks = mock(KwetterService.class);

    @Test
    public void testServiceLayerImplementatie() {
        ks.clearMemory();

        when(ks.getKwetteraarBaseService()).thenReturn(new KwetteraarBaseService());
        when(ks.getKweetBaseService()).thenReturn(new KweetBaseService());
        when(ks.getHashtagBaseService()).thenReturn(new HashtagBaseService());
        when(ks.getLocatieBaseService()).thenReturn(new LocatieBaseService());
        when(ks.getRolBaseService()).thenReturn(new RolBaseService());

        String gebruikersnaam = "Berry";
        String wachtwoord = "Test";

        ks.getKwetteraarBaseService().registreren(gebruikersnaam, wachtwoord);

        Kwetteraar kwetteraar = ks.getKwetteraarBaseService().getKwetteraars().stream().filter(k->k.getProfielNaam().equals(gebruikersnaam)).findAny().orElse(null);

        assertEquals(gebruikersnaam, kwetteraar.getProfielNaam());

        boolean result = ks.getKwetteraarBaseService().inloggen(gebruikersnaam, wachtwoord);

        assertEquals(true, result);

        Rol rol = ks.getRolBaseService().insertRol("Regulier");
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

        assertEquals(1, ks.getKweetBaseService().getRecenteEigenKweetsByKwetteraarId(kwetteraar.getId()).size());

        Kweet k1 = ks.getKweetBaseService().getMatchesByInhoud("met een mention").get(0);
        assertEquals(kweetInhoud, k1.getInhoud());

        ks.getKwetteraarBaseService().registreren(gebruikersnaam, wachtwoord);
        Kwetteraar kwetteraar1 = ks.getKwetteraarBaseService().getKwetteraars().stream().filter(k->k.getProfielNaam().equals(gebruikersnaam)).findAny().orElse(null);

        ks.getKwetteraarBaseService().addVolger(kwetteraar.getId(), kwetteraar1.getId());

        assertEquals(1, ks.getKwetteraarBaseService().getVolgers(kwetteraar1.getId()).size());
        assertEquals(0, ks.getKwetteraarBaseService().getVolgers(kwetteraar.getId()).size());
        assertEquals(1, ks.getKwetteraarBaseService().getLeiders(kwetteraar.getId()).size());
        assertEquals(0, ks.getKwetteraarBaseService().getLeiders(kwetteraar1.getId()).size());

        ks.geefHartje(kwetteraar1.getId(), k1.getId());

        assertEquals(1, k1.getHartjes().size());

        ks.stuurKweet(kwetteraar.getId(), "Dit is de tweede kweet van kwetteraar. #COOL");
        ks.stuurKweet(kwetteraar1.getId(), "Dit is mijn eerste kweet, leuk he. #Leuk #COOL @Yva");

        assertEquals(3, ks.getEigenEnLeiderKweets(kwetteraar.getId()).size());

        assertEquals(2, ks.getKweetBaseService().getKweetsByMentionId(kwetteraar.getId()).size());

        assertEquals(2, ks.getTrends("#COOL").size());

        ks.verwijderKweet(k1.getId());
        ks.uitloggen();

        assertEquals(1, kwetteraar.getKweets().size());

        assertEquals(false, ks.getKwetteraarBaseService().inloggen("test", "test"));

        for (int i = 0; i < 60; i++) {
            ks.stuurKweet(kwetteraar1.getId(), "Test " + i);
        }

        assertEquals(10, ks.getKweetBaseService().getRecenteEigenKweetsByKwetteraarId(kwetteraar1.getId()).size());
        assertEquals(50, ks.getEigenEnLeiderKweets(kwetteraar1.getId()).size());
    }
}
