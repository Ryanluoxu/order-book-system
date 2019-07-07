package io.ryanluoxu.orderbook.orderbook;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.ryanluoxu.orderbook.bean.orderbook.OrderBookInput;
import io.ryanluoxu.orderbook.bean.orderbook.OrderBookVO;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.constant.StatusConstant;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderBookControllerTest {

	@Autowired
	private OrderBookController orderBookController;
	
	/**
	 * test missing fields: financialInstrumentId
	 */
	private void testValidateMissingField(OrderBookInput input, String missingField) {
		try {
			orderBookController.validate(input, ActionTypeConstant.ACTION_TYPE_ADD);
		} catch (CommonException e) {
			Assert.assertThat(e.getErrorMsg(), Matchers.allOf(
							Matchers.containsString("missing"),
							Matchers.containsString(missingField)));
			System.out.println(e.getErrorMsg());
		}
	}
	
	@Test
	public void testMissingFinancialInstrumentId() {
		OrderBookInput input = getInputForAdd();
		input.setFinancialInstrumentId(null);
		String missingField = "financialInstrumentId";
		testValidateMissingField(input, missingField);
	}

	/**
	 * test validate fields: 
	 * 	- no duplicate orderBook for FinancialInstrumentId
	 */
	private void testValidateValidField(OrderBookInput input, String validateField) {
		String errorMsg = null;
		try {
			orderBookController.validate(input, ActionTypeConstant.ACTION_TYPE_ADD);
		} catch (CommonException e) {
			errorMsg = e.getErrorMsg();
		}
		Assert.assertThat(errorMsg, Matchers.allOf(
				Matchers.containsString("exist"),
				Matchers.containsString(validateField)));
	}
	
	/**
	 * test validate fields: 
	 * 	- same status for update
	 */
	private void testValidateValidFieldForUpdate(OrderBookInput input, String validateField) {
		String errorMsg = null;
		try {
			orderBookController.validate(input, ActionTypeConstant.ACTION_TYPE_UPDATE);
		} catch (CommonException e) {
			errorMsg = e.getErrorMsg();
		}
		Assert.assertThat(errorMsg, Matchers.containsString(validateField));
	}
	
	@Test
	public void testValidateValidFinancialInstrumentId() {
		OrderBookInput input = getInputForAdd();
		input.setFinancialInstrumentId(6L);
		String validateField = "financial instrument id";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testValidateValidStatus() {
		OrderBookInput input = getInputForUpdate();
		String validateField = "status";
		testValidateValidFieldForUpdate(input, validateField);
	}

	private OrderBookInput getInputForAdd() {
		OrderBookInput input = new OrderBookInput();
		input.setFinancialInstrumentId(6L);
		return input;
	}
	private OrderBookInput getInputForUpdate() {
		OrderBookInput input = new OrderBookInput();
		input.setOrderBookId(1L);
		input.setStatus(StatusConstant.ORDER_BOOK_CLOSE);
		return input;
	}
	
	@Test
	public void testAdd() {
		OrderBookInput input = getInputForAdd();
		OrderBookVO vo = orderBookController.add(input);
		Assert.assertThat(vo.getOrderBookId(), Matchers.greaterThan(0L));
	}
	
	@Test
	public void testFindAll() {
		List<OrderBookVO> vos = orderBookController.findAll();
		Assert.assertThat(vos.size(), Matchers.greaterThan(0));
	}
	
	@Test
	public void testUpdate() {
		OrderBookInput input = getInputForUpdate();
		OrderBookVO vo = orderBookController.update(input);
		Assert.assertThat(vo.getOrderBookId(), Matchers.greaterThan(0L));
	}

}
