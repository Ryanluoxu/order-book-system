package io.ryanluoxu.orderbook.orderinfo;

import org.springframework.stereotype.Repository;

import io.ryanluoxu.orderbook.bean.orderinfo.OrderInfo;
import io.ryanluoxu.orderbook.common.GenericDaoImpl;

@Repository
public class OrderInfoDaoImpl extends GenericDaoImpl<OrderInfo, Long> implements OrderInfoDao {
	

}