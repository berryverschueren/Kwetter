package dao.implementations.jpa;

import dao.interfaces.KweetDAO;
import model.Kweet;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@RequestScoped
@Alternative
public class KweetDAOImpJPA implements KweetDAO {
    @Override
    public Kweet save(Kweet kweet) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Kweet get(long id) {
        return null;
    }

    @Override
    public List<Kweet> getAll() {
        return null;
    }

    @Override
    public List<Kweet> getMatchesByInhoud(String inhoud) {
        return null;
    }

    @Override
    public List<Kweet> getKweetByHashtagId(long id) {
        return null;
    }

    @Override
    public List<Kweet> getKweetsByMentionId(long id) {
        return null;
    }

    @Override
    public List<Kweet> getKweetsByKwetteraarId(long id) {
        return null;
    }

    @Override
    public List<Kweet> getRecenteEigenKweetsByKwetteraarId(long id) {
        return null;
    }
}
