package dao.interfaces.jpa;

import model.Rol;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface RolDAO extends GenericDao<Rol> {
    Rol getByTitel(String titel);
}
