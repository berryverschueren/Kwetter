package service.base;

import dao.interfaces.RolDAO;
import model.Rol;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class RolBaseService {

    @Inject
    private RolDAO rolDao; // = new RolDAOImp();

    public List<Rol> getRollen() {
        return rolDao.getAll();
    }

    public Rol getRol(long id) {
        return rolDao.get(id);
    }

    public Rol insertRol(String titel) {
        if (getExactlyMatchingRol(titel) != null)
            return getExactlyMatchingRol(titel);

        Rol rol = new Rol();
        rol.setTitel(titel);
        return rolDao.save(rol);
    }

    public Rol updateRol(long id, String titel) {
        Rol rol = getRol(id);
        rol.setTitel(titel);
        return rolDao.save(rol);
    }

    public boolean deleteRol(long id) {
        return rolDao.delete(id);
    }

    public Rol getExactlyMatchingRol(String titel) {
        return rolDao.getByTitel(titel);
    }
}
