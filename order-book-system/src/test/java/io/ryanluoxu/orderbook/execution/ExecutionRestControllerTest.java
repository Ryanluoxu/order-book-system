package io.ryanluoxu.orderbook.execution;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.ryanluoxu.orderbook.bean.execution.ExecutionInput;
import io.ryanluoxu.orderbook.common.RestControllerTest;
import io.ryanluoxu.orderbook.common.constant.PathConstant;
import io.ryanluoxu.orderbook.common.util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExecutionRestControllerTest extends RestControllerTest {
	
    @Test
	public void testAdd() throws Exception {
		String jsonInput = JsonUtil.getJsonString(getInputForAdd());
		MvcResult result =
			mvc.perform(MockMvcRequestBuilders.post(PathConstant.REST_EXECUTION_ADD)
					.content(jsonInput)
					.contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	        )
	       .andExpect(MockMvcResultMatchers.status().isOk())
	       .andReturn();
		printResult(result);
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
	public void testFindAll() throws Exception {
		MvcResult result =
	        mvc.perform(MockMvcRequestBuilders.post(PathConstant.REST_EXECUTION_FIND_ALL)
	        		.accept(MediaType.APPLICATION_JSON)
	        )
	       .andExpect(MockMvcResultMatchers.status().isOk())
	       .andReturn();
		printResult(result);
	}

}
