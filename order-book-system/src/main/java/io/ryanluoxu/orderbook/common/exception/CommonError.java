package io.ryanluoxu.orderbook.common.exception;

public class CommonError {

	public final static String INVALID_ACTION_TYPE = "Error : invalid action type: out of CURD.";
	
	// FINANCIAL_INSTRUMENT
	public final static String MISSING_FINANCIAL_INSTRUMENT_ID = "Error : missing financial instrument id.";
	public final static String MISSING_FINANCIAL_INSTRUMENT_TYPE = "Error : missing financial instrument type.";
	public final static String MISSING_FINANCIAL_INSTRUMENT_NAME = "Error : missing financial instrument name.";
	public final static String MISSING_FINANCIAL_INSTRUMENT_CODE = "Error : missing financial instrument code.";
	public final static String VALIDATE_FINANCIAL_INSTRUMENT_CODE = "Error : already exists: financial instrument code.";
	public final static String VALIDATE_FINANCIAL_INSTRUMENT_NAME = "Error : already exists: financial instrument name.";
	public final static String VALIDATE_FINANCIAL_INSTRUMENT_ID_NOT_EXIST = "Error : invalid : financial instrument id.";
	
	// ORDER_BOOK
	public final static String MISSING_ORDER_BOOK_STATUS = "Error : missing order book status.";
	public final static String MISSING_ORDER_BOOK_ID = "Error : missing order book id.";
	public final static String VALIDATE_ORDER_BOOK_EXIST = "Error : already exists for this financial instrument id.";
	public final static String VALIDATE_ORDER_BOOK_ID = "Error : invalid : order book id.";
	public final static String VALIDATE_ORDER_BOOK_SAME_STATUS = "Error : order book status is already %s";
	public final static String VALIDATE_ORDER_BOOK_STATUS_CLOSE = "Error : invalid: order book is close";
	public final static String VALIDATE_ORDER_BOOK_NOT_FOUND = "Error : invalid: order book is not found";
	
	
	// ORDER_INFO
	public final static String MISSING_ORDER_INFO_ID = "Error : missing order info id.";
	public final static String MISSING_ORDER_INFO_PRICE = "Error : missing order price.";
	public final static String MISSING_ORDER_INFO_ORDER_TYPE = "Error : missing order type.";
	public final static String MISSING_ORDER_INFO_QUANTITY = "Error : missing order quantity.";
	public final static String VALIDATE_ORDER_INFO_ID = "Error : invalid order info id.";
	public final static String VALIDATE_ORDER_INFO_PRICE = "Error : invalid order price : %s";
	public final static String VALIDATE_ORDER_INFO_ORDER_TYPE = "Error : invalid order type : %s";
	public final static String VALIDATE_ORDER_INFO_QUANTITY = "Error : invalid order quantity : %s";

	// EXECUTION
	public final static String MISSING_EXECUTION_ID = "Error : missing execution id.";
	public final static String MISSING_EXECUTION_PRICE = "Error : missing execution price.";
	public final static String MISSING_EXECUTION_QUANTITY = "Error : missing execution quantity.";
	public final static String MISSING_EXECUTION_ORDER_BOOK_ID = "Error : missing execution order book id.";
	public final static String MISSING_EXECUTION_ORDER_INFO_ID = "Error : missing execution order info id.";
	public final static String VALIDATE_EXECUTION_ID = "Error : invalid execution id.";
	public final static String VALIDATE_EXECUTION_PRICE = "Error : invalid execution price : %s";
	public final static String VALIDATE_EXECUTION_QUANTITY = "Error : invalid execution quantity : %s";
	public final static String VALIDATE_EXECUTION_ORDER_BOOK_ID = "Error : invalid execution order book id.";
	public final static String VALIDATE_EXECUTION_ORDER_INFO_ID = "Error : invalid execution order info id.";
	public final static String VALIDATE_EXECUTION_ORDER_INFO_WITH_ORDER_BOOK = "Error : order [%s] is not in the order book [%s].";
	public final static String VALIDATE_EXECUTION_ORDER_BOOK_NOT_CLOSE = "Error : order book is not close";
}
