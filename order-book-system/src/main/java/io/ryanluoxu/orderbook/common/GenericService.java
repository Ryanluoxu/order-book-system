package io.ryanluoxu.orderbook.common;

import java.util.List;

public interface GenericService<T, ID> {
	T add(T t);
	T update(T t);
	T deleteById(ID id);

	List<T> findAll();
	T getById(ID id);
}
