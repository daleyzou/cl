package com.daleyzou.dao;

import java.util.List;

import com.daleyzou.entity.UserInfo;
import com.daleyzou.utils.PageBean;

/**
 * �û���Ϣdao��ӿ����
 * @author 11543
 *
 */
public interface IUserInfoDao {
	/**
	 * ��ҳ��ʾ�����û���Ϣ
	 */
	void getAll(PageBean<UserInfo> pb);
	
	/**
	 * �õ����û���Ϣ����
	 */
	int getTotalCount(PageBean<UserInfo> pb);
	
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
	
	/**
	 * ���Ĺ���Ա��Ϣ
	 * @param id
	 * @param pwd
	 */
	void updateAdministrator(int id, String pwd);
}
