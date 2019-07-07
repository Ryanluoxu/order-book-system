package io.ryanluoxu.orderbook.execution;

import java.math.BigDecimal;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import io.ryanluoxu.orderbook.bean.execution.Execution;
import io.ryanluoxu.orderbook.bean.execution.ExecutionInput;
import io.ryanluoxu.orderbook.bean.orderbook.OrderBook;
import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfo;
import io.ryanluoxu.orderbook.common.CommonValidator;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.constant.StatusConstant;
import io.ryanluoxu.orderbook.common.exception.CommonError;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@Service
public class ExecutionValidatorImpl extends CommonValidator implements ExecutionValidator {
	
	@Override
	public void validateMandatoryFields(ExecutionInput executionInput, String actionType) throws CommonException {
		if (ActionTypeConstant.ACTION_TYPE_ADD.equals(actionType)) {
			/*
			 * quantity, price, orderBookId, orderInfoId
			 */
			checkMissingOrderBookId(executionInput);
			checkMissingPrice(executionInput);
			checkMissingQuantity(executionInput);
			checkMissingOrderInfoId(executionInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_DELETE.equals(actionType)) {
			checkMissingExecutionId(executionInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_UPDATE.equals(actionType)) {
			checkMissingExecutionId(executionInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_FIND.equals(actionType)) {
			
		} else {
			throw new CommonException(CommonError.INVALID_ACTION_TYPE);
		}
	}

	@Override
	public void validateInputValue(ExecutionInput executionInput, String actionType) throws CommonException {
		if (ActionTypeConstant.ACTION_TYPE_ADD.equals(actionType)) {
			/*
			 * quantity, price, orderBookId, orderInfoId
			 */
			validateOrderBookId(executionInput.getOrderBookId());
			validateOrderBookStatusForExecution(executionInput.getOrderBookId());
			validatePrice(executionInput);
			for (Entry<Long, BigDecimal> entry : executionInput.getOrderInfoIdToQuantityMap().entrySet()) {
				validateOrderInfoId(entry.getKey());
				validateOrderInfoWithOrderBook(executionInput.getOrderBookId(), entry.getKey());
				validateQuantity(entry.getValue());
			}
			
		} else if (ActionTypeConstant.ACTION_TYPE_UPDATE.equals(actionType)) {
			validateExecutionId(executionInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_FIND.equals(actionType)) {
			
		} else if (ActionTypeConstant.ACTION_TYPE_DELETE.equals(actionType)) {
			validateExecutionId(executionInput);
			
		} else {
			throw new CommonException(CommonError.INVALID_ACTION_TYPE);
		}
	}




	/**
	 * check missing fields
	 */
	private void checkMissingExecutionId(ExecutionInput executionInput) throws CommonException {
		if (executionInput.getExecutionId() == null) {
			throw new CommonException(CommonError.MISSING_EXECUTION_ID);
		}
	}
	private void checkMissingOrderBookId(ExecutionInput executionInput) throws CommonException {
		if (executionInput.getOrderBookId() == null) {
			throw new CommonException(CommonError.MISSING_EXECUTION_ORDER_BOOK_ID);
		}
	}
	private void checkMissingPrice(ExecutionInput executionInput) throws CommonException {
		if (executionInput.getPrice() == null) {
			throw new CommonException(CommonError.MISSING_EXECUTION_PRICE);
		}
	}
	private void checkMissingQuantity(ExecutionInput executionInput) throws CommonException {
		if (executionInput.getOrderInfoIdToQuantityMap() == null) {
			throw new CommonException(CommonError.MISSING_EXECUTION_ORDER_INFO_ID);
		}
	}
	private void checkMissingOrderInfoId(ExecutionInput executionInput) throws CommonException {
		if (executionInput.getOrderInfoIdToQuantityMap() != null) {
			for (Entry<Long, BigDecimal> entry : executionInput.getOrderInfoIdToQuantityMap().entrySet()) {
				if (entry.getValue()==null) {
					throw new CommonException(CommonError.MISSING_EXECUTION_QUANTITY);					
				}
			}
		}
	}
	
	
	/**
	 * validate fields
	 */
	private void validateExecutionId(ExecutionInput executionInput) throws CommonException {
		Execution execution = executionService.getById(executionInput.getOrderBookId());
		if (execution==null) {
			throw new CommonException(CommonError.VALIDATE_ORDER_INFO_ID);
		}
	}
	private void validateQuantity(BigDecimal quantity) throws CommonException {
		if (quantity.compareTo(BigDecimal.ZERO)<=0) {
			throw new CommonException(CommonError.VALIDATE_EXECUTION_QUANTITY);
		}
	}
	private void validatePrice(ExecutionInput executionInput) throws CommonException {
		if (executionInput.getPrice().compareTo(BigDecimal.ZERO)<=0) {
			throw new CommonException(CommonError.VALIDATE_EXECUTION_PRICE);
		}
	}
	private void validateOrderInfoWithOrderBook(Long orderBookId, Long orderInfoId) throws CommonException {
		OrderInfo orderInfo = orderInfoService.getById(orderInfoId);
		if (!orderInfo.getOrderBookId().equals(orderBookId)) {
			throw new CommonException(CommonError.VALIDATE_EXECUTION_ORDER_INFO_WITH_ORDER_BOOK, orderInfoId, orderBookId);
		}
	}
	private void validateOrderBookStatusForExecution(Long orderBookId) throws CommonException {
		OrderBook orderBook = orderBookService.getById(orderBookId);
		if (!StatusConstant.ORDER_BOOK_CLOSE.equals(orderBook.getStatus())) {
			throw new CommonException(CommonError.VALIDATE_EXECUTION_ORDER_BOOK_NOT_CLOSE);
		}
	}
}
