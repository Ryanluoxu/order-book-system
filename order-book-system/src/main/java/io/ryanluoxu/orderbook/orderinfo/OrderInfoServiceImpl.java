package io.ryanluoxu.orderbook.orderinfo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfo;

@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {

	@Autowired
	private OrderInfoDao orderInfoDao;

	@Override
	public OrderInfo add(OrderInfo orderInfo) {
		orderInfo.setEntryDate(new Date());
		return orderInfoDao.add(orderInfo);
	}
	
	@Override
	public OrderInfo deleteById(Long orderInfoId) {
		return orderInfoDao.deleteById(orderInfoId);
	}

	@Override
	public OrderInfo update(OrderInfo orderInfo) {
		return orderInfoDao.update(orderInfo);
	}

	@Override
	public List<OrderInfo> findAll() {
		return orderInfoDao.findAll();
	}

	@Override
	public OrderInfo getById(Long orderInfoId) {
		return orderInfoDao.getById(orderInfoId);
	}

}
