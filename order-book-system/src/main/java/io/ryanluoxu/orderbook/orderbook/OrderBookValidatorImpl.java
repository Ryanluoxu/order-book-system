package io.ryanluoxu.orderbook.orderbook;

import org.springframework.stereotype.Service;

import io.ryanluoxu.orderbook.bean.orderbook.OrderBook;
import io.ryanluoxu.orderbook.bean.orderbook.OrderBookInput;
import io.ryanluoxu.orderbook.common.CommonValidator;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.exception.CommonError;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@Service
public class OrderBookValidatorImpl extends CommonValidator implements OrderBookValidator {
	
	@Override
	public void validateMandatoryFields(OrderBookInput orderBookInput, String actionType) throws CommonException {
		if (ActionTypeConstant.ACTION_TYPE_ADD.equals(actionType)) {
			checkMissingFinancialInstrumentId(orderBookInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_DELETE.equals(actionType)) {
			checkMissingOrderBookId(orderBookInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_UPDATE.equals(actionType)) {
			checkMissingOrderBookId(orderBookInput);
			checkMissingOrderBookStatus(orderBookInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_FIND.equals(actionType)) {
			
		} else {
			throw new CommonException(CommonError.INVALID_ACTION_TYPE);
		}
	}


	@Override
	public void validateInputValue(OrderBookInput orderBookInput, String actionType) throws CommonException {
		if (ActionTypeConstant.ACTION_TYPE_ADD.equals(actionType)) {
			validateFinancialInstrumentIdForAddOrderBook(orderBookInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_UPDATE.equals(actionType)) {
			validateOrderBookId(orderBookInput.getOrderBookId());
			validateOrderBookStatus(orderBookInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_FIND.equals(actionType)) {
			
		} else if (ActionTypeConstant.ACTION_TYPE_DELETE.equals(actionType)) {
			validateOrderBookId(orderBookInput.getOrderBookId());
			
		} else {
			throw new CommonException(CommonError.INVALID_ACTION_TYPE);
		}
	}

	/**
	 * check missing fields
	 */
	private void checkMissingOrderBookId(OrderBookInput orderBookInput) throws CommonException {
		if (orderBookInput.getOrderBookId() == null) {
			throw new CommonException(CommonError.MISSING_ORDER_BOOK_ID);
		}
	}
	private void checkMissingFinancialInstrumentId(OrderBookInput orderBookInput) throws CommonException {
		if (orderBookInput.getFinancialInstrumentId() == null) {
			throw new CommonException(CommonError.MISSING_FINANCIAL_INSTRUMENT_ID);
		}
	}

	private void checkMissingOrderBookStatus(OrderBookInput orderBookInput) throws CommonException {
		if (orderBookInput.getStatus() == null) {
			throw new CommonException(CommonError.MISSING_ORDER_BOOK_STATUS);
		}
	}
	
	/**
	 * validate fields
	 */

	private void validateOrderBookStatus(OrderBookInput orderBookInput) throws CommonException {
		OrderBook orderBook = orderBookService.getById(orderBookInput.getOrderBookId());
		if (orderBook!=null) {
			if(orderBookInput.getStatus().equals(orderBook.getStatus())) {
				throw new CommonException(CommonError.VALIDATE_ORDER_BOOK_SAME_STATUS, orderBook.getStatus());
			}
		}
	}
	private void validateFinancialInstrumentIdForAddOrderBook(OrderBookInput orderBookInput) throws CommonException {
		// valid FinancialInstrumentId
		validateFinancialInstrumentId(orderBookInput.getFinancialInstrumentId());
		
		// no existing order book for FinancialInstrumentId
		OrderBook orderBook = orderBookService.getByFinancialInstrumentId(orderBookInput.getFinancialInstrumentId());
		if (orderBook!=null) {
			throw new CommonException(CommonError.VALIDATE_ORDER_BOOK_EXIST);
		}
	}


}
