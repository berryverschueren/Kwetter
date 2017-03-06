package dao;

import java.util.List;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface KwetteraarDAO {
    KwetteraarDAOImp save(KwetteraarDAOImp kwetteraar);
    boolean delete(long id);
    KwetteraarDAOImp get(long id);
    List<KwetteraarDAOImp> getAll();
}
