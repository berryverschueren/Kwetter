package dao;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface LocatieDAO {
    LocatieDAOImp save(LocatieDAOImp locatie);
    boolean delete(long id);
    LocatieDAOImp get(long id);
    List<LocatieDAOImp> getAll();
}
