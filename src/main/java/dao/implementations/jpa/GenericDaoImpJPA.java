package dao.implementations.jpa;

import dao.interfaces.jpa.GenericDao;
import logger.Logger;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Berry-PC on 16/03/2017.
 */
public abstract class GenericDaoImpJPA <T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> type;

    public GenericDaoImpJPA() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public List<T> getAll() {
        final StringBuilder queryString = new StringBuilder(
                "SELECT x from ");

        queryString.append(type.getSimpleName()).append(" x ");

        final Query query = em.createQuery(queryString.toString());

        return (List<T>) query.getResultList();
    }

    @Override
    public T create(final T t) {
        this.em.persist(t);
        return t;
    }

    @Override
    public void delete(final Object id) {
        this.em.remove(this.em.getReference(type, id));
    }

    @Override
    public T find(final Object id) {
        return this.em.find(type, id);
    }

    @Override
    public T update(final T t) {
        try {
            return this.em.merge(t);
        } catch (Exception x){
            Logger.log(x);
            return t;
        }
    }
}
