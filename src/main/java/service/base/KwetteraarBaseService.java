package service.base;

import dao.implementations.KwetteraarDAOImp;
import dao.interfaces.KwetteraarDAO;
import model.Kweet;
import model.Kwetteraar;

import java.util.List;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class KwetteraarBaseService {
    private KwetteraarDAO kwetteraarDao = new KwetteraarDAOImp();

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
}
