package com.productheaven.generics;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractHibernateDao<T, PK extends Serializable> {
    private final Class<T> persistentClass;

    @Autowired
    private SessionFactory sessionFactory;

    public AbstractHibernateDao(){
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T findOne(long id){
        return (T) getSession().get(persistentClass, id);
    }

    public List<T> findAll() {
        List<T> result = getSession().createQuery("from " + persistentClass.getName()).list();
        return result;
    }

    public T create(T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity) {
        return (T) getSession().merge(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
