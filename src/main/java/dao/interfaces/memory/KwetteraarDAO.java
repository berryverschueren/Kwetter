package dao.interfaces.memory;

import model.Kwetteraar;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface KwetteraarDAO {
    Kwetteraar save(Kwetteraar kwetteraar);
    boolean delete(long id);
    Kwetteraar get(long id);
    List<Kwetteraar> getAll();
    Kwetteraar getByProfielnaam(String profielnaam);
    void addVolger(long id, long idLeider);
    void registreren(String profielnaam, String wachtwoord);
    boolean inloggen(String profielnaam, String wachtwoord);
}
