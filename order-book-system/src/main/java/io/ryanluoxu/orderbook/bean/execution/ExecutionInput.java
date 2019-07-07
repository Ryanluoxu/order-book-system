package io.ryanluoxu.orderbook.bean.execution;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class ExecutionInput{
	private Long executionId;
	private Long orderBookId;
	private BigDecimal price;
	private Date executionDate;
	private Map<Long, BigDecimal> orderInfoIdToQuantityMap;
	
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
	public Map<Long, BigDecimal> getOrderInfoIdToQuantityMap() {
		return orderInfoIdToQuantityMap;
	}
	public void setOrderInfoIdToQuantityMap(Map<Long, BigDecimal> orderInfoIdToQuantityMap) {
		this.orderInfoIdToQuantityMap = orderInfoIdToQuantityMap;
	}
	
}
