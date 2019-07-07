package io.ryanluoxu.orderbook.orderbook;

import java.util.List;

import io.ryanluoxu.orderbook.bean.orderbook.OrderBook;
import io.ryanluoxu.orderbook.common.GenericService;

public interface OrderBookService extends GenericService<OrderBook, Long>{

	OrderBook getByFinancialInstrumentId(Long financialInstrumentId);
	List<OrderBook> findByFinancialInstrumentId(Long financialInstrumentId);

}
