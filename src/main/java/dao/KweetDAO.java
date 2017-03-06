package dao;

import model.Kweet;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface KweetDAO {
    Kweet save(Kweet kweet);
    boolean delete(long id);
    Kweet get(long id);
    List<Kweet> getAll();
}
