package dao.implementations;

import dao.interfaces.LocatieDAO;
import model.Locatie;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public class LocatieDAOImp implements LocatieDAO {

    InMemoryCollectionObject im = InMemoryCollectionObject.getInstance();

    public LocatieDAOImp() {}

    @Override
    public Locatie save(Locatie locatie) {
        //Invalid, return.
        if (locatie == null || locatie.getPlaatsNaam() == null || locatie.getPlaatsNaam().isEmpty())
            return null;

        //Non existing, add id.
        if (locatie.getId() == 0L)
            locatie.setId(im.useLocatieId());

        //Existing, update.
        if (locatie.getId() > 0L) {
            if (im.getLocaties().stream().filter(l->l.getId() == locatie.getId()).count() == 0) {
                im.getLocaties().add(locatie);
                return locatie;
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

        //Search and delete.
        Locatie locatie = im.getLocaties().stream().filter(l->l.getId() == id).findAny().orElse(null);
        if (locatie != null)
            im.getLocaties().remove(locatie);

        return true;
    }

    @Override
    public Locatie get(long id) {
        //Invalid id, return.
        if (id < 0L || id == 0L)
            return null;

        //Search and return, else return null.
        return im.getLocaties().stream().filter(l->l.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Locatie> getAll() {
        //Return all.
        return im.getLocaties();
    }

    @Override
    public Locatie getByPlaatsnaam(String plaatsnaam) {
        return im.getLocaties().stream().filter(l->l.getPlaatsNaam().equals(plaatsnaam)).findAny().orElse(null);
    }
}
