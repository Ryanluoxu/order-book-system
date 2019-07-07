package io.ryanluoxu.orderbook.common;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import io.ryanluoxu.orderbook.common.util.ClassUtil;
import io.ryanluoxu.orderbook.execution.ExecutionService;
import io.ryanluoxu.orderbook.execution.ExecutionValidator;
import io.ryanluoxu.orderbook.financialinstrument.FinancialInstrumentService;
import io.ryanluoxu.orderbook.financialinstrument.FinancialInstrumentValidator;
import io.ryanluoxu.orderbook.orderbook.OrderBookService;
import io.ryanluoxu.orderbook.orderbook.OrderBookValidator;
import io.ryanluoxu.orderbook.orderinfo.OrderInfoService;
import io.ryanluoxu.orderbook.orderinfo.OrderInfoValidator;

public abstract class GenericControllerImpl<T, TVO, TInput> implements GenericController<T, TVO, TInput> {

	@SuppressWarnings("rawtypes")
	protected Class tClass;
	@SuppressWarnings("rawtypes")
	protected Class tVOClass;
	@SuppressWarnings("rawtypes")
	protected Class tInputClass;

	@Autowired
	protected FinancialInstrumentService financialInstrumentService;
	@Autowired
	protected FinancialInstrumentValidator financialInstrumentValidator;
	
	@Autowired
	protected OrderBookService orderBookService;
	@Autowired
	protected OrderBookValidator orderBookValidator;
	
	@Autowired
	protected OrderInfoService orderInfoService;
	@Autowired
	protected OrderInfoValidator orderInfoValidator;
	
	@Autowired
	protected ExecutionService executionService;
	@Autowired
	protected ExecutionValidator executionValidator;


	public GenericControllerImpl() {
		this.tClass = ClassUtil.getTypeArguments(GenericControllerImpl.class, this.getClass()).get(0);
		this.tVOClass = ClassUtil.getTypeArguments(GenericControllerImpl.class, this.getClass()).get(1);
		this.tInputClass = ClassUtil.getTypeArguments(GenericControllerImpl.class, this.getClass()).get(2);
	}

	@SuppressWarnings("unchecked")
	protected TVO convertToVO(T t) {
		TVO tVO = null;
		try {
			tVO = (TVO) tVOClass.newInstance();
			BeanUtils.copyProperties(t, tVO);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return tVO;
	}

	@SuppressWarnings("unchecked")
	protected T convertToBean(TInput input) {
		T t = null;
		try {
			t = (T) tClass.newInstance();
			BeanUtils.copyProperties(input, t);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	protected T convertVOToBean(TVO tVO) {
		T t = null;
		try {
			t = (T) tClass.newInstance();
			BeanUtils.copyProperties(tVO, t);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	protected TInput convertToInput(T t) {
		TInput tInput = null;
		try {
			tInput = (TInput) tInputClass.newInstance();
			BeanUtils.copyProperties(t, tInput);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return tInput;
	}
	
	protected TVO populateVO(T t) {
		return null;
	}

}
