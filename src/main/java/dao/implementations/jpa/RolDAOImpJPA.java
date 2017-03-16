package dao.implementations.jpa;

import dao.interfaces.jpa.RolDAO;
import model.Rol;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.*;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@Stateless
@Alternative
public class RolDAOImpJPA extends GenericDaoImpJPA<Rol> implements RolDAO {

    @PersistenceContext
    private EntityManager em;

    public RolDAOImpJPA() {}

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
