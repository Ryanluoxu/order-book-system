package io.ryanluoxu.orderbook.bean.execution;

import java.math.BigDecimal;
import java.util.Date;

public class ExecutionVO {
	private Long executionId;
	private Long orderBookId;
	private BigDecimal price;
	private Date executionDate;
	private Long orderInfoId;
	private BigDecimal quantity;
	
	public Long getExecutionId() {
		return executionId;
	}
	public void setExecutionId(Long executionId) {
		this.executionId = executionId;
	}
	public Long getOrderBookId() {
		return orderBookId;
	}
	public void setOrderBookId(Long orderBookId) {
		this.orderBookId = orderBookId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
	public Long getOrderInfoId() {
		return orderInfoId;
	}
	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}
