package dao.implementations.jpa;

import dao.interfaces.RolDAO;
import model.memory.Rol;

import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
public class RolDAOImpJPA implements RolDAO {
    @Override
    public Rol save(Rol rol) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Rol get(long id) {
        return null;
    }

    @Override
    public List<Rol> getAll() {
        return null;
    }

    @Override
    public Rol getByTitel(String titel) {
        return null;
    }
}
