package org.azamov.learnjakarta.jakarta_bean_validation.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.constraints.NotNull;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.Base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDao<T extends Base, ID extends Serializable> {
    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_unit");
    protected static final EntityManager em = emf.createEntityManager();
    private final Class<T> persistanceClass;

    @SuppressWarnings("unchecked")
    protected BaseDao() {
        this.persistanceClass = (Class<T>) ((
                (ParameterizedType)
                        getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected void begin() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        em.getTransaction().begin();
    }

    protected void rollback() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

    protected void commit() {
        em.getTransaction().commit();
    }

    public T save(T entity) {
        try {
            begin();
            em.persist(entity);
            commit();
            return entity;
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public boolean update(T entity) {
        try {
            begin();
            em.merge(entity);
            commit();
            return true;
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public T findByID(@NotNull ID id) {
        try {
            begin();
            T entity = em.find(persistanceClass, id);
            commit();
            return entity;
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public List<T> findAll() {
        try {
            begin();
            List<T> entities = em.createQuery("from " + persistanceClass.getSimpleName(), persistanceClass).getResultList();
            commit();
            return entities;
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public List<T> findPage(int page, int size) {
        try {
            begin();
            List<T> result = em.createQuery("from " + persistanceClass.getSimpleName() + " t order by t.createdAt desc", persistanceClass)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .getResultList();
            commit();
            return result;
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public long count() {
        try {
            begin();
            long n = em.createQuery("select count(t) from " + persistanceClass.getSimpleName() + " t", Long.class)
                    .getSingleResult();
            commit();
            return n;
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public boolean deleteById(@NotNull ID id) {
        try {
            begin();
            em.createQuery("DELETE from " + persistanceClass.getSimpleName() + " t where t.id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            commit();
            return true;
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }
}
