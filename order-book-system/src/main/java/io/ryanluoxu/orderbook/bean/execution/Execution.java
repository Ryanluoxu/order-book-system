package io.ryanluoxu.orderbook.bean.execution;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="execution")
public class Execution implements Serializable{

	private static final long serialVersionUID = -1555121241012703378L;

	@Id
	@Column(name="execution_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "execution_seq_gen")
	@SequenceGenerator(name = "execution_seq_gen", sequenceName = "execution_id_seq", initialValue = 1, allocationSize = 1)
	private Long executionId;

	@Column(name="order_book_id")
	private Long orderBookId;
	@Column(name="order_info_id")
	private Long orderInfoId;
	@Column(name="quantity")
	private BigDecimal quantity;
	@Column(name="price")
	private BigDecimal price;
	@Column(name="execution_date")
	private Date executionDate;
	
	public Long getOrderInfoId() {
		return orderInfoId;
	}
	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}
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
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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
}
