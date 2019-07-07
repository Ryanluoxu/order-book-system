package io.ryanluoxu.orderbook.execution;

import org.springframework.stereotype.Repository;

import io.ryanluoxu.orderbook.bean.execution.Execution;
import io.ryanluoxu.orderbook.common.GenericDaoImpl;

@Repository
public class ExecutionDaoImpl extends GenericDaoImpl<Execution, Long> implements ExecutionDao {
	

}