package io.ryanluoxu.orderbook.bean.financialinstrument;

public class FinancialInstrumentInput {
	private Long financialInstrumentId;
	private String type;
	private String code;
	private String name;
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
}
