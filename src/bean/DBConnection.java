package bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
	static Connection con =null;
	public static Connection getConnection(){
		if(con==null){
			try{
				Class.forName("com.mysql.jdbc.Driver");
			}catch(Exception e){
				e.printStackTrace();
				System.out.print("����mysql���ݿ�ʧ�ܣ�");
			}
			try
			{
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/clothshop","root","1234");
			}catch(Exception e){
				e.printStackTrace();
				System.out.print("��ȡ���ݿ�����ʧ�ܣ�");
			}
		}
		return con;
	}
	public static void closeConnection(){
		if(con!=null){
			try{
			   con.close();
			   con=null;
			}catch(SQLException e){
				e.printStackTrace();
				System.out.print("�ر�con����ʧ��");
			}
		}
		
	}

}

