package manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * ɾ����Ʒ(ͨ��id)
 */
public class delete {

	public static void del(int id){
		Connection con=null;
		PreparedStatement stm=null;
		int rs;
		con=DBConnection.getConnection();
		try {
			stm=con.prepareStatement("delete from t_shopinfos where si_id=?");
			stm.setInt(1, id);
			rs=stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * ɾ��һ��ͼ
	 */
	public static void deltu(int id){
		Connection con=null;
		PreparedStatement stm=null;
		con=DBConnection.getConnection();
		try {
			stm=con.prepareStatement("delete from t_picture where p_id=?");
			stm.setInt(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * ɾ������ͼ
	 */
	public static void delalltu(int sid){
		Connection con=null;
		PreparedStatement stm=null;
		con=DBConnection.getConnection();
		try {
			stm=con.prepareStatement("delete from t_picture where p_sid=?");
			stm.setInt(1, sid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
