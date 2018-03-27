package com.daleyzou.utils;

import java.util.List;

/**
 * ��װ��ҳ����
 * @author 11543
 *
 */
public class PageBean<T> {

	private int currentPage;	//��ǰҳ
	private int pageCount = 8;	//ÿҳ��ʾ�ļ�¼����Ĭ��һҳ8��ͼƬ
	private int totalCount;		//�ܼ�¼��
	private int totalPage;		//��ҳ��
	private List<T> pageData;	//ÿҳ������
	private Condition condition;//��װ��ѯ������
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		//��ҳ�� = �ܼ�¼ / ÿҳ��ʾ�ļ�¼�� ��+1��
		if (totalCount % pageCount == 0){
			totalPage = totalCount / pageCount;
		}else {
			totalPage = totalCount / pageCount + 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
}
