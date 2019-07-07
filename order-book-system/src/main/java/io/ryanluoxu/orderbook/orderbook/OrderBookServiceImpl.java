package io.ryanluoxu.orderbook.orderbook;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ryanluoxu.orderbook.bean.orderbook.OrderBook;

@Service
@Transactional
public class OrderBookServiceImpl implements OrderBookService {

	@Autowired
	private OrderBookDao orderBookDao;

	@Override
	public OrderBook add(OrderBook orderBook) {
		return orderBookDao.add(orderBook);
	}
	
	@Override
	public OrderBook deleteById(Long orderBookId) {
		return orderBookDao.deleteById(orderBookId);
	}

	@Override
	public OrderBook update(OrderBook orderBook) {
		orderBook.setUpdatedDate(new Date());
		return orderBookDao.update(orderBook);
	}

	@Override
	public List<OrderBook> findAll() {
		return orderBookDao.findAll();
	}

	@Override
	public OrderBook getById(Long orderBookId) {
		return orderBookDao.getById(orderBookId);
	}

	@Override
	public OrderBook getByFinancialInstrumentId(Long financialInstrumentId) {
		return orderBookDao.getByFinancialInstrumentId(financialInstrumentId);
	}

	@Override
	public List<OrderBook> findByFinancialInstrumentId(Long financialInstrumentId) {
		return orderBookDao.findByFinancialInstrumentId(financialInstrumentId);
	}

}
