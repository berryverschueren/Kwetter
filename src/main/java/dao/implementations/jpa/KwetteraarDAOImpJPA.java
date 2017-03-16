package dao.implementations.jpa;

import dao.interfaces.KwetteraarDAO;
import model.Kwetteraar;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@Stateless
@Alternative
public class KwetteraarDAOImpJPA implements KwetteraarDAO {

    @PersistenceContext
    private EntityManager em;

    public KwetteraarDAOImpJPA() {}

        @Override
    public Kwetteraar save(Kwetteraar kwetteraar) {
        if(kwetteraar == null || kwetteraar.getProfielNaam() == null || kwetteraar.getProfielNaam().isEmpty())
            return null;
        try {
            if (kwetteraar.getId() <= 0)
                em.persist(kwetteraar);

            else
                em.merge(kwetteraar);

            return kwetteraar;
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
            return (List<Kwetteraar>) em.createQuery("select k from Kwetteraar k").getResultList();
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
            return (Kwetteraar) em.createQuery("select k from Kwetteraar k where k.profielNaam = '" + profielnaam + "'").getSingleResult();
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
            Kwetteraar kwetteraar = (Kwetteraar) em.createQuery("select k from Kwetteraar k where k.profielnNaam = '" + profielnaam + "' and k.wachtwoord = '" + wachtwoord + "'").getSingleResult();
            if (kwetteraar != null)
                return true;
            return false;
        }
        catch (Exception x) {
            return false;
        }
    }
}
