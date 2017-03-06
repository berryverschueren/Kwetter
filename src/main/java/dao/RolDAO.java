package dao;

import model.Rol;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface RolDAO {
    Rol save(Rol rol);
    boolean delete(long id);
    Rol get(long id);
    List<Rol> getAll();
}
