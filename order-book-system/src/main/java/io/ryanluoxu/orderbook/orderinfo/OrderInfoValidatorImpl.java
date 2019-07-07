package io.ryanluoxu.orderbook.orderinfo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import io.ryanluoxu.orderbook.bean.orderbook.OrderBook;
import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfo;
import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfoInput;
import io.ryanluoxu.orderbook.common.CommonValidator;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.constant.StatusConstant;
import io.ryanluoxu.orderbook.common.constant.TypeConstant;
import io.ryanluoxu.orderbook.common.exception.CommonError;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@Service
public class OrderInfoValidatorImpl extends CommonValidator implements OrderInfoValidator {
	
	@Override
	public void validateMandatoryFields(OrderInfoInput orderInfoInput, String actionType) throws CommonException {
		if (ActionTypeConstant.ACTION_TYPE_ADD.equals(actionType)) {
			/*
			 * financialInstrumentId, quantity, orderType, price(option)
			 */
			checkMissingFinancialInstrumentId(orderInfoInput);
			checkMissingQuantity(orderInfoInput);
			checkMissingOrderType(orderInfoInput);
			if (TypeConstant.ORDER_INFO_ORDER_TYPE_LIMIT.equals(orderInfoInput.getOrderType())) {
				checkMissingPrice(orderInfoInput);
			}
			
		} else if (ActionTypeConstant.ACTION_TYPE_DELETE.equals(actionType)) {
			checkMissingOrderInfoId(orderInfoInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_UPDATE.equals(actionType)) {
			checkMissingOrderInfoId(orderInfoInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_FIND.equals(actionType)) {
			
		} else {
			throw new CommonException(CommonError.INVALID_ACTION_TYPE);
		}
	}

	@Override
	public void validateInputValue(OrderInfoInput orderInfoInput, String actionType) throws CommonException {
		if (ActionTypeConstant.ACTION_TYPE_ADD.equals(actionType)) {
			/*
			 * financialInstrumentId, orderType, quantity, price, orderBook
			 */
			validateFinancialInstrumentId(orderInfoInput.getFinancialInstrumentId());
			validateOrderType(orderInfoInput);
			validateQuantity(orderInfoInput);
			validatePrice(orderInfoInput);
			validateOrderBook(orderInfoInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_UPDATE.equals(actionType)) {
			validateOrderInfoId(orderInfoInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_FIND.equals(actionType)) {
			
		} else if (ActionTypeConstant.ACTION_TYPE_DELETE.equals(actionType)) {
			validateOrderInfoId(orderInfoInput);
			
		} else {
			throw new CommonException(CommonError.INVALID_ACTION_TYPE);
		}
	}


	/**
	 * check missing fields
	 */
	private void checkMissingOrderInfoId(OrderInfoInput orderInfoInput) throws CommonException {
		if (orderInfoInput.getOrderInfoId() == null) {
			throw new CommonException(CommonError.MISSING_ORDER_INFO_ID);
		}
	}
	private void checkMissingFinancialInstrumentId(OrderInfoInput orderInfoInput) throws CommonException {
		if (orderInfoInput.getFinancialInstrumentId() == null) {
			throw new CommonException(CommonError.MISSING_FINANCIAL_INSTRUMENT_ID);
		}
	}
	private void checkMissingQuantity(OrderInfoInput orderInfoInput) throws CommonException {
		if (orderInfoInput.getQuantity() == null) {
			throw new CommonException(CommonError.MISSING_ORDER_INFO_QUANTITY);
		}
	}
	private void checkMissingOrderType(OrderInfoInput orderInfoInput) throws CommonException {
		if (orderInfoInput.getOrderType() == null) {
			throw new CommonException(CommonError.MISSING_ORDER_INFO_ORDER_TYPE);
		}
	}
	private void checkMissingPrice(OrderInfoInput orderInfoInput) throws CommonException {
		if (orderInfoInput.getPrice() == null) {
			throw new CommonException(CommonError.MISSING_ORDER_INFO_PRICE);
		}
	}
	
	/**
	 * validate fields
	 */
	private void validateOrderInfoId(OrderInfoInput orderInfoInput) throws CommonException {
		OrderInfo orderInfo = orderInfoService.getById(orderInfoInput.getOrderBookId());
		if (orderInfo==null) {
			throw new CommonException(CommonError.VALIDATE_ORDER_INFO_ID);
		}
	}
	private void validateOrderType(OrderInfoInput orderInfoInput) throws CommonException {
		if (!TypeConstant.ORDER_INFO_ORDER_TYPE_LIMIT.equals(orderInfoInput.getOrderType())
				&& !TypeConstant.ORDER_INFO_ORDER_TYPE_MARKET.equals(orderInfoInput.getOrderType())) {
			throw new CommonException(CommonError.VALIDATE_ORDER_INFO_ORDER_TYPE, orderInfoInput.getOrderType());
		}
	}
	private void validateQuantity(OrderInfoInput orderInfoInput) throws CommonException {
		if (orderInfoInput.getQuantity().compareTo(BigDecimal.ZERO)<=0) {
			throw new CommonException(CommonError.VALIDATE_ORDER_INFO_QUANTITY, orderInfoInput.getQuantity());
		}
	}
	private void validatePrice(OrderInfoInput orderInfoInput) throws CommonException {
		if (orderInfoInput.getPrice().compareTo(BigDecimal.ZERO)<=0) {
			throw new CommonException(CommonError.VALIDATE_ORDER_INFO_PRICE, orderInfoInput.getPrice());
		}
	}
	private void validateOrderBook(OrderInfoInput orderInfoInput) throws CommonException {
		List<OrderBook> orderBooks = orderBookService.findByFinancialInstrumentId(orderInfoInput.getFinancialInstrumentId());
		if (CollectionUtils.isEmpty(orderBooks)) {
			throw new CommonException(CommonError.VALIDATE_ORDER_BOOK_NOT_FOUND);			
		}
		OrderBook orderBook = orderBookService.getByFinancialInstrumentId(orderInfoInput.getFinancialInstrumentId());
		if (!StatusConstant.ORDER_BOOK_OPEN.equals(orderBook.getStatus())) {
			throw new CommonException(CommonError.VALIDATE_ORDER_BOOK_STATUS_CLOSE);
		}
	}
}
