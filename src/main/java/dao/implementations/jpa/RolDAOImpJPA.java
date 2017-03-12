package dao.implementations.jpa;

import dao.interfaces.RolDAO;
import model.Rol;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@RequestScoped
@Alternative
public class RolDAOImpJPA implements RolDAO {

    private static final String PERSISTENCE_UNIT_NAME = "kwetterDB";
    private static EntityManagerFactory factory  = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private EntityManager em = factory.createEntityManager();

    public RolDAOImpJPA() {}

    @Override
    public Rol save(Rol rol) {
        if (rol == null || rol.getTitel() == null || rol.getTitel().isEmpty())
            return null;


        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(rol);
            et.commit();
            return rol;
        }
        catch (Exception x) {
            if (et.isActive())
                et.rollback();
            return null;
        }
    }

    @Override
    public boolean delete(long id) {
        if (id >= 0) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                em.remove(get(id));
                et.commit();
                return true;
            }
            catch (Exception x) {
                if (et.isActive())
                    et.rollback();
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
            return (List<Rol>) em.createQuery("select r from t_rol r").getResultList();
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
            return (Rol) em.createQuery("select r from t_rol r where titel = " + titel).getSingleResult();
        }
        catch (Exception x) {
            return null;
        }
    }
}
