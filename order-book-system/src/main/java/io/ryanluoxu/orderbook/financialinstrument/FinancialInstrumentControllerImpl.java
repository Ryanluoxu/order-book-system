package io.ryanluoxu.orderbook.financialinstrument;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrument;
import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrumentInput;
import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrumentVO;
import io.ryanluoxu.orderbook.common.GenericControllerImpl;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@Controller
public class FinancialInstrumentControllerImpl extends GenericControllerImpl<FinancialInstrument, FinancialInstrumentVO, FinancialInstrumentInput> implements FinancialInstrumentController {

	@Override
	public List<FinancialInstrumentVO> findAll() {
		List<FinancialInstrumentVO> financialInstrumentVOs = new ArrayList<>();
		List<FinancialInstrument> financialInstruments = financialInstrumentService.findAll();
		for (FinancialInstrument financialInstrument : financialInstruments) {
			financialInstrumentVOs.add(convertToVO(financialInstrument));
		}
		return financialInstrumentVOs;
	}

	@Override
	public FinancialInstrumentVO add(FinancialInstrumentInput input) {
		FinancialInstrument bean = convertToBean(input);
		bean.setFinancialInstrumentId(null);
		return convertToVO(financialInstrumentService.add(bean));
	}

	@Override
	public FinancialInstrumentVO update(FinancialInstrumentInput input) {
		return null;
	}

	@Override
	public FinancialInstrumentVO delete(FinancialInstrumentInput input) {
		return null;
	}

	@Override
	public void validate(FinancialInstrumentInput input, String actionType) throws CommonException {
		financialInstrumentValidator.validateMandatoryFields(input, actionType);
		financialInstrumentValidator.validateInputValue(input, actionType);
	}

}
