package dao.implementations.memory;

import dao.interfaces.memory.KwetteraarDAO;
import model.Kwetteraar;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
@RequestScoped
@Default
public class KwetteraarDAOImp implements KwetteraarDAO {

    InMemoryCollectionObject im = InMemoryCollectionObject.getInstance();

    public KwetteraarDAOImp() {}

    @Override
    public Kwetteraar save(Kwetteraar kwetteraar) {
        //Invalid, return.
        if (kwetteraar == null || kwetteraar.getProfielNaam() == null || kwetteraar.getProfielNaam().isEmpty())
            return null;

        //Non existing, add id.
        if (kwetteraar.getId() == 0L)
            kwetteraar.setId(im.useKwetteraarId());

        //Existing, update.
        if (kwetteraar.getId() > 0L) {
            if (im.getKwetteraars().stream().filter(k->k.getId() == kwetteraar.getId()).count() == 0) {
                im.getKwetteraars().add(kwetteraar);
                return kwetteraar;
            }
        }

        //Failed.
        return null;
    }

    @Override
    public boolean delete(long id) {
        //Invalid id, return.
        if (id < 0L || id == 0L)
            return false;

        Kwetteraar kwetteraar = im.getKwetteraars().stream().filter(k->k.getId() == id).findAny().orElse(null);
        if (kwetteraar != null)
            im.getKwetteraars().remove(kwetteraar);

        return true;
    }

    @Override
    public Kwetteraar get(long id) {
        //Invalid id, return.
        if (id < 0L || id == 0L)
            return null;

        //Search and return, else return null.
        return im.getKwetteraars().stream().filter(k->k.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Kwetteraar> getAll() {
        //Return all.
        return im.getKwetteraars();
    }

    @Override
    public Kwetteraar getByProfielnaam(String profielnaam) {
        return im.getKwetteraars().stream().filter(k->k.getProfielNaam().equals(profielnaam)).findAny().orElse(null);
    }

    @Override
    public void addVolger(long id, long idLeider) {
        get(idLeider).addVolger(get(id));
    }

    @Override
    public void registreren(String profielnaam, String wachtwoord) {
        Kwetteraar kwetteraar = new Kwetteraar();
        kwetteraar.setProfielNaam(profielnaam);
        kwetteraar.setWachtwoord(wachtwoord);
        save(kwetteraar);
    }

    @Override
    public boolean inloggen(String profielnaam, String wachtwoord) {
        if(getByProfielnaam(profielnaam) != null)
            return getByProfielnaam(profielnaam).getWachtwoord().equals(wachtwoord);
        return false;
    }
}
