package com.daleyzou.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daleyzou.factory.BeanFactory;
import com.daleyzou.service.IOrderService;
import com.daleyzou.service.IUserInfoService;
import com.daleyzou.utils.WebUtils;

/**
 * ��Ŀ�е�Servlet��ϣ�����е�servlet���̳д���
 * @author 11543
 *
 */
public abstract class BaseServlet extends HttpServlet {
	
	//һ�δ������е�service
	protected IUserInfoService userInfoService = 
			BeanFactory.getInstance("userInfoService", IUserInfoService.class);
	protected IOrderService orderService = 
			BeanFactory.getInstance("orderService", IOrderService.class);
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		//��������ֵ
		Object returnValue = null;
		
		//��Լ�����������͵�ֵ�������Ӧservlet�еķ��������ơ�
		String methodName = request.getParameter("method");
		
		try {
			//1.��ȡ��ǰ����ֽ���
			Class clazz = this.getClass();
			//2.��ȡ��ǰִ�еķ�����Method����
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			//3.ִ�з���
			returnValue = method.invoke(this, request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = "/error/error.jsp";
		}
		
		//��ת
		WebUtils.goTo(request, response, returnValue);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
