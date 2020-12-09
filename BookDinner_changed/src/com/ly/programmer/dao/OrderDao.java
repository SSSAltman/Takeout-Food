package com.ly.programmer.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.ly.programmer.entity.Order;
import com.ly.programmer.entity.OrderItem;

/*
*订单信息dao
*@author LiYing
*@version 2020年11月5日 下午1:17:58
*
*/
@Repository
public interface OrderDao {
	public int add(Order Order);
	public int addItem(OrderItem orderItem);
	public int edit(Order Order);
	public List<Order> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int deleteItems(Long id);
	public int delete(Long id);
}
