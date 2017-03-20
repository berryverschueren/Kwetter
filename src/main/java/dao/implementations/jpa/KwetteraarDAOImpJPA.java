package dao.implementations.jpa;

import dao.interfaces.jpa.KwetteraarDAO;
import model.Kwetteraar;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import logger.Logger;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@Stateless
@Alternative
public class KwetteraarDAOImpJPA extends GenericDaoImpJPA<Kwetteraar> implements KwetteraarDAO {

    public KwetteraarDAOImpJPA() {
        // Empty constructor for dependency injection purposes.
    }

    @Override
    public Kwetteraar getByProfielnaam(String profielnaam) {
        if (profielnaam == null || profielnaam.isEmpty())
            return null;

        try {
            return (Kwetteraar) em.createQuery("select k from Kwetteraar k where k.profielNaam = '" + profielnaam + "'").getSingleResult();
        }
        catch (Exception x) {
            Logger.log(x);
            return null;
        }
    }

    @Override
    public void addVolger(long id, long idLeider) {
        if (id >= 0 && idLeider >= 0) {
            try {
                Kwetteraar volger = find(id);
                Kwetteraar leider = find(idLeider);
                leider.addVolger(volger);
                update(volger);
                update(leider);
            }
            catch (Exception x) {
                Logger.log(x);
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
            create(kwetteraar);
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
            Logger.log(x);
            return false;
        }
    }
}
