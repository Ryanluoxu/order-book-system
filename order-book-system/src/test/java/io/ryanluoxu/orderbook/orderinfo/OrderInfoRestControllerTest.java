package io.ryanluoxu.orderbook.orderinfo;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfoInput;
import io.ryanluoxu.orderbook.common.RestControllerTest;
import io.ryanluoxu.orderbook.common.constant.PathConstant;
import io.ryanluoxu.orderbook.common.constant.TypeConstant;
import io.ryanluoxu.orderbook.common.util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderInfoRestControllerTest extends RestControllerTest {
	
    @Test
	public void testAdd() throws Exception {
		String jsonInput = JsonUtil.getJsonString(getInputForAdd());
		MvcResult result =
			mvc.perform(MockMvcRequestBuilders.post(PathConstant.REST_ORDER_INFO_ADD)
					.content(jsonInput)
					.contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	        )
	       .andExpect(MockMvcResultMatchers.status().isOk())
	       .andReturn();
		printResult(result);
	}
	
	private OrderInfoInput getInputForAdd() {
		OrderInfoInput input = new OrderInfoInput();
		input.setFinancialInstrumentId(6L);
		input.setQuantity(BigDecimal.ONE);
		input.setOrderType(TypeConstant.ORDER_INFO_ORDER_TYPE_LIMIT);
		input.setPrice(BigDecimal.ONE);
		return input;
	}

	@Test
	public void testFindAll() throws Exception {
		MvcResult result =
	        mvc.perform(MockMvcRequestBuilders.post(PathConstant.REST_ORDER_INFO_FIND_ALL)
	        		.accept(MediaType.APPLICATION_JSON)
	        )
	       .andExpect(MockMvcResultMatchers.status().isOk())
	       .andReturn();
		printResult(result);
	}

}
