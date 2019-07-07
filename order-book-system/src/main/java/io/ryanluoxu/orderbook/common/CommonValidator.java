package io.ryanluoxu.orderbook.common;

import org.springframework.beans.factory.annotation.Autowired;

import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrument;
import io.ryanluoxu.orderbook.bean.orderbook.OrderBook;
import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfo;
import io.ryanluoxu.orderbook.common.exception.CommonError;
import io.ryanluoxu.orderbook.common.exception.CommonException;
import io.ryanluoxu.orderbook.execution.ExecutionService;
import io.ryanluoxu.orderbook.financialinstrument.FinancialInstrumentService;
import io.ryanluoxu.orderbook.orderbook.OrderBookService;
import io.ryanluoxu.orderbook.orderinfo.OrderInfoService;

public class CommonValidator {
	
	@Autowired
	protected FinancialInstrumentService financialInstrumentService;
	@Autowired
	protected OrderInfoService orderInfoService;
	@Autowired
	protected OrderBookService orderBookService;
	@Autowired
	protected ExecutionService executionService;
	
	
	protected void validateFinancialInstrumentId(Long financialInstrumentId) throws CommonException {
		FinancialInstrument financialInstrument = financialInstrumentService.getById(financialInstrumentId);
		if (financialInstrument==null) {
			throw new CommonException(CommonError.VALIDATE_FINANCIAL_INSTRUMENT_ID_NOT_EXIST);
		}
	}
	
	protected void validateOrderBookId(Long orderBookId) throws CommonException {
		OrderBook orderBook = orderBookService.getById(orderBookId);
		if (orderBook==null) {
			throw new CommonException(CommonError.VALIDATE_ORDER_BOOK_ID);
		}
	}
	
	protected void validateOrderInfoId(Long orderInfoId) throws CommonException {
		OrderInfo orderInfo = orderInfoService.getById(orderInfoId);
		if (orderInfo==null) {
			throw new CommonException(CommonError.VALIDATE_ORDER_INFO_ID);
		}
	}
}
