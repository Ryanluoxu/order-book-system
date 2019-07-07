package io.ryanluoxu.orderbook.common;

import io.ryanluoxu.orderbook.common.bean.ResponseModel;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.constant.StatusConstant;
import io.ryanluoxu.orderbook.common.exception.CommonException;

public class GenericRestController<T, TVO, TInput> {
	
	/**
	 * return one single VO
	 * @param actionType
	 * @param input
	 * @param controller
	 * @return
	 */
	protected ResponseModel<TVO> getResponse(String actionType, TInput input, GenericController<T, TVO, TInput> controller) {
		/**
		 * steps:
		 * - validate input
		 * - actual action (CURD)
		 * - return VO with status
		 */
		ResponseModel<TVO> response = new ResponseModel<>();
		try {
			String responseStatus = StatusConstant.RESPONSE_SUCCESS;
			controller.validate(input, actionType);
			TVO returnVO;
			switch (actionType) {
			case ActionTypeConstant.ACTION_TYPE_ADD:
				returnVO = controller.add(input);
				break;
			case ActionTypeConstant.ACTION_TYPE_DELETE:
				returnVO = controller.delete(input);
				break;	
			case ActionTypeConstant.ACTION_TYPE_UPDATE:
				returnVO = controller.update(input);
				break;
			default:
				returnVO = null;
				responseStatus = StatusConstant.RESPONSE_FAIL;
				break;
			}
			response.setStatus(responseStatus);
			response.setData(returnVO);
		} catch (CommonException e) {
			response.setStatus(StatusConstant.RESPONSE_FAIL);
			response.setErrorMsg(e.getErrorMsg());
		}
		return response;
	}

}
