package io.ryanluoxu.orderbook.common;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
	/**
	 * Add
	 */
	T add(T t);
	
	/**
	 * Find
	 */
	List<T> findAll();
	T getById(ID id);
	
	/**
	 * Update
	 */
	T update(T t);
	
	/**
	 * Delete
	 */
	T deleteById(ID id);	
	
}
