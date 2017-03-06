package dao;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface RolDAO {
    RolDAOImp save(RolDAOImp rol);
    boolean delete(long id);
    RolDAOImp get(long id);
    List<RolDAOImp> getAll();
}
