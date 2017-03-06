package dao;

import model.Rol;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public class RolDAOImp implements RolDAO {

    InMemoryCollectionObject im = InMemoryCollectionObject.getInstance();

    public RolDAOImp() {}

    @Override
    public Rol save(Rol rol) {
        //Invalid, return.
        if (rol == null)
            return null;

        //Non existing, add id.
        if (rol.getId() == 0L)
            rol.setId(im.useRollenId());

        //Existing, update.
        if (rol.getId() > 0L) {
            if (im.getRollen().stream().filter(k->k.getId() == rol.getId()).count() > 0) {
                im.getRollen().add(rol);
                return rol;
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
        im.getRollen().stream().filter(r->r.getId() == id).forEach(r -> im.getRollen().remove(r));
        return true;
    }

    @Override
    public Rol get(long id) {
        //Invalid id, return.
        if (id < 0L || id == 0L)
            return null;

        //Search and return, else return null.
        return im.getRollen().stream().filter(r->r.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Rol> getAll() {
        //Return all.
        return im.getRollen();
    }
}
