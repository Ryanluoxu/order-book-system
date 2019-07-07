package io.ryanluoxu.orderbook.orderbook;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import io.ryanluoxu.orderbook.bean.orderbook.OrderBook;
import io.ryanluoxu.orderbook.bean.orderbook.OrderBookInput;
import io.ryanluoxu.orderbook.bean.orderbook.OrderBookVO;
import io.ryanluoxu.orderbook.common.GenericControllerImpl;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@Controller
public class OrderBookControllerImpl extends GenericControllerImpl<OrderBook, OrderBookVO, OrderBookInput> implements OrderBookController {

	@Override
	public List<OrderBookVO> findAll() {
		List<OrderBookVO> orderBookVOs = new ArrayList<>();
		List<OrderBook> orderBooks = orderBookService.findAll();
		for (OrderBook orderBook : orderBooks) {
			orderBookVOs.add(convertToVO(orderBook));
		}
		return orderBookVOs;
	}

	@Override
	public OrderBookVO add(OrderBookInput input) {
		OrderBook bean = convertToBean(input);
		bean.setOrderBookId(null);
		return convertToVO(orderBookService.add(bean));
	}

	@Override
	public OrderBookVO update(OrderBookInput input) {
		OrderBook bean = orderBookService.getById(input.getOrderBookId());
		bean.setStatus(input.getStatus());
		return convertToVO(orderBookService.update(bean));
	}

	@Override
	public OrderBookVO delete(OrderBookInput input) {return null;}

	@Override
	public void validate(OrderBookInput input, String actionType) throws CommonException {
		orderBookValidator.validateMandatoryFields(input, actionType);
		orderBookValidator.validateInputValue(input, actionType);
	}

}
