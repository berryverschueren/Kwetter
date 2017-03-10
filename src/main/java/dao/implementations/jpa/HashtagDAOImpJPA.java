package dao.implementations.jpa;

import dao.interfaces.HashtagDAO;
import model.memory.Hashtag;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import java.util.List;

/**
 * Created by Berry-PC on 09/03/2017.
 */
@RequestScoped
@Alternative
public class HashtagDAOImpJPA implements HashtagDAO {
    @Override
    public Hashtag save(Hashtag hashtag) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Hashtag get(long id) {
        return null;
    }

    @Override
    public List<Hashtag> getAll() {
        return null;
    }

    @Override
    public Hashtag getByInhoud(String inhoud) {
        return null;
    }

    @Override
    public List<Hashtag> getMatchesByInhoud(String inhoud) {
        return null;
    }
}
