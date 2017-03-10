package dao.implementations.jpa;

import dao.interfaces.LocatieDAO;
import model.memory.Locatie;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@RequestScoped
@Alternative
public class LocatieDAOImpJPA implements LocatieDAO {
    @Override
    public Locatie save(Locatie locatie) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Locatie get(long id) {
        return null;
    }

    @Override
    public List<Locatie> getAll() {
        return null;
    }

    @Override
    public Locatie getByPlaatsnaam(String plaatsnaam) {
        return null;
    }
}
