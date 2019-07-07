package io.ryanluoxu.orderbook.orderinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfo;
import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfoInput;
import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfoVO;
import io.ryanluoxu.orderbook.common.GenericRestController;
import io.ryanluoxu.orderbook.common.bean.ResponseModel;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.constant.StatusConstant;

@RestController
@RequestMapping("/rest/orderInfo")
public class OrderInfoRestController extends GenericRestController<OrderInfo, OrderInfoVO, OrderInfoInput> {

	@Autowired
	private OrderInfoController orderInfoController;

	/**
	 * return one single VO
	 */
    @PostMapping("/add")
    public ResponseModel<OrderInfoVO> add(@RequestBody OrderInfoInput orderInfoInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_ADD, orderInfoInput, orderInfoController);
	}
	
	@PostMapping("/delete")
	public ResponseModel<OrderInfoVO> delete(@RequestBody OrderInfoInput orderInfoInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_DELETE, orderInfoInput, orderInfoController);
	}
	
	@PostMapping("/update")
	public ResponseModel<OrderInfoVO> update(@RequestBody OrderInfoInput orderInfoInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_UPDATE, orderInfoInput, orderInfoController);
	}

	/**
	 * return a list of VO
	 */
	@PostMapping("/findAll")
	public ResponseModel<List<OrderInfoVO>> findAll(){
		ResponseModel<List<OrderInfoVO>> response = new ResponseModel<>();
		try {
			List<OrderInfoVO> orderInfoVOs = orderInfoController.findAll();
			response.setStatus(StatusConstant.RESPONSE_SUCCESS);
			response.setData(orderInfoVOs);
		} catch (Exception e) {
			response.setStatus(StatusConstant.RESPONSE_FAIL);
			response.setErrorMsg(e.toString());
		}
		return response;
	}

}
