package dao;

import model.Locatie;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface LocatieDAO {
    Locatie save(Locatie locatie);
    boolean delete(long id);
    Locatie get(long id);
    List<Locatie> getAll();
}
