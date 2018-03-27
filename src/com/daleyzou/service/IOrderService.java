package com.daleyzou.service;

import java.util.List;

import com.daleyzou.entity.Order;
import com.daleyzou.entity.Shop;
import com.daleyzou.utils.PageBean;

/*
 * ����service�ӿ����
 */
public interface IOrderService {

	/**
	 * ��ҳ��ʾ���ж�����Ϣ
	 */
	void getAll(PageBean<Order> pb);
	
	
	
	/**
	 * �����û�id��ѯ����
	 */
	List<Order> findByU_id(int u_id);
	
	/**
	 * ���ݶ���id����������ѯ������Ϣ
	 */
	Order findById(int o_id);
	
	/**
	 * ����id�����������¶���״̬
	 */
	void update(Order order);
	
	/**
	 * ���ݶ�����ɾ��ָ������
	 */
	void delete(int o_id);
	
	/**
	 * ������Ʒid��ѯ��Ʒ�����ƺ�ͼƬ·��
	 */
	Shop getShopInfo(int o_shopid);
}
