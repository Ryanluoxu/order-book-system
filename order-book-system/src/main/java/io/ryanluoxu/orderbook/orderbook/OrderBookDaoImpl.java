package io.ryanluoxu.orderbook.orderbook;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import io.ryanluoxu.orderbook.bean.orderbook.OrderBook;
import io.ryanluoxu.orderbook.common.GenericDaoImpl;

@Repository
public class OrderBookDaoImpl extends GenericDaoImpl<OrderBook, Long> implements OrderBookDao {
	
	private static final String FINANCIAL_INSTRUMENT_ID = "financialInstrumentId";
	
	@Override
	public OrderBook getByFinancialInstrumentId(Long financialInstrumentId) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<OrderBook> criteriaQuery = criteriaBuilder.createQuery(OrderBook.class);
		Root<OrderBook> root = criteriaQuery.from(OrderBook.class);
		criteriaQuery.select(root);

		criteriaQuery.where(
				criteriaBuilder.and(
						criteriaBuilder.equal(root.get(FINANCIAL_INSTRUMENT_ID), financialInstrumentId)
				));		
		
		return getSession().createQuery(criteriaQuery).getSingleResult();
	}

	@Override
	public List<OrderBook> findByFinancialInstrumentId(Long financialInstrumentId) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<OrderBook> criteriaQuery = criteriaBuilder.createQuery(OrderBook.class);
		Root<OrderBook> root = criteriaQuery.from(OrderBook.class);
		criteriaQuery.select(root);

		criteriaQuery.where(
				criteriaBuilder.and(
						criteriaBuilder.equal(root.get(FINANCIAL_INSTRUMENT_ID), financialInstrumentId)
				));		
		
		return getSession().createQuery(criteriaQuery).getResultList();
	}

}