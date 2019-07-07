package io.ryanluoxu.orderbook.common;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.ryanluoxu.orderbook.common.util.ClassUtil;
import io.ryanluoxu.orderbook.common.util.JpaUtil;

public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

	@SuppressWarnings("rawtypes")
	protected Class targetClass;
	
	protected String STATUS = "status";

	@Autowired
	private SessionFactory sessionFactory;

	public GenericDaoImpl() {
		this.targetClass = ClassUtil.getTypeArguments(GenericDaoImpl.class, this.getClass()).get(0);
	}

	protected final Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public T add(T t) {
		EntityManager em = JpaUtil.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(t);
		tx.commit();
		em.close();
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T deleteById(ID id) {
		EntityManager em = JpaUtil.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		T t = (T) em.find(targetClass, id);
		em.remove(t);
		tx.commit();
		em.close();
		return t;
	}

	@Override
	public T update(T t) {
		EntityManager em = JpaUtil.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(t);
		tx.commit();
		em.close();
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		EntityManager em = JpaUtil.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query query  = em.createQuery("select t from " + targetClass.getName() + " t");
		List<T> list = query.getResultList();
		tx.commit();
		em.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(ID id) {
		EntityManager em = JpaUtil.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		T t = (T) em.find(targetClass, id);
		tx.commit();
		em.close();
		return t;
	}

}
