package io.ryanluoxu.orderbook.bean.orderbook;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="order_book")
public class OrderBook implements Serializable{

	private static final long serialVersionUID = -5962877235085811044L;

	@Id
	@Column(name="order_book_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_book_seq_gen")
	@SequenceGenerator(name = "order_book_seq_gen", sequenceName = "order_book_id_seq", initialValue = 1, allocationSize = 1)
	private Long orderBookId;

	@Column(name="financial_instrument_id")
	private Long financialInstrumentId;
	@Column(name="status")
	private String status;
	@Column(name="updated_by")
	private String updatedBy;
	@Column(name="updated_date")
	private Date updatedDate;
	
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
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
