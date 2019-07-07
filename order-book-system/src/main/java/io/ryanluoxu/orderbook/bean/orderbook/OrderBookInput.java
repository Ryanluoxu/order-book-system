package io.ryanluoxu.orderbook.bean.orderbook;

public class OrderBookInput{

	private Long orderBookId;
	private Long financialInstrumentId;
	private String status;
	public Long getOrderBookId() {
		return orderBookId;
	}
	public void setOrderBookId(Long orderBookId) {
		this.orderBookId = orderBookId;
	}
	public Long getFinancialInstrumentId() {
		return financialInstrumentId;
	}
	public void setFinancialInstrumentId(Long financialInstrumentId) {
		this.financialInstrumentId = financialInstrumentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
