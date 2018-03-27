package com.daleyzou.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.daleyzou.dao.IOrderDao;
import com.daleyzou.entity.Order;
import com.daleyzou.entity.Shop;
import com.daleyzou.utils.Condition;
import com.daleyzou.utils.JdbcUtils;
import com.daleyzou.utils.PageBean;

public class OrderDao implements IOrderDao {

	@Override
	public void getAll(PageBean<Order> pb) {
		
		//��ȡ��������
		Condition condition = pb.getCondition();
		//����֮�µ�������
		String userName = condition.getU_name();
		StringBuffer sb = new StringBuffer();
		sb.append(" select");
		sb.append("      o.`o_id`,");
		sb.append("      u.`u_name`,");
		sb.append("      o.`o_shopid`,");
		sb.append("      o.`o_shopprice`,");
		sb.append("      o.`o_nums`,");
		sb.append("      o.`o_time`,");
		sb.append("      o.`o_name`,");
		sb.append("      o.`o_adress`,");
		sb.append("      o.`o_phone`,");
		sb.append("      o.`o_youbian`,");
		sb.append("      o.`o_memo`,");
		sb.append("      o.`o_status`");
		sb.append("  FROM");
		sb.append("      t_order o,");
		sb.append("      t_users u");
		sb.append(" WHERE");
		sb.append("      1=1");
		sb.append("  AND");
		sb.append("      o.`o_userid`=u.`u_id`");
		
		List<Object> list = new ArrayList<Object>();//�洢��ѯ������Ӧ��ֵ
		/************ƴ�Ӳ�ѯ����******************/
		
		//��Ʒ��������
		if (userName != null && !"".equals(userName.trim())){
			sb.append("  AND u.`u_name` LIKE ?");
			userName = "%"+userName+"%";
			list.add(userName);	//��װ����ֵ
		}
		/**************��ҳ����*************************/
		sb.append(" LIMIT ?,?");
		/**�жϣ���ǰҳ<1�����õ�ǰҳΪ1����ǰҳ>��ҳ�������õ�ǰҳΪ��ҳ��**/
		//�Ȳ�ѯ�ܼ�¼��
		int totalCount = getTotalCount(pb);       //******totalCount=0�Ļ���û�б�Ҫ������ȥ��*******
		pb.setTotalCount(totalCount);
		if (pb.getCurrentPage() < 1){
			pb.setCurrentPage(1);
		}else if (pb.getCurrentPage() > pb.getTotalPage()) {
			pb.setCurrentPage(pb.getTotalPage());
		}
		int index = (pb.getCurrentPage() - 1) * pb.getPageCount();
		int count = pb.getPageCount();
		list.add(index);	   	//��װ����ֵ--��ʼ��
		list.add(count);       	//��װ����ֵ--��ѯ���ص���
		
		try {
			List<Order> pageData = JdbcUtils.getQueryRunner().query(sb.toString(),
					new BeanListHandler<Order>(Order.class), list.toArray());
			//�Ѳ鵽���������õ���ҳ������
			pb.setPageData(pageData);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}


	}

	@Override
	public int getTotalCount(PageBean<Order> pb) {
		//��ȡ��������
		Condition condition = pb.getCondition();
		//����֮�µ�������
		String userName = condition.getU_name();
		StringBuffer sb = new StringBuffer();
		sb.append(" select");
		sb.append("      count(*)");
		sb.append("  FROM");
		sb.append("      t_order o,");
		sb.append("      t_users u");
		sb.append(" WHERE");
		sb.append("      1=1");
		sb.append("  AND");
		sb.append("      o.`o_userid`=u.`u_id`");
		
		List<Object> list = new ArrayList<Object>();//�洢��ѯ������Ӧ��ֵ
		/************ƴ�Ӳ�ѯ����******************/
		
		//��Ʒ��������
		if (userName != null && !"".equals(userName.trim())){
			sb.append("  AND u.`u_name` LIKE ?");
			userName = "%"+userName+"%";
			list.add(userName);	//��װ����ֵ
		}
				
		try {
			Long num = JdbcUtils.getQueryRunner().query(sb.toString(), new ScalarHandler<Long>(), list.toArray());
			return num.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<Order> findByU_name(String u_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(int o_id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select");
		sb.append("      o.`o_id`,");
		sb.append("      u.`u_name`,");
		sb.append("      o.`o_shopid`,");
		sb.append("      o.`o_shopprice`,");
		sb.append("      o.`o_nums`,");
		sb.append("      o.`o_time`,");
		sb.append("      o.`o_name`,");
		sb.append("      o.`o_adress`,");
		sb.append("      o.`o_phone`,");
		sb.append("      o.`o_youbian`,");
		sb.append("      o.`o_memo`,");
		sb.append("      o.`o_status`");
		sb.append("  FROM");
		sb.append("      t_order o,");
		sb.append("      t_users u");
		sb.append(" WHERE");
		sb.append("      1=1");
		sb.append("  AND");
		sb.append("      o.`o_userid`=u.`u_id`");
		sb.append("	 AND");
		sb.append("		 o.`o_id`=?");
		try {
			return JdbcUtils.getQueryRunner().query(sb.toString(), new BeanHandler<Order>(Order.class), o_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Order order) {
		String sql = "UPDATE t_order SET o_status=? WHERE o_id=?;";
		try {
			JdbcUtils.getQueryRunner().update(sql, order.getO_status(), order.getO_id());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delete(int o_id) {
		String sql = "DELETE FROM t_order WHERE o_id=?;";
		try {
			JdbcUtils.getQueryRunner().update(sql, o_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public Shop getShopInfo(int o_shopid) {
		String sql = "select t_shopinfos.`si_name`,t_shopinfos.`si_photo` from t_shopinfos where si_id=?;";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Shop>(Shop.class), o_shopid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findByU_id(int u_id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select");
		sb.append("      o.`o_id`,");
		sb.append("      u.`u_name`,");
		sb.append("      o.`o_shopid`,");
		sb.append("      o.`o_shopprice`,");
		sb.append("      o.`o_nums`,");
		sb.append("      o.`o_time`,");
		sb.append("      o.`o_name`,");
		sb.append("      o.`o_adress`,");
		sb.append("      o.`o_phone`,");
		sb.append("      o.`o_youbian`,");
		sb.append("      o.`o_memo`,");
		sb.append("      o.`o_status`,");
		sb.append("      info.`si_name`,");
		sb.append("      info.`si_photo`");
		sb.append("  FROM");
		sb.append("      t_order o,");
		sb.append("      t_users u,");
		sb.append("      t_shopinfos info");
		sb.append(" WHERE");
		sb.append("      1=1");
		sb.append("  AND");
		sb.append("      o.`o_userid`=u.`u_id`");
		sb.append("	 AND");
		sb.append("		 o.`o_userid`=?");
		sb.append("	 AND");
		sb.append("		 info.`si_id`=o.`o_shopid`");
		try {
			return JdbcUtils.getQueryRunner().query(sb.toString(), new BeanListHandler<Order>(Order.class), u_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	

}
