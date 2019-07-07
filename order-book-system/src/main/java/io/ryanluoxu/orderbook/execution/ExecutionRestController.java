package io.ryanluoxu.orderbook.execution;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ryanluoxu.orderbook.bean.execution.Execution;
import io.ryanluoxu.orderbook.bean.execution.ExecutionInput;
import io.ryanluoxu.orderbook.bean.execution.ExecutionVO;
import io.ryanluoxu.orderbook.common.GenericRestController;
import io.ryanluoxu.orderbook.common.bean.ResponseModel;
import io.ryanluoxu.orderbook.common.constant.ActionTypeConstant;
import io.ryanluoxu.orderbook.common.constant.StatusConstant;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@RestController
@RequestMapping("/rest/execution")
public class ExecutionRestController extends GenericRestController<Execution, ExecutionVO, ExecutionInput> {

	@Autowired
	private ExecutionController executionController;

	/**
	 * return one single VO
	 */
    @PostMapping("/add")
    public ResponseModel<ExecutionVO> add(@RequestBody ExecutionInput executionInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_ADD, executionInput, executionController);
	}
	
	@PostMapping("/delete")
	public ResponseModel<ExecutionVO> delete(@RequestBody ExecutionInput executionInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_DELETE, executionInput, executionController);
	}
	
	@PostMapping("/update")
	public ResponseModel<ExecutionVO> update(@RequestBody ExecutionInput executionInput){
		return getResponse(ActionTypeConstant.ACTION_TYPE_UPDATE, executionInput, executionController);
	}

	/**
	 * return a list of VO
	 */
	@PostMapping("/findAll")
	public ResponseModel<List<ExecutionVO>> findAll(){
		ResponseModel<List<ExecutionVO>> response = new ResponseModel<>();
		try {
			List<ExecutionVO> executionVOs = executionController.findAll();
			response.setStatus(StatusConstant.RESPONSE_SUCCESS);
			response.setData(executionVOs);
		} catch (Exception e) {
			response.setStatus(StatusConstant.RESPONSE_FAIL);
			response.setErrorMsg(e.toString());
		}
		return response;
	}
	
    @PostMapping("/addExecution")
    public ResponseModel<List<ExecutionVO>> addExecution(@RequestBody ExecutionInput executionInput){
    	ResponseModel<List<ExecutionVO>> response = new ResponseModel<>();
		try {
			executionController.validate(executionInput, ActionTypeConstant.ACTION_TYPE_ADD);
			List<ExecutionVO> returnVO = executionController.addExecution(executionInput);
			response.setStatus(StatusConstant.RESPONSE_SUCCESS);
			response.setData(returnVO);
		} catch (CommonException e) {
			response.setStatus(StatusConstant.RESPONSE_FAIL);
			response.setErrorMsg(e.getErrorMsg());
		}
		return response;
    }

}
