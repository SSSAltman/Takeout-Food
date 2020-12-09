package com.ly.programmer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.programmer.dao.OrderDao;
import com.ly.programmer.entity.Order;
import com.ly.programmer.entity.OrderItem;
import com.ly.programmer.service.OrderService;

/*
*订单信息实现类
*@author LiYing
*@version 2020年11月5日 下午1:19:08
*
*/
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public int add(Order Order) {
		// TODO Auto-generated method stub
		return orderDao.add(Order);
	}

	@Override
	public int edit(Order Order) {
		// TODO Auto-generated method stub
		return orderDao.edit(Order);
	}

	@Override
	public List<Order> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return orderDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return orderDao.getTotal(queryMap);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return orderDao.delete(id);
	}

	@Override
	public int addItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return orderDao.addItem(orderItem);
	}

	@Override
	public int deleteItems(Long id) {
		// TODO Auto-generated method stub
		return orderDao.deleteItems(id);
	}

	
	
	

}
