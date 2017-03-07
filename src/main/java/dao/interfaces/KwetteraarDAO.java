package dao.interfaces;

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
}
