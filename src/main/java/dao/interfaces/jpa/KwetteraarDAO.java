package dao.interfaces.jpa;

import model.Kwetteraar;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface KwetteraarDAO extends GenericDao<Kwetteraar> {
    Kwetteraar getByProfielnaam(String profielnaam);
    void addVolger(long id, long idLeider);
    void registreren(String profielnaam, String wachtwoord);
    boolean inloggen(String profielnaam, String wachtwoord);
}
