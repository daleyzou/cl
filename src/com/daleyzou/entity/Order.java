package com.daleyzou.entity;

import java.util.Date;

/**
 * 1.����ʵ�������
 * @author 11543
 *
 */

public class Order {
	private int o_id;				//�������
	private String u_name;			//�µ����û���
	private int o_shopid;			//��Ʒid��Ȼ���ڸ�����Ʒid��ѯ��Ʒ�����ƺ�ͼƬ·��
	private double o_shopprice;		//�۸�
	private int o_nums;				//��Ʒ����
	private Date o_time;			//�µ�ʱ��
	private String o_name;			//�ջ�������
	private String o_adress;		//�ջ���ַ
	private String o_phone;			//��ϵ�绰
	private int o_youbian;			//�ʱ�
	private String o_memo;			//��ע
	private int o_status;			//����״̬
	private String si_name;			//��Ʒ����
	private String si_photo;		//��ƷͼƬ
	
	
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public int getO_shopid() {
		return o_shopid;
	}
	public void setO_shopid(int o_shopid) {
		this.o_shopid = o_shopid;
	}
	public double getO_shopprice() {
		return o_shopprice;
	}
	public void setO_shopprice(double o_shopprice) {
		this.o_shopprice = o_shopprice;
	}
	public int getO_nums() {
		return o_nums;
	}
	public void setO_nums(int o_nums) {
		this.o_nums = o_nums;
	}
	public Date getO_time() {
		return o_time;
	}
	public void setO_time(Date o_time) {
		this.o_time = o_time;
	}
	public String getO_name() {
		return o_name;
	}
	public void setO_name(String o_name) {
		this.o_name = o_name;
	}
	public String getO_adress() {
		return o_adress;
	}
	public void setO_adress(String o_adress) {
		this.o_adress = o_adress;
	}
	public String getO_phone() {
		return o_phone;
	}
	public void setO_phone(String o_phone) {
		this.o_phone = o_phone;
	}
	public int getO_youbian() {
		return o_youbian;
	}
	public void setO_youbian(int o_youbian) {
		this.o_youbian = o_youbian;
	}
	public String getO_memo() {
		return o_memo;
	}
	public void setO_memo(String o_memo) {
		this.o_memo = o_memo;
	}
	public int getO_status() {
		return o_status;
	}
	public void setO_status(int o_status) {
		this.o_status = o_status;
	}
	public String getSi_name() {
		return si_name;
	}
	public void setSi_name(String si_name) {
		this.si_name = si_name;
	}
	public String getSi_photo() {
		return si_photo;
	}
	public void setSi_photo(String si_photo) {
		this.si_photo = si_photo;
	}
	
	
	
	
	
}
