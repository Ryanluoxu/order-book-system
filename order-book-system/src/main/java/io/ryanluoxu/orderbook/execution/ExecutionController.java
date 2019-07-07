package io.ryanluoxu.orderbook.execution;

import java.util.List;

import io.ryanluoxu.orderbook.bean.execution.Execution;
import io.ryanluoxu.orderbook.bean.execution.ExecutionInput;
import io.ryanluoxu.orderbook.bean.execution.ExecutionVO;
import io.ryanluoxu.orderbook.common.GenericController;

public interface ExecutionController extends GenericController<Execution, ExecutionVO, ExecutionInput> {
	List<ExecutionVO> addExecution(ExecutionInput input);
}
