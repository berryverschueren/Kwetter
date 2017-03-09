package dao.implementations.jpa;

import dao.interfaces.KwetteraarDAO;
import model.memory.Kwetteraar;

import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
public class KwetteraarDAOImpJPA implements KwetteraarDAO {
    @Override
    public Kwetteraar save(Kwetteraar kwetteraar) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Kwetteraar get(long id) {
        return null;
    }

    @Override
    public List<Kwetteraar> getAll() {
        return null;
    }

    @Override
    public Kwetteraar getByProfielnaam(String profielnaam) {
        return null;
    }

    @Override
    public void addVolger(long id, long idLeider) {

    }

    @Override
    public void registreren(String profielnaam, String wachtwoord) {

    }

    @Override
    public boolean inloggen(String profielnaam, String wachtwoord) {
        return false;
    }
}
