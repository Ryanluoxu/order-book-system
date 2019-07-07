package io.ryanluoxu.orderbook.common;

import java.util.List;

import io.ryanluoxu.orderbook.common.exception.CommonException;

public interface GenericController<T, TVO, TInput> {

	void validate(TInput input, String actionType) throws CommonException;
	
	TVO add(TInput input);
	
	TVO update(TInput input);
	
	List<TVO> findAll();

	TVO delete(TInput input);

}
