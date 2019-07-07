package io.ryanluoxu.orderbook.orderbook;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.ryanluoxu.orderbook.bean.orderbook.OrderBookInput;
import io.ryanluoxu.orderbook.common.RestControllerTest;
import io.ryanluoxu.orderbook.common.constant.PathConstant;
import io.ryanluoxu.orderbook.common.util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderBookRestControllerTest extends RestControllerTest {
	
    @Test
	public void testAdd() throws Exception {
		String jsonInput = JsonUtil.getJsonString(getInputForAdd());
		MvcResult result =
			mvc.perform(MockMvcRequestBuilders.post(PathConstant.REST_ORDER_BOOK_ADD)
					.content(jsonInput)
					.contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	        )
	       .andExpect(MockMvcResultMatchers.status().isOk())
	       .andReturn();
		printResult(result);
	}
	
	private OrderBookInput getInputForAdd() {
		OrderBookInput input = new OrderBookInput();
		input.setFinancialInstrumentId(6L);
		return input;
	}

	@Test
	public void testFindAll() throws Exception {
		MvcResult result =
	        mvc.perform(MockMvcRequestBuilders.post(PathConstant.REST_ORDER_BOOK_FIND_ALL)
	        		.accept(MediaType.APPLICATION_JSON)
	        )
	       .andExpect(MockMvcResultMatchers.status().isOk())
	       .andReturn();
		printResult(result);
	}
	
	@Test
	public void testOpenOrderBook() throws Exception {
		MvcResult result =
			mvc.perform(MockMvcRequestBuilders.post(PathConstant.REST_ORDER_BOOK_OPEN)
					.param("orderBookId", "1")
	                .accept(MediaType.APPLICATION_JSON)
	        )
	       .andExpect(MockMvcResultMatchers.status().isOk())
	       .andReturn();
		printResult(result);
	}
	
	@Test
	public void testCloseOrderBook() throws Exception {
		MvcResult result =
			mvc.perform(MockMvcRequestBuilders.post(PathConstant.REST_ORDER_BOOK_CLOSE)
					.param("orderBookId", "1")
	                .accept(MediaType.APPLICATION_JSON)
	        )
	       .andExpect(MockMvcResultMatchers.status().isOk())
	       .andReturn();
		printResult(result);
	}

}
