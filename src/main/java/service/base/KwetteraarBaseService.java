package service.base;

import dao.interfaces.jpa.KwetteraarDAO;
import model.Kweet;
import model.Kwetteraar;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class KwetteraarBaseService {
    @Inject
    private KwetteraarDAO kwetteraarDao; // = new KwetteraarDAOImp();

    public List<Kwetteraar> getKwetteraars() {
        return kwetteraarDao.getAll();
    }

    public Kwetteraar getKwetteraar(long id) {
        return kwetteraarDao.find(id);
    }

    public Kwetteraar getKwetteraarByProfielnaam(String profielnaam) {
        return kwetteraarDao.getByProfielnaam(profielnaam);
    }

    public void deleteKwetteraar(long id) {
        kwetteraarDao.delete(id);
    }

    public List<Kweet> getKweets(long id) {
        return kwetteraarDao.find(id).getKweets();
    }

    public List<Kwetteraar> getVolgers(long id) {
        return kwetteraarDao.find(id).getVolgers();
    }

    public List<Kwetteraar> getLeiders(long id) {
        return kwetteraarDao.find(id).getLeiders();
    }

    public void addVolger(long id, long idLeider) {
        kwetteraarDao.addVolger(id, idLeider);
    }

    public void registreren(String profielnaam, String wachtwoord) {
        kwetteraarDao.registreren(profielnaam, wachtwoord);
    }

    public boolean inloggen(String profielnaam, String wachtwoord) {
        return kwetteraarDao.inloggen(profielnaam, wachtwoord);
    }

    public Kwetteraar saveKwetteraar(Kwetteraar kwetteraar) {
        return kwetteraarDao.create(kwetteraar);
    }

    public Kwetteraar updateKwetteraar(Kwetteraar kwetteraar) {
        return kwetteraarDao.update(kwetteraar);
    }

    public void deleteKweet(long id, long kweetId) {
        getKweets(id).remove(getKweets(id).stream().filter(k->k.getId() == kweetId).findAny().orElse(null));
    }
}
