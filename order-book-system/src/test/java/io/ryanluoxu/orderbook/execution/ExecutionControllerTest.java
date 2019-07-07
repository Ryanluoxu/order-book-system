package io.ryanluoxu.orderbook.execution;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.ryanluoxu.orderbook.bean.execution.ExecutionInput;
import io.ryanluoxu.orderbook.bean.execution.ExecutionVO;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExecutionControllerTest {

	@Autowired
	private ExecutionController executionController;
	
	/**
	 * test missing fields: orderBookId, price, (orderInfoId, Quantity)
	 */
	private void testValidateMissingField(ExecutionInput input, String missingField) {
		try {
			executionController.validate(input, ActionTypeConstant.ACTION_TYPE_ADD);
		} catch (CommonException e) {
			Assert.assertThat(e.getErrorMsg(), Matchers.allOf(
							Matchers.containsString("missing"),
							Matchers.containsString(missingField)));
			System.out.println(e.getErrorMsg());
		}
	}
	
	private ExecutionInput getInputForAdd() {
		ExecutionInput input = new ExecutionInput();
		input.setOrderBookId(1L);
		input.setPrice(BigDecimal.valueOf(1.5));
		Map<Long, BigDecimal> orderInfoIdToQuantityMap = new HashMap<>();
		orderInfoIdToQuantityMap.put(3L, BigDecimal.valueOf(10));
		orderInfoIdToQuantityMap.put(2L, BigDecimal.valueOf(5));
		input.setOrderInfoIdToQuantityMap(orderInfoIdToQuantityMap);
		return input;
	}
	
	@Test
	public void testMissingOrderBookId() {
		ExecutionInput input = getInputForAdd();
		input.setOrderBookId(null);
		String missingField = "order book id";
		testValidateMissingField(input, missingField);
	}
	
	@Test
	public void testMissingPrice() {
		ExecutionInput input = getInputForAdd();
		input.setPrice(null);
		String missingField = "price";
		testValidateMissingField(input, missingField);
	}

	@Test
	public void testMissingOrderInfoId() {
		ExecutionInput input = getInputForAdd();
		input.setOrderInfoIdToQuantityMap(null);
		String missingField = "order info id";
		testValidateMissingField(input, missingField);
	}

	@Test
	public void testMissingQuantity() {
		ExecutionInput input = getInputForAdd();
		Map<Long, BigDecimal> map = input.getOrderInfoIdToQuantityMap();
		for (Entry<Long, BigDecimal> entry : map.entrySet()) {
			entry.setValue(null);
			break;
		}
		String missingField = "quantity";
		testValidateMissingField(input, missingField);
	}


	/**
	 * test validate fields: valid orderBookId, price, (orderInfoId, Quantity), orderBook status 
	 */
	private void testValidateValidField(ExecutionInput input, String validateField) {
		String errorMsg = null;
		try {
			executionController.validate(input, ActionTypeConstant.ACTION_TYPE_ADD);
		} catch (CommonException e) {
			errorMsg = e.getErrorMsg();
		}
		Assert.assertThat(errorMsg, Matchers.allOf(
				Matchers.containsString("Error"),
				Matchers.containsString(validateField)));
	}
	
	@Test
	public void testValidateValidOrderBookId() {
		ExecutionInput input = getInputForAdd();
		input.setOrderBookId(11L);
		String validateField = "order book id";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testValidateValidPrice() {
		ExecutionInput input = getInputForAdd();
		input.setPrice(BigDecimal.ZERO);
		String validateField = "price";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testValidateValidOrderInfoId() {
		ExecutionInput input = getInputForAdd();
		Map<Long, BigDecimal> map = new HashMap<>();
		map.put(111L, BigDecimal.ONE);
		input.setOrderInfoIdToQuantityMap(map);
		String validateField = "order info id";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testValidateValidQuantity() {
		ExecutionInput input = getInputForAdd();
		Map<Long, BigDecimal> map = new HashMap<>();
		map.put(3L, BigDecimal.ZERO);
		input.setOrderInfoIdToQuantityMap(map);
		String validateField = "quantity";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testValidateValidOrderBookStatus() {
		ExecutionInput input = getInputForAdd();
		String validateField = "order book";
		testValidateValidField(input, validateField);
	}
	
	@Test
	public void testAdd() {
		ExecutionInput input = getInputForAdd();
		List<ExecutionVO> vos = executionController.addExecution(input);
		Assert.assertThat(vos.size(), Matchers.greaterThan(0));
	}
	
	@Test
	public void testFindAll() {
		List<ExecutionVO> vos = executionController.findAll();
		Assert.assertThat(vos.size(), Matchers.greaterThan(0));
	}
	
}
