package io.ryanluoxu.orderbook.common;

import io.ryanluoxu.orderbook.common.exception.CommonException;

public interface GenericValidator<TInput> {

	void validateMandatoryFields(TInput input, String actionType) throws CommonException;
	void validateInputValue(TInput input, String actionType) throws CommonException;

}
