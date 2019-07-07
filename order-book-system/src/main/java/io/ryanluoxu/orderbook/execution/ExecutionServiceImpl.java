package io.ryanluoxu.orderbook.execution;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ryanluoxu.orderbook.bean.execution.Execution;

@Service
@Transactional
public class ExecutionServiceImpl implements ExecutionService {

	@Autowired
	private ExecutionDao executionDao;

	@Override
	public Execution add(Execution execution) {
		execution.setExecutionDate(new Date());
		return executionDao.add(execution);
	}
	
	@Override
	public Execution deleteById(Long executionId) {
		return executionDao.deleteById(executionId);
	}

	@Override
	public Execution update(Execution execution) {
		return executionDao.update(execution);
	}

	@Override
	public List<Execution> findAll() {
		return executionDao.findAll();
	}

	@Override
	public Execution getById(Long executionId) {
		return executionDao.getById(executionId);
	}

}
