import static org.junit.Assert.assertEquals;

import dao.*;
import model.*;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class DataAccessLayerTests {

    RolDAO rolDao = new RolDAOImp();
    HashtagDAO hashtagDao = new HashtagDAOImp();
    KwetteraarDAO kwetteraarDao = new KwetteraarDAOImp();
    KweetDAO kweetDao = new KweetDAOImp();
    LocatieDAO locatieDao = new LocatieDAOImp();

    @Test
    public void testDaoImplementaties() {
        Locatie locatie1 = new Locatie();
        locatie1.setPlaatsNaam("Eindhoven");

        Locatie locatie2 = new Locatie();
        locatie2.setPlaatsNaam("Roosendaal");

        Rol rol1 = new Rol();
        rol1.setTitel("Regulier");

        Rol rol2 = new Rol();
        rol2.setTitel("Administrator");

        Kwetteraar kwetteraar1 = new Kwetteraar();
        kwetteraar1.setProfielNaam("Berry");

        Kwetteraar kwetteraar2 = new Kwetteraar();
        kwetteraar2.setProfielNaam("Yva");

        Hashtag hashtag1 = new Hashtag();
        hashtag1.setInhoud("#Testing");

        Hashtag hashtag2 = new Hashtag();
        hashtag2.setInhoud("#AgainAndAgain");

        Kweet kweet1 = new Kweet();
        kweet1.setInhoud("Test Kweet 1.");
        kweet1.setDatum(LocalDateTime.now());

        Kweet kweet2 = new Kweet();
        kweet2.setInhoud("Test Kweet 2.");
        kweet2.setDatum(LocalDateTime.now());

        locatieDao.save(locatie1);
        locatieDao.save(locatie2);
        assertEquals(1, locatie1.getId());
        assertEquals(2, locatie2.getId());

        rolDao.save(rol1);
        rolDao.save(rol2);
        assertEquals(1, rol1.getId());
        assertEquals(2, rol2.getId());

        kwetteraarDao.save(kwetteraar1);
        kwetteraarDao.save(kwetteraar2);
        assertEquals(1, kwetteraar1.getId());
        assertEquals(2, kwetteraar2.getId());

        hashtagDao.save(hashtag1);
        hashtagDao.save(hashtag2);
        assertEquals(1, hashtag1.getId());
        assertEquals(2, hashtag2.getId());

        kweetDao.save(kweet1);
        kweetDao.save(kweet2);
        assertEquals(1, kweet1.getId());
        assertEquals(2, kweet2.getId());

        locatie1.setPlaatsNaam("Amsterdam");
        locatieDao.save(locatie1);
        assertEquals(1, locatie1.getId());
        assertEquals("Amsterdam", locatie1.getPlaatsNaam());

        rol1.setTitel("Gebruiker");
        rolDao.save(rol1);
        assertEquals(1, rol1.getId());
        assertEquals("Gebruiker", rol1.getTitel());

        kwetteraar1.setProfielNaam("Willem");
        kwetteraarDao.save(kwetteraar1);
        assertEquals(1, kwetteraar1.getId());
        assertEquals("Willem", kwetteraar1.getProfielNaam());

        hashtag1.setInhoud("#TestingRefactored");
        hashtagDao.save(hashtag1);
        assertEquals(1, hashtag1.getId());
        assertEquals("#TestingRefactored", hashtag1.getInhoud());

        kweet1.setInhoud("Test Kweet 1 Refactored.");
        kweetDao.save(kweet1);
        assertEquals(1, kweet1.getId());
        assertEquals("Test Kweet 1 Refactored.", kweet1.getInhoud());

        assertEquals(2, locatieDao.getAll().size());
        assertEquals(2, rolDao.getAll().size());
        assertEquals(2, kwetteraarDao.getAll().size());
        assertEquals(2, hashtagDao.getAll().size());
        assertEquals(2, kweetDao.getAll().size());

        assertEquals(locatie2, locatieDao.get(2));
        assertEquals(rol2, rolDao.get(2));
        assertEquals(kwetteraar2, kwetteraarDao.get(2));
        assertEquals(hashtag2, hashtagDao.get(2));
        assertEquals(kweet2, kweetDao.get(2));

        locatieDao.delete(1);
        rolDao.delete(1);
        kwetteraarDao.delete(1);
        hashtagDao.delete(1);
        kweetDao.delete(1);

        assertEquals(1, locatieDao.getAll().size());
        assertEquals(1, rolDao.getAll().size());
        assertEquals(1, kwetteraarDao.getAll().size());
        assertEquals(1, hashtagDao.getAll().size());
        assertEquals(1, kweetDao.getAll().size());

        assertEquals(null, locatieDao.get(0));
        assertEquals(null, rolDao.get(0));
        assertEquals(null, kwetteraarDao.get(0));
        assertEquals(null, hashtagDao.get(0));
        assertEquals(null, kweetDao.get(0));

        assertEquals(false, locatieDao.delete(0));
        assertEquals(false, rolDao.delete(0));
        assertEquals(false, kwetteraarDao.delete(0));
        assertEquals(false, hashtagDao.delete(0));
        assertEquals(false, kweetDao.delete(0));

        assertEquals(null, locatieDao.save(null));
        assertEquals(null, rolDao.save(null));
        assertEquals(null, kwetteraarDao.save(null));
        assertEquals(null, hashtagDao.save(null));
        assertEquals(null, kweetDao.save(null));
    }
}
