package io.ryanluoxu.orderbook.bean.orderinfo;

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
@Table(name="order_info")
public class OrderInfo implements Serializable{

	private static final long serialVersionUID = -7140666400733378538L;

	@Id
	@Column(name="order_info_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_info_seq_gen")
	@SequenceGenerator(name = "order_info_seq_gen", sequenceName = "order_info_id_seq", initialValue = 1, allocationSize = 1)
	private Long orderInfoId;

	@Column(name="financial_instrument_id")
	private Long financialInstrumentId;
	@Column(name="order_book_id")
	private Long orderBookId;
	@Column(name="status")
	private String status;
	@Column(name="quantity")
	private BigDecimal quantity;
	@Column(name="price")
	private BigDecimal price;
	@Column(name="entry_date")
	private Date entryDate;
	@Column(name="order_type")
	private String orderType;	// market order / limit order
	
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
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Long getOrderInfoId() {
		return orderInfoId;
	}
	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}
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
