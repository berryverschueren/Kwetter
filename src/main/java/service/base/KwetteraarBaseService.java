package service.base;

import dao.interfaces.KwetteraarDAO;
import model.Kweet;
import model.Kwetteraar;

import javax.ejb.Stateless;
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
        return kwetteraarDao.get(id);
    }

    public Kwetteraar getKwetteraarByProfielnaam(String profielnaam) {
        return kwetteraarDao.getByProfielnaam(profielnaam);
    }

    public boolean deleteKwetteraar(long id) {
        return kwetteraarDao.delete(id);
    }

    public List<Kweet> getKweets(long id) {
        return kwetteraarDao.get(id).getKweets();
    }

    public List<Kwetteraar> getVolgers(long id) {
        return kwetteraarDao.get(id).getVolgers();
    }

    public List<Kwetteraar> getLeiders(long id) {
        return kwetteraarDao.get(id).getLeiders();
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
        return kwetteraarDao.save(kwetteraar);
    }

    public void deleteKweet(long id, long kweetId) {
        getKweets(id).remove(getKweets(id).stream().filter(k->k.getId() == kweetId).findAny().orElse(null));
    }
}
