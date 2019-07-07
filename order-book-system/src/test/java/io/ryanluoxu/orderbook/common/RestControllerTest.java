package io.ryanluoxu.orderbook.common;


import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class RestControllerTest {
	
	@Autowired
    private WebApplicationContext wac;

    protected MockMvc mvc;
    
    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
	
    protected void printResult(MvcResult result) throws UnsupportedEncodingException {
		System.out.println(">>>");
		System.out.println(">>>");
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(">>>");
		System.out.println(">>>");
	}

}
