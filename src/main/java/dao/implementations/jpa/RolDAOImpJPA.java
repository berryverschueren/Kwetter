package dao.implementations.jpa;

import dao.interfaces.RolDAO;
import model.Rol;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@Stateless
@Alternative
public class RolDAOImpJPA implements RolDAO {

    @PersistenceContext
    private EntityManager em;

    public RolDAOImpJPA() {}

    @Override
    public Rol save(Rol rol) {
        if (rol == null || rol.getTitel() == null || rol.getTitel().isEmpty())
            return null;
        try {
            if (rol.getId() <= 0)
                em.persist(rol);

            else
                em.merge(rol);

            return rol;
        }
        catch (Exception x) {
            return null;
        }
    }

    @Override
    public boolean delete(long id) {
        if (id >= 0) {
            try {
                em.remove(get(id));
                return true;
            }
            catch (Exception x) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Rol get(long id) {
        if (id >= 0) {
            try {
                return em.find(Rol.class, id);
            }
            catch (Exception x) {
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Rol> getAll() {
        try {
            return (List<Rol>) em.createQuery("select r from Rol r").getResultList();
        }
        catch (Exception x) {
            return null;
        }
    }

    @Override
    public Rol getByTitel(String titel) {
        if (titel == null || titel.isEmpty())
             return null;

        try {
            return (Rol) em.createQuery("select r from Rol r where r.titel = '" + titel + "'").getSingleResult();
        }
        catch (Exception x) {
            return null;
        }
    }
}
