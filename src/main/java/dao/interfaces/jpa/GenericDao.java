package dao.interfaces.jpa;

import java.util.List;

/**
 * Created by Berry-PC on 16/03/2017.
 */
public interface GenericDao<T> {
    List<T> getAll();
    T create (T t);
    void delete (Object id);
    T find (Object id);
    T update(T t);
}
