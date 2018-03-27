package com.daleyzou.utils;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ��װ���õĲ���
 * @author 11543
 *
 */
public class JdbcUtils {
	
	//��ʼ�� ���ӳ�
	private static DataSource dataSource;
	
	static {
		dataSource = new ComboPooledDataSource();
	}
	
	/**
	 * ��������Դ����
	 * @return
	 */
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	/**
	 * ����DbUtils���ù��������
	 */
	public static QueryRunner getQueryRunner(){
		return new QueryRunner(dataSource);
	}
}







