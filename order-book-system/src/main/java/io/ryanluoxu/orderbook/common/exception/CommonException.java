package io.ryanluoxu.orderbook.common.exception;

public class CommonException extends Exception {
	private static final long serialVersionUID = 7996938116570928536L;
	private String errorMsg;

	public CommonException(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public CommonException(String errorMsg, Object... args) {
		errorMsg = String.format(errorMsg, args);
		this.errorMsg = errorMsg;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
