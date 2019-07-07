package io.ryanluoxu.orderbook.financialinstrument;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrument;
import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrumentInput;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.exception.CommonError;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@Service
public class FinancialInstrumentValidatorImpl implements FinancialInstrumentValidator {
	
	@Autowired
	private FinancialInstrumentService financialInstrumentService;
	
	@Override
	public void validateMandatoryFields(FinancialInstrumentInput financialInstrumentInput, String actionType) throws CommonException {
		if (ActionTypeConstant.ACTION_TYPE_ADD.equals(actionType)) {
			/*
			 * type, code, name
			 */
			checkMissingType(financialInstrumentInput);
			checkMissingCode(financialInstrumentInput);
			checkMissingName(financialInstrumentInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_DELETE.equals(actionType)) {
			checkMissingFinancialInstrumentId(financialInstrumentInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_UPDATE.equals(actionType)) {
			checkMissingFinancialInstrumentId(financialInstrumentInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_FIND.equals(actionType)) {
			
		} else {
			throw new CommonException(CommonError.INVALID_ACTION_TYPE);
		}
	}

	@Override
	public void validateInputValue(FinancialInstrumentInput financialInstrumentInput, String actionType) throws CommonException {
		if (ActionTypeConstant.ACTION_TYPE_ADD.equals(actionType)) {
			/*
			 * no existing code, name;
			 */
			validateCode(financialInstrumentInput);
			validateName(financialInstrumentInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_UPDATE.equals(actionType)) {
			validateFinancialInstrumentId(financialInstrumentInput);
			
		} else if (ActionTypeConstant.ACTION_TYPE_FIND.equals(actionType)) {
			
		} else if (ActionTypeConstant.ACTION_TYPE_DELETE.equals(actionType)) {
			validateFinancialInstrumentId(financialInstrumentInput);
			
		} else {
			throw new CommonException(CommonError.INVALID_ACTION_TYPE);
		}
	}








	/**
	 * check missing fields
	 */
	private void checkMissingFinancialInstrumentId(FinancialInstrumentInput financialInstrumentInput) throws CommonException {
		if (financialInstrumentInput.getFinancialInstrumentId() == null) {
			throw new CommonException(CommonError.MISSING_FINANCIAL_INSTRUMENT_ID);
		}
	}
	private void checkMissingName(FinancialInstrumentInput financialInstrumentInput) throws CommonException {
		if (financialInstrumentInput.getName() == null) {
			throw new CommonException(CommonError.MISSING_FINANCIAL_INSTRUMENT_NAME);
		}
	}
	private void checkMissingCode(FinancialInstrumentInput financialInstrumentInput) throws CommonException {
		if (financialInstrumentInput.getCode() == null) {
			throw new CommonException(CommonError.MISSING_FINANCIAL_INSTRUMENT_CODE);
		}
	}
	private void checkMissingType(FinancialInstrumentInput financialInstrumentInput) throws CommonException {
		if (financialInstrumentInput.getType() == null) {
			throw new CommonException(CommonError.MISSING_FINANCIAL_INSTRUMENT_TYPE);
		}
	}
	
	/**
	 * validate fields
	 */
	private void validateFinancialInstrumentId(FinancialInstrumentInput financialInstrumentInput) throws CommonException {
		
	}
	private void validateName(FinancialInstrumentInput financialInstrumentInput) throws CommonException {
		List<FinancialInstrument> financialInstruments = financialInstrumentService.findByName(financialInstrumentInput.getName());
		if (!CollectionUtils.isEmpty(financialInstruments)) {
			throw new CommonException(CommonError.VALIDATE_FINANCIAL_INSTRUMENT_NAME);
		}
	}
	private void validateCode(FinancialInstrumentInput financialInstrumentInput) throws CommonException {
		List<FinancialInstrument> financialInstruments = financialInstrumentService.findByCode(financialInstrumentInput.getCode());
		if (!CollectionUtils.isEmpty(financialInstruments)) {
			throw new CommonException(CommonError.VALIDATE_FINANCIAL_INSTRUMENT_CODE);
		}
	}


}
