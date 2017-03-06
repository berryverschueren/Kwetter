package dao;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface KweetDAO {
    KweetDAOImp save(KweetDAOImp kweet);
    boolean delete(long id);
    KweetDAOImp get(long id);
    List<KweetDAOImp> getAll();
}
