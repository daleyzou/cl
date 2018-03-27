package com.daleyzou.service;

import java.util.List;

import com.daleyzou.entity.UserInfo;
import com.daleyzou.utils.PageBean;

public interface IUserInfoService {
	/**
	 * ��ҳ��ʾ�����û���Ϣ
	 */
	void getAll(PageBean<UserInfo> pb);
	
	
	/**
	 * �����û�����ѯ�û���Ϣ
	 */
	List<UserInfo> getAll(String u_name);
	
	/**
	 * ����id����������ѯ�û���Ϣ
	 */
	UserInfo findById(int u_id);
	
	/**
	 * �����û���Ϣ
	 */
	void update(UserInfo userInfo);
	
	/**
	 * ɾ��ָ���û�
	 */
	void delete(int u_id);
	
	void updateAdministrator(int id, String pwd);
}
