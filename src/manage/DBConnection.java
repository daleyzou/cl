package manage;

import java.sql.*;

public class DBConnection {
	static String NAME="root";
	static String KEY="1234";
	static Connection con=null;
	 /**���ݿ⽨������**/
	public static Connection getConnection(){
		if(con==null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("��������ʧ�ܣ�");
			}
			try {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/clothshop",NAME,KEY);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("���ݿ�����ʧ�ܣ�");
			}
		}
		return con;
	}
	
	/** �ر����ݿ�Ĳ��� */
    public static void closeConnection() {
     	if(con!=null)
			try {
				con.close();
				con=null;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�con����ʧ�ܣ�");
			}
    }
}
