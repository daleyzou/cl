package com.daleyzou.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daleyzou.entity.Order;
import com.daleyzou.entity.Shop;
import com.daleyzou.entity.UserInfo;
import com.daleyzou.utils.Condition;
import com.daleyzou.utils.PageBean;

public class OrderServlet extends BaseServlet {

	
	public Object orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.1��ѯ���еġ�������Ϣ��������
		PageBean<Order> pb = new PageBean<Order>();
		//��������
		Condition condition = new Condition();
		//��һ�η��ʣ����õ�ǰҳΪ1
		String curPage = request.getParameter("currentPage");
		//�ж�
		if (curPage == null || "".equals(curPage.trim())){
			//��һ�η��ʣ����õ�ǰҳΪ1
			pb.setCurrentPage(1);
		}else {
			//�����õ�ǰҳ������
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		//������������pageBean��
		pb.setCondition(condition);
		//-----��ҳ��ѯ
		orderService.getAll(pb);
		//�����ѯ���pb����
		request.setAttribute("pb", pb);
		//1.4��ת
		return request.getRequestDispatcher("/admin/daleyzou/orderList.jsp");
	}
	
	public Object search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.�õ�������û���
			String u_name = request.getParameter("searchOrder");
			if (u_name == null || "".equals(u_name)){
				return request.getRequestDispatcher("/admin/daleyzou/userInfoList.jsp");
			}
			
			//2.�õ�����Աѡ��Ĳ�ѯ��ʽ
			String findMethod = request.getParameter("mySelected");
			System.out.println(findMethod+":"+u_name);
			if ("searchByOrderName".equals(findMethod)){
				PageBean<Order> pb = new PageBean<Order>();
				pb.setCurrentPage(1);
				Condition condition = new Condition();
				condition.setU_name(u_name);
				pb.setCondition(condition);
				//-----��ҳ��ѯ
				orderService.getAll(pb);
				//�����ѯ���pb����
				request.setAttribute("pb", pb);
			}else if ("searchByOrderId".equals(findMethod)) {
				Order order = orderService.findById(Integer.parseInt(u_name));
				
				List<Order> list = new ArrayList<Order>();
				list.add(order);
				PageBean<Order> pb = new PageBean<Order>();
				if (order != null){
					pb.setPageData(list);
				}else {
					pb.setPageData(null);
				}
				pb.setCurrentPage(1);
				pb.setTotalPage(1);
				
				//�����ѯ���pb����
				request.setAttribute("pb", pb);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return request.getRequestDispatcher("/admin/daleyzou/orderList.jsp");
	}
	
	
	/**
	 * 
	 *��̨״̬����
	 */
	public Object deliver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = new Order();
		int o_id = Integer.parseInt(request.getParameter("o_id"));
		int status = Integer.parseInt(request.getParameter("nowStatus"));
		order.setO_status(status+1);
		order.setO_id(o_id);
		orderService.update(order);
		
		return request.getRequestDispatcher("/order?method=orderList");
	}
	
	
	
	/**
	 * 
	 *ǰ̨״̬����
	 */
	public Object deliver1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = new Order();
		int o_id = Integer.parseInt(request.getParameter("o_id"));
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		int status = Integer.parseInt(request.getParameter("nowStatus"));
		order.setO_status(status+1);
		order.setO_id(o_id);
		orderService.update(order);
		
		return request.getRequestDispatcher("/order?method=customOrderDetail&u_id="+u_id);
	}
	
	
	/**
	 * 
	 * ��̨ɾ������
	 */
	public Object delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ����id
		String o_id = request.getParameter("o_id");
		//2.����service����
		orderService.delete(Integer.parseInt(o_id));
		
		return request.getRequestDispatcher("/order?method=orderList");
	}
	
	/**
	 * 
	 * ǰ̨ɾ������
	 */
	public Object delete1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ����id
		String o_id = request.getParameter("o_id");
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		//2.����service����
		orderService.delete(Integer.parseInt(o_id));

		//��ת
		return request.getRequestDispatcher("/order?method=customOrderDetail&u_id="+u_id);
	}
	
	
	public Object orderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ����id
		String o_id = request.getParameter("o_id");
		//2.����service����
		Order order = orderService.findById(Integer.parseInt(o_id));
		
		//ͨ�������е���Ʒid��ѯ��Ʒ�����ƺ�ͼƬ·��
		Shop shop = orderService.getShopInfo(order.getO_shopid());
		
		request.setAttribute("order", order);
		request.setAttribute("shop", shop);
		
		return request.getRequestDispatcher("/admin/daleyzou/orderDetail.jsp");
	}
	
	public Object customOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ����id
		String u_id = request.getParameter("u_id");
		//2.����service����
		List<Order> orderList = orderService.findByU_id(Integer.parseInt(u_id));
		request.setAttribute("orderList", orderList);
		request.setAttribute("userId", u_id);
		return request.getRequestDispatcher("/user/order.jsp");
		
	}
	
	
	public Object dd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return null;
	}



	

}
