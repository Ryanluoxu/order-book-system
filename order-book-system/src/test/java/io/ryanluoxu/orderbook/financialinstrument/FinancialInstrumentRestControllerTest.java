package io.ryanluoxu.orderbook.financialinstrument;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.ryanluoxu.orderbook.bean.financialinstrument.FinancialInstrumentInput;
import io.ryanluoxu.orderbook.common.RestControllerTest;
import io.ryanluoxu.orderbook.common.constant.PathConstant;
import io.ryanluoxu.orderbook.common.constant.TypeConstant;
import io.ryanluoxu.orderbook.common.util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinancialInstrumentRestControllerTest extends RestControllerTest {
    
	@Test
	public void testAdd() throws Exception {
		String jsonInput = JsonUtil.getJsonString(getInputForAdd());
		MvcResult result =
			mvc.perform(MockMvcRequestBuilders.post(PathConstant.REST_FINANCIAL_INSTRUMENT_ADD)
					.content(jsonInput)
					.contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	        )
	       .andExpect(MockMvcResultMatchers.status().isOk())
	       .andReturn();
		printResult(result);
	}

	private FinancialInstrumentInput getInputForAdd() {
		FinancialInstrumentInput input = new FinancialInstrumentInput();
		input.setCode("X0022");
		input.setName("X Stock 22");
		input.setType(TypeConstant.FINANCIAL_INSTRUMENT_TYPE_STOCK);
		return input;
	}

	@Test
	public void testFindAll() throws Exception {
		MvcResult result =
	        mvc.perform(MockMvcRequestBuilders.post(PathConstant.REST_FINANCIAL_INSTRUMENT_FIND_ALL)
	        		.accept(MediaType.APPLICATION_JSON)
	        )
	       .andExpect(MockMvcResultMatchers.status().isOk())
	       .andReturn();
		printResult(result);
	}

}
