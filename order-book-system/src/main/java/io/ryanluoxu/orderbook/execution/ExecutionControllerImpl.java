package io.ryanluoxu.orderbook.execution;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;

import io.ryanluoxu.orderbook.bean.execution.Execution;
import io.ryanluoxu.orderbook.bean.execution.ExecutionInput;
import io.ryanluoxu.orderbook.bean.execution.ExecutionVO;
import io.ryanluoxu.orderbook.common.GenericControllerImpl;
import io.ryanluoxu.orderbook.common.exception.CommonException;

@Controller
public class ExecutionControllerImpl extends GenericControllerImpl<Execution, ExecutionVO, ExecutionInput> implements ExecutionController {

	@Override
	public List<ExecutionVO> findAll() {
		List<ExecutionVO> executionVOs = new ArrayList<>();
		List<Execution> executions = executionService.findAll();
		for (Execution execution : executions) {
			executionVOs.add(convertToVO(execution));
		}
		return executionVOs;
	}

	@Override
	public ExecutionVO add(ExecutionInput input) {
		return null;
	}
	
	@Override
	public List<ExecutionVO> addExecution(ExecutionInput input) {
		List<ExecutionVO> executionVOs = new ArrayList<>();
		Long orderBookId = input.getOrderBookId();
		BigDecimal price = input.getPrice();
		for (Entry<Long, BigDecimal> entry : input.getOrderInfoIdToQuantityMap().entrySet()) {
			Execution execution = new Execution();
			execution.setOrderBookId(orderBookId);
			execution.setPrice(price);
			execution.setOrderInfoId(entry.getKey());
			execution.setQuantity(entry.getValue());
			executionVOs.add(convertToVO(executionService.add(execution)));
		}
		return executionVOs;
	}

	@Override
	public ExecutionVO update(ExecutionInput input) {return null;}

	@Override
	public ExecutionVO delete(ExecutionInput input) {return null;}

	@Override
	public void validate(ExecutionInput input, String actionType) throws CommonException {
		executionValidator.validateMandatoryFields(input, actionType);
		executionValidator.validateInputValue(input, actionType);
	}


}
