package io.ryanluoxu.orderbook.financialinstrument;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrumentInput;
import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrumentVO;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.constant.TypeConstant;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinancialInstrumentControllerTest {

	@Autowired
	private FinancialInstrumentController financialInstrumentController;
	
	/**
	 * test missing fields: type, code, name
	 */
	private void testValidateMissingField(FinancialInstrumentInput input, String missingField) {
		try {
			financialInstrumentController.validate(input, ActionTypeConstant.ACTION_TYPE_ADD);
		} catch (CommonException e) {
			Assert.assertThat(e.getErrorMsg(), Matchers.allOf(
							Matchers.containsString("missing"),
							Matchers.containsString(missingField)));
			System.out.println(e.getErrorMsg());
		}
	}
	
	@Test
	public void testMissingType() {
		FinancialInstrumentInput input = getInputForAdd();
		input.setType(null);
		String missingField = "type";
		testValidateMissingField(input, missingField);
	}

	@Test
	public void testMissingCode() {
		FinancialInstrumentInput input = getInputForAdd();
		input.setCode(null);
		String missingField = "code";
		testValidateMissingField(input, missingField);
	}

	@Test
	public void testMissingName() {
		FinancialInstrumentInput input = getInputForAdd();
		input.setName(null);
		String missingField = "name";
		testValidateMissingField(input, missingField);
	}
	
	/**
	 * test validate fields: no existing code, name
	 */
	private void testValidateValidField(FinancialInstrumentInput input, String validateField) {
		String errorMsg = null;
		try {
			financialInstrumentController.validate(input, ActionTypeConstant.ACTION_TYPE_ADD);
		} catch (CommonException e) {
			errorMsg = e.getErrorMsg();
		}
		Assert.assertThat(errorMsg, Matchers.allOf(
				Matchers.containsString("exist"),
				Matchers.containsString(validateField)));
	}
	
	@Test
	public void testValidateValidCode() {
		FinancialInstrumentInput input = getInputForAdd();
		input.setCode("X0011");
		String validateField = "code";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testValidateValidName() {
		FinancialInstrumentInput input = getInputForAdd();
		input.setCode("X");
		input.setName("X Stock 02");
		String validateField = "name";
		testValidateValidField(input, validateField);
	}

	private FinancialInstrumentInput getInputForAdd() {
		FinancialInstrumentInput input = new FinancialInstrumentInput();
		input.setCode("X0011");
		input.setName("X Stock 02");
		input.setType(TypeConstant.FINANCIAL_INSTRUMENT_TYPE_STOCK);
		return input;
	}

	@Test
	public void testAdd() {
		FinancialInstrumentInput input = getInputForAdd();
		FinancialInstrumentVO vo = financialInstrumentController.add(input);
		Assert.assertThat(vo.getCode(), Matchers.is("X0011"));
	}
	
	@Test
	public void testFindAll() {
		List<FinancialInstrumentVO> vos = financialInstrumentController.findAll();
		Assert.assertThat(vos.size(), Matchers.greaterThan(0));
	}

}
