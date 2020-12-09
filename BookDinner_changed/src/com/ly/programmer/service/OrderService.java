package com.ly.programmer.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ly.programmer.entity.Order;
import com.ly.programmer.entity.OrderItem;

/*
*订单信息service
*@author LiYing
*@version 2020年11月5日 下午1:17:02
*
*/
@Service
public interface OrderService {
	public int add(Order order);
	public int addItem(OrderItem orderItem);
	public int edit(Order Order);
	public List<Order> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int deleteItems(Long id);
	public int delete(Long id);
}
