package com.daleyzou.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daleyzou.entity.UserInfo;
import com.daleyzou.utils.PageBean;

import users.adminEntity;

public class UserInfoServlet extends BaseServlet {

	/**
	 * ��ʾ�û���ϸ��Ϣ�б�
	 */
	public Object userInfoList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.1��ѯ���еġ��û���Ϣ��������
		PageBean<UserInfo> pb = new PageBean<UserInfo>();
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
		pb.setPageCount(12);
		//-----��ҳ��ѯ
		userInfoService.getAll(pb);
		//�����ѯ���pb����
		request.setAttribute("pb", pb);
		//1.4��ת
		return request.getRequestDispatcher("/admin/daleyzou/userInfoList.jsp");
		
	}
	
	
	
	public Object delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.�õ�Ҫɾ����u_id
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		
		//2.����serviceִ��ɾ��
		userInfoService.delete(u_id);
		
		return request.getRequestDispatcher("/userInfo?method=userInfoList");
		
	}
	
	
	public Object search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����search����������");
		try {
			//1.�õ�������û���
			String name = request.getParameter("searchUser");
			if (name == null || "".equals(name)){
				return request.getRequestDispatcher("/admin/daleyzou/userInfoList.jsp");
			}
			//2.����serviceִ�в���
			List<UserInfo> list = userInfoService.getAll(name);
			PageBean<UserInfo> pb = new PageBean<UserInfo>();
			pb.setPageData(list);
			pb.setCurrentPage(1);
			pb.setTotalPage(1);
			
			//�����ѯ���pb����
			request.setAttribute("pb", pb);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return request.getRequestDispatcher("/admin/daleyzou/userInfoList.jsp");
	}
	
	/**
	 * 
	 * ��ʾ�û��ջ���ַ
	 */
	public Object userInfoDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.�õ�Ҫ��ʾ��ϸ��Ϣ��u_id
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		
		//2.����serviceִ�в�ѯ
		UserInfo userInfo = userInfoService.findById(u_id);
		//3.�����ѯ������Ϣ
		request.setAttribute("userInfo", userInfo);
		//��ת
		return request.getRequestDispatcher("/admin/daleyzou/address.jsp");
		
	}
	
	/**
	 * 
	 * ǰ̨�û��鿴�Լ�����ϸ��Ϣ
	 */
	public Object customDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.�õ�Ҫ��ʾ��ϸ��Ϣ��u_id����ǰ�û�ID��
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		
		//2.����serviceִ�в�ѯ
		UserInfo loginUserInfo = userInfoService.findById(u_id);
		//3.�����ѯ������Ϣ
		HttpSession session = request.getSession();
		session.setAttribute("loginUserInfo", loginUserInfo);
		//��ת
		return request.getRequestDispatcher("/user/info.jsp");
		
	}
	
	/**
	 * 
	 * ǰ̨�û������Լ�����ϸ��Ϣ
	 */
	public Object update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.�õ�Ҫ������ϸ��Ϣ��u_id����ǰ�û�ID��
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		String u_password = request.getParameter("password");
		HttpSession session = request.getSession(false);
		UserInfo userInfo = (UserInfo)session.getAttribute("loginUserInfo");
		String oldPassword = userInfo.getU_password();	
		//�ж����������Ƿ���ȷ
		if (!u_password.equals(oldPassword)){
			request.setAttribute("message", "�������");
			return request.getRequestDispatcher("/user/change.jsp");
		}
		//�õ��û����벢��װ		
		String ui_email = request.getParameter("email");	
		String ui_address = request.getParameter("adress");	
		String ui_phone = request.getParameter("phone");	
		String u_newpassword = request.getParameter("newpassword");  
		UserInfo newUserInfo = new UserInfo();
		newUserInfo.setU_id(u_id);
		newUserInfo.setUi_email(ui_email);
		newUserInfo.setUi_address(ui_address);
		newUserInfo.setUi_phone(ui_phone);
		newUserInfo.setU_password(u_newpassword);
		
		//.����serviceִ�и���
		userInfoService.update(newUserInfo);
		//System.out.println("ȷ���͸�����Ŷ��");
		//��ת
		return request.getRequestDispatcher("/user/info.jsp");
		
	}
	
	/**
	 * ���Ĺ���Ա��Ϣ
	 */
	public Object administrator(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//1.�õ�Ҫ���ĵ�����
		String pwd1 = request.getParameter("psw1");
		String pwd2 = request.getParameter("psw2");
		if (pwd1 == null || "".equals(pwd1)){
			request.setAttribute("message", "���벻��Ϊ�գ�");
			return request.getRequestDispatcher("/admin/daleyzou/administrator.jsp");
		}
		if (!pwd1.equals(pwd2)){
			request.setAttribute("message", "�����������벻��ȣ�");
			return request.getRequestDispatcher("/admin/daleyzou/administrator.jsp");
		}
		//2.��ȡsession���õ���ǰ��¼����Ա��id
		int id = ((adminEntity)request.getSession(false).getAttribute("admin")).getId();
		//3.����service���Ĺ���Ա��Ϣ
		userInfoService.updateAdministrator(id, pwd1);
		
		request.setAttribute("message", "���Ĺ���Ա��Ϣ�ɹ���������");
		//4.��ת
		return request.getRequestDispatcher("/admin/right.jsp");
		
	}
	
	
	/**
	 * �����û��˳�
	 */
	public Object quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(false).invalidate();; 
		//4.��ת
		return request.getRequestDispatcher("/index.jsp");
		
	}

	

}
