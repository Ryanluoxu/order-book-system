package io.ryanluoxu.orderbook.financialinstrument;

import java.util.List;

import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrument;
import io.ryanluoxu.orderbook.common.GenericService;

public interface FinancialInstrumentService extends GenericService<FinancialInstrument, Long>{

	List<FinancialInstrument> findByName(String name);

	List<FinancialInstrument> findByCode(String code);

}
