package dao.implementations.jpa;

import dao.interfaces.KwetteraarDAO;
import model.Kwetteraar;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@RequestScoped
@Alternative
public class KwetteraarDAOImpJPA implements KwetteraarDAO {

    private static final String PERSISTENCE_UNIT_NAME = "kwetterDB";
    private static EntityManagerFactory factory  = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private EntityManager em = factory.createEntityManager();

    public KwetteraarDAOImpJPA() {}

    @Override
    public Kwetteraar save(Kwetteraar kwetteraar) {
        if(kwetteraar == null || kwetteraar.getProfielNaam() == null || kwetteraar.getProfielNaam().isEmpty())
            return null;

        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(kwetteraar);
            et.commit();
            return kwetteraar;
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
    public Kwetteraar get(long id) {
        if (id >= 0) {
            try {
                return em.find(Kwetteraar.class, id);
            }
            catch (Exception x) {
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Kwetteraar> getAll() {
        try {
            return (List<Kwetteraar>) em.createQuery("select k from t_kwetteraar k").getResultList();
        }
        catch (Exception x) {
            return null;
        }
    }

    @Override
    public Kwetteraar getByProfielnaam(String profielnaam) {
        if (profielnaam == null || profielnaam.isEmpty())
            return null;

        try {
            return (Kwetteraar) em.createQuery("select k from t_kwetteraar where profielnaam = " + profielnaam).getSingleResult();
        }
        catch (Exception x) {
            return null;
        }
    }

    @Override
    public void addVolger(long id, long idLeider) {
        if (id >= 0 && idLeider >= 0) {
            try {
                Kwetteraar volger = get(id);
                Kwetteraar leider = get(idLeider);
                leider.addVolger(volger);
                save(volger);
                save(leider);
            }
            catch (Exception x) {
                System.out.println(x);
            }
        }
    }

    @Override
    public void registreren(String profielnaam, String wachtwoord) {
        if (profielnaam != null && !profielnaam.isEmpty() && wachtwoord != null && !wachtwoord.isEmpty()) {
            Kwetteraar kwetteraar = new Kwetteraar();
            kwetteraar.setProfielNaam(profielnaam);
            kwetteraar.setWachtwoord(wachtwoord);
            save(kwetteraar);
        }
    }

    @Override
    public boolean inloggen(String profielnaam, String wachtwoord) {
        if(profielnaam == null || profielnaam.isEmpty() || wachtwoord == null || wachtwoord.isEmpty())
            return false;

        try {
            Kwetteraar kwetteraar = (Kwetteraar) em.createQuery("select k from t_kwetteraar k where profielnaam = " + profielnaam + " and wachtwoord = " + wachtwoord).getSingleResult();
            if (kwetteraar != null)
                return true;
            return false;
        }
        catch (Exception x) {
            return false;
        }
    }
}
