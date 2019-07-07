package io.ryanluoxu.orderbook.orderbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.ryanluoxu.orderbook.bean.orderbook.OrderBook;
import io.ryanluoxu.orderbook.bean.orderbook.OrderBookInput;
import io.ryanluoxu.orderbook.bean.orderbook.OrderBookVO;
import io.ryanluoxu.orderbook.common.GenericRestController;
import io.ryanluoxu.orderbook.common.bean.ResponseModel;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.constant.StatusConstant;

@RestController
@RequestMapping("/rest/orderBook")
public class OrderBookRestController extends GenericRestController<OrderBook, OrderBookVO, OrderBookInput> {

	@Autowired
	private OrderBookController orderBookController;
	
	/**
	 * return one single VO
	 */
    @PostMapping("/add")
    public ResponseModel<OrderBookVO> add(@RequestBody OrderBookInput orderBookInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_ADD, orderBookInput, orderBookController);
	}
	
	@PostMapping("/delete")
	public ResponseModel<OrderBookVO> delete(@RequestBody OrderBookInput orderBookInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_DELETE, orderBookInput, orderBookController);
	}
	
	@PostMapping("/update")
	public ResponseModel<OrderBookVO> update(@RequestBody OrderBookInput orderBookInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_UPDATE, orderBookInput, orderBookController);
	}
	@PostMapping("/openOrderBook")
	public ResponseModel<OrderBookVO> openOrderBook(@RequestParam Long orderBookId){
		OrderBookInput orderBookInput = new OrderBookInput();
		orderBookInput.setOrderBookId(orderBookId);
		orderBookInput.setStatus(StatusConstant.ORDER_BOOK_OPEN);
		return update(orderBookInput);
	}
	@PostMapping("/closeOrderBook")
	public ResponseModel<OrderBookVO> closeOrderBook(@RequestParam Long orderBookId){
		OrderBookInput orderBookInput = new OrderBookInput();
		orderBookInput.setOrderBookId(orderBookId);
		orderBookInput.setStatus(StatusConstant.ORDER_BOOK_CLOSE);
		return update(orderBookInput);
	}


	/**
	 * return a list of VO
	 */
	@PostMapping("/findAll")
	public ResponseModel<List<OrderBookVO>> findAll(){
		ResponseModel<List<OrderBookVO>> response = new ResponseModel<>();
		try {
			List<OrderBookVO> orderBookVOs = orderBookController.findAll();
			response.setStatus(StatusConstant.RESPONSE_SUCCESS);
			response.setData(orderBookVOs);
		} catch (Exception e) {
			response.setStatus(StatusConstant.RESPONSE_FAIL);
			response.setErrorMsg(e.toString());
		}
		return response;
	}

}
