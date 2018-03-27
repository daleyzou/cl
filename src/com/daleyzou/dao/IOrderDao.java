package com.daleyzou.dao;

import java.util.List;

import com.daleyzou.entity.Order;
import com.daleyzou.entity.Shop;
import com.daleyzou.utils.PageBean;

/**
 * ������Ϣdao�ӿ����
 * @author 11543
 *
 */
public interface IOrderDao {
	
	/**
	 * ��ҳ��ʾ���ж�����Ϣ
	 */
	void getAll(PageBean<Order> pb);
	
	/**
	 * �õ��ܶ�����Ϣ����
	 */
	int getTotalCount(PageBean<Order> pb);
	
	/**
	 * �����µ���������ѯ����
	 */
	List<Order> findByU_name(String u_name);
	
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
