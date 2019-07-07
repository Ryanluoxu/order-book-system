package io.ryanluoxu.orderbook.orderinfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import io.ryanluoxu.orderbook.bean.orderbook.OrderBook;
import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfo;
import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfoInput;
import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfoVO;
import io.ryanluoxu.orderbook.common.GenericControllerImpl;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@Controller
public class OrderInfoControllerImpl extends GenericControllerImpl<OrderInfo, OrderInfoVO, OrderInfoInput> implements OrderInfoController {

	@Override
	public List<OrderInfoVO> findAll() {
		List<OrderInfoVO> orderInfoVOs = new ArrayList<>();
		List<OrderInfo> orderInfos = orderInfoService.findAll();
		for (OrderInfo orderInfo : orderInfos) {
			orderInfoVOs.add(convertToVO(orderInfo));
		}
		return orderInfoVOs;
	}

	@Override
	public OrderInfoVO add(OrderInfoInput input) {
		OrderInfo bean = convertToBean(input);
		OrderBook orderBook = orderBookService.getByFinancialInstrumentId(input.getFinancialInstrumentId());
		bean.setOrderBookId(orderBook.getOrderBookId());
		bean.setOrderInfoId(null);
		return convertToVO(orderInfoService.add(bean));
	}

	@Override
	public OrderInfoVO update(OrderInfoInput input) {return null;}

	@Override
	public OrderInfoVO delete(OrderInfoInput input) {return null;}

	@Override
	public void validate(OrderInfoInput input, String actionType) throws CommonException {
		orderInfoValidator.validateMandatoryFields(input, actionType);
		orderInfoValidator.validateInputValue(input, actionType);
	}

}
