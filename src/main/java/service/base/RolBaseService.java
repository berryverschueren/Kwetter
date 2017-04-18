package service.base;

import dao.interfaces.jpa.RolDAO;
import model.Rol;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class RolBaseService {

    private RolDAO rolDao;

    @Inject
    public RolBaseService (RolDAO rd) {
        rolDao = rd;
    }

    public List<Rol> getRollen() {
        return rolDao.getAll();
    }

    public Rol getRol(long id) {
        return rolDao.find(id);
    }

    public Rol insertRol(String titel) {
        if (getExactlyMatchingRol(titel) != null)
            return getExactlyMatchingRol(titel);

        Rol rol = new Rol();
        rol.setTitel(titel);
        return rolDao.create(rol);
    }

    public Rol updateRol(Rol rol) {
        return rolDao.update(rol);
    }

    public void deleteRol(long id) {
        rolDao.delete(id);
    }

    public Rol getExactlyMatchingRol(String titel) {
        return rolDao.getByTitel(titel);
    }
}
