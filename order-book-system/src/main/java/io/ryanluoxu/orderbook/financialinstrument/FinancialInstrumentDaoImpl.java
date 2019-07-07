package io.ryanluoxu.orderbook.financialinstrument;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrument;
import io.ryanluoxu.orderbook.common.GenericDaoImpl;

@Repository
public class FinancialInstrumentDaoImpl extends GenericDaoImpl<FinancialInstrument, Long> implements FinancialInstrumentDao {
	
	private static final String NAME = "name";
	private static final String CODE = "code";

	@Override
	public List<FinancialInstrument> findByName(String name) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<FinancialInstrument> criteriaQuery = criteriaBuilder.createQuery(FinancialInstrument.class);
		Root<FinancialInstrument> root = criteriaQuery.from(FinancialInstrument.class);
		criteriaQuery.select(root);

		criteriaQuery.where(
				criteriaBuilder.and(
						criteriaBuilder.equal(root.get(NAME), name)
				));		
		
		return getSession().createQuery(criteriaQuery).getResultList();	
	}

	@Override
	public List<FinancialInstrument> findByCode(String code) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<FinancialInstrument> criteriaQuery = criteriaBuilder.createQuery(FinancialInstrument.class);
		Root<FinancialInstrument> root = criteriaQuery.from(FinancialInstrument.class);
		criteriaQuery.select(root);

		criteriaQuery.where(
				criteriaBuilder.and(
						criteriaBuilder.equal(root.get(CODE), code)
				));		
		
		return getSession().createQuery(criteriaQuery).getResultList();	
	}

}