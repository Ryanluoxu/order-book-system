package io.ryanluoxu.orderbook.bean.financialinstrument;

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
@Table(name="financial_instrument")
public class FinancialInstrument implements Serializable{

	private static final long serialVersionUID = -8327354548583533727L;

	@Id
	@Column(name="financial_instrument_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_instrument_seq_gen")
	@SequenceGenerator(name = "financial_instrument_seq_gen", sequenceName = "financial_instrument_id_seq", initialValue = 1, allocationSize = 1)
	private Long financialInstrumentId;

	@Column(name="type")
	private String type;
	@Column(name="code")
	private String code;
	@Column(name="name")
	private String name;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="status")
	private String status;
	public Long getFinancialInstrumentId() {
		return financialInstrumentId;
	}
	public void setFinancialInstrumentId(Long financialInstrumentId) {
		this.financialInstrumentId = financialInstrumentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
