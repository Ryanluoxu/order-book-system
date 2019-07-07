package io.ryanluoxu.orderbook.orderbook;

import java.util.List;

import io.ryanluoxu.orderbook.bean.orderbook.OrderBook;
import io.ryanluoxu.orderbook.common.GenericDao;

public interface OrderBookDao extends GenericDao<OrderBook, Long>{

	OrderBook getByFinancialInstrumentId(Long financialInstrumentId);
	List<OrderBook> findByFinancialInstrumentId(Long financialInstrumentId);

}
