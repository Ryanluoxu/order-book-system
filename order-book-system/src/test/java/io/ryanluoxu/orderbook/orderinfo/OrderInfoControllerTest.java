package io.ryanluoxu.orderbook.orderinfo;

import java.math.BigDecimal;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfoInput;
import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfoVO;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.constant.TypeConstant;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderInfoControllerTest {

	@Autowired
	private OrderInfoController orderInfoController;
	
	/**
	 * test missing fields: financialInstrumentId, quantity, orderType, price
	 */
	private void testValidateMissingField(OrderInfoInput input, String missingField) {
		try {
			orderInfoController.validate(input, ActionTypeConstant.ACTION_TYPE_ADD);
		} catch (CommonException e) {
			Assert.assertThat(e.getErrorMsg(), Matchers.allOf(
							Matchers.containsString("missing"),
							Matchers.containsString(missingField)));
			System.out.println(e.getErrorMsg());
		}
	}
	
	private OrderInfoInput getInputForAdd() {
		OrderInfoInput input = new OrderInfoInput();
		input.setFinancialInstrumentId(6L);
		input.setOrderType(TypeConstant.ORDER_INFO_ORDER_TYPE_LIMIT);
		input.setPrice(BigDecimal.ONE);
		input.setQuantity(BigDecimal.ONE);
		return input;
	}
	
	@Test
	public void testMissingFinancialInstrumentId() {
		OrderInfoInput input = getInputForAdd();
		input.setFinancialInstrumentId(null);
		String missingField = "financial instrument id";
		testValidateMissingField(input, missingField);
	}
	
	@Test
	public void testMissingOrderType() {
		OrderInfoInput input = getInputForAdd();
		input.setOrderType(null);
		String missingField = "order type";
		testValidateMissingField(input, missingField);
	}

	@Test
	public void testMissingQuantity() {
		OrderInfoInput input = getInputForAdd();
		input.setQuantity(null);
		String missingField = "quantity";
		testValidateMissingField(input, missingField);
	}

	@Test
	public void testMissingPrice() {
		OrderInfoInput input = getInputForAdd();
		input.setPrice(null);
		String missingField = "price";
		testValidateMissingField(input, missingField);
	}

	/**
	 * test validate fields: valid financialInstrumentId, quantity, orderType, price, orderBook
	 */
	private void testValidateValidField(OrderInfoInput input, String validateField) {
		String errorMsg = null;
		try {
			orderInfoController.validate(input, ActionTypeConstant.ACTION_TYPE_ADD);
		} catch (CommonException e) {
			errorMsg = e.getErrorMsg();
		}
		Assert.assertThat(errorMsg, Matchers.allOf(
				Matchers.containsString("invalid"),
				Matchers.containsString(validateField)));
	}
	
	@Test
	public void testValidateValidFinancialInstrumentId() {
		OrderInfoInput input = getInputForAdd();
		input.setFinancialInstrumentId(111L);
		String validateField = "financial instrument id";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testValidateValidOrderType() {
		OrderInfoInput input = getInputForAdd();
		input.setOrderType("Test");
		String validateField = "order type";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testValidateValidQuantity() {
		OrderInfoInput input = getInputForAdd();
		input.setQuantity(BigDecimal.ZERO);
		String validateField = "quantity";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testValidateValidPrice() {
		OrderInfoInput input = getInputForAdd();
		input.setPrice(BigDecimal.ZERO);
		String validateField = "price";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testValidateValidOrderBook() {
		OrderInfoInput input = getInputForAdd();
		input.setFinancialInstrumentId(11L);
		String validateField = "order book";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testAdd() {
		OrderInfoInput input = getInputForAdd();
		OrderInfoVO vo = orderInfoController.add(input);
		Assert.assertThat(vo.getOrderInfoId(), Matchers.greaterThan(0L));
	}
	
	@Test
	public void testFindAll() {
		List<OrderInfoVO> vos = orderInfoController.findAll();
		Assert.assertThat(vos.size(), Matchers.greaterThan(0));
	}
	
}
