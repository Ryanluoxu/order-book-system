package io.ryanluoxu.orderbook.financialinstrument;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrument;
import io.ryanluoxu.orderbook.common.constant.StatusConstant;

@Service
@Transactional
public class FinancialInstrumentServiceImpl implements FinancialInstrumentService {

	@Autowired
	private FinancialInstrumentDao financialInstrumentDao;

	@Override
	public FinancialInstrument add(FinancialInstrument financialInstrument) {
		financialInstrument.setCreatedDate(new Date());
		financialInstrument.setStatus(StatusConstant.ACTIVE);
		return financialInstrumentDao.add(financialInstrument);
	}
	
	@Override
	public FinancialInstrument deleteById(Long financialInstrumentId) {
		return financialInstrumentDao.deleteById(financialInstrumentId);
	}

	@Override
	public FinancialInstrument update(FinancialInstrument financialInstrument) {
		return financialInstrumentDao.update(financialInstrument);
	}

	@Override
	public List<FinancialInstrument> findAll() {
		return financialInstrumentDao.findAll();
	}

	@Override
	public FinancialInstrument getById(Long financialInstrumentId) {
		return financialInstrumentDao.getById(financialInstrumentId);
	}

	@Override
	public List<FinancialInstrument> findByName(String name) {
		return financialInstrumentDao.findByName(name);
	}

	@Override
	public List<FinancialInstrument> findByCode(String code) {
		return financialInstrumentDao.findByCode(code);
	}

}
