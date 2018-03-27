package manage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class find {
	
	
	
	/*
	 * ��ͨ�Ĳ��� ��ͨ�����֣�
	 */
	public static List<shopinfos> find_by_name(String name){
		ResultSet rs=null;
		Connection con = null;
        PreparedStatement stm=null;
        List<shopinfos> shopInfo = new ArrayList<shopinfos>();
		try {
			con = DBConnection.getConnection();
			stm = con.prepareStatement("select * from t_shopinfos where si_name like ?");
			if(name.equals("")){
				name="lol";
			}else{
				name="%"+name+"%";
			}
			stm.setString(1, name);
			rs=stm.executeQuery();
			//Statement stm=con.createStatement();
			//rs=stm.executeQuery("select * from t_shopinfos where si_name like '%����%'");
			while(rs.next()){
				shopinfos sinfo = new shopinfos();
				// TODO ��Ʒ��Ϣ��ȡ
				sinfo.setSi_id(rs.getInt("si_id"));
				sinfo.setSi_name(rs.getString("si_name"));
				sinfo.setSi_typeid(rs.getInt("si_typeid"));
				sinfo.setSi_typename(rs.getString("si_typename"));
				sinfo.setSi_time(rs.getDate("si_time"));
				sinfo.setSi_photo(rs.getString("si_photo"));
				sinfo.setSi_bigphoto(rs.getString("si_bigphoto"));
				sinfo.setSi_price(rs.getDouble("si_price"));
				sinfo.setSi_allnum(rs.getInt("si_allnum"));
				sinfo.setSi_salenum(rs.getInt("si_salenum"));
				sinfo.setSi_content(rs.getString("si_content"));
				sinfo.setSi_remark(rs.getString("si_remark"));
				sinfo.setSi_count(rs.getDouble("si_count"));
				shopInfo.add(sinfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection();
		}
		return shopInfo;
	}
	
	
	/*
	 * ͨ��si_id ������Ʒ��Ϣ
	 */
	public static shopinfos find_by_id(int id){
		ResultSet rs=null;
		Connection con = null;
        PreparedStatement stm=null;	
        shopinfos sinfo = new shopinfos();
		try {
			con = DBConnection.getConnection();
			stm = con.prepareStatement("select * from t_shopinfos where si_id=?");
			stm.setInt(1, id);
			rs=stm.executeQuery();
			while(rs.next()){
				// TODO ��Ʒ��Ϣ��ȡ
				sinfo.setSi_id(rs.getInt("si_id"));
				sinfo.setSi_name(rs.getString("si_name"));
				sinfo.setSi_typeid(rs.getInt("si_typeid"));
				sinfo.setSi_typename(rs.getString("si_typename"));
				sinfo.setSi_time(rs.getDate("si_time"));
				sinfo.setSi_photo(rs.getString("si_photo"));
				sinfo.setSi_bigphoto(rs.getString("si_bigphoto"));
				sinfo.setSi_price(rs.getDouble("si_price"));
				sinfo.setSi_allnum(rs.getInt("si_allnum"));
				sinfo.setSi_salenum(rs.getInt("si_salenum"));
				sinfo.setSi_content(rs.getString("si_content"));
				sinfo.setSi_remark(rs.getString("si_remark"));
				sinfo.setSi_count(rs.getDouble("si_count"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection();
		}
		return sinfo;
	}
	
	/*
	 * ģ�����ң�ֻ��Ҫ������Ʒ���ƹؼ��֡�ѡ����Ʒ�۸�Χ�Լ���Ʒ���������
	 */
	public static List<shopinfos> find_by_blur(String name,int low,int high,String type){
		ResultSet rs=null;
		PreparedStatement stm=null;
		Connection con = null;
		List<shopinfos> shopInfo = new ArrayList<shopinfos>();
		con = DBConnection.getConnection();
		try {
			stm = con.prepareStatement("select * from t_shopinfos where si_name like ? and si_price>? and si_price<? and si_typename like ?");
			String n="%"+name+"%";
			stm.setString(1, n);
			stm.setInt(2, low);
			stm.setInt(3, high);
			String t="%"+type+"%";
			stm.setString(4, t);
			rs=stm.executeQuery();
			while(rs.next()){
				shopinfos sinfo = new shopinfos();
				sinfo.setSi_id(rs.getInt("si_id"));
				sinfo.setSi_name(rs.getString("si_name"));
				sinfo.setSi_typeid(rs.getInt("si_typeid"));
				sinfo.setSi_typename(rs.getString("si_typename"));
				sinfo.setSi_time(rs.getDate("si_time"));
				sinfo.setSi_photo(rs.getString("si_photo"));
				sinfo.setSi_bigphoto(rs.getString("si_bigphoto"));
				sinfo.setSi_price(rs.getDouble("si_price"));
				sinfo.setSi_allnum(rs.getInt("si_allnum"));
				sinfo.setSi_salenum(rs.getInt("si_salenum"));
				sinfo.setSi_content(rs.getString("si_content"));
				sinfo.setSi_remark(rs.getString("si_remark"));
				sinfo.setSi_count(rs.getDouble("si_count"));
				shopInfo.add(sinfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection();
		}
		return shopInfo;
	}
	
	/*
	 * ͨ������ID ������������
	 */
	public static String findname(int typeid){
		String name=null;
		ResultSet rs=null;
		PreparedStatement stm=null;
		Connection con = null;
		con = DBConnection.getConnection();
		try {
			stm=con.prepareStatement("select si_typename from t_shopinfos where si_typeid=?");
			stm.setInt(1, typeid);
			rs=stm.executeQuery();
			while(rs.next()){
				name=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection();
		}
		return name;
		
	}
	
	
	/*
	 * ��ͨ�Ĳ��� ��ͨ���������
	 */
	public static List<shopinfos> find_by_typeid(int typeid){
		ResultSet rs=null;
		Connection con = null;
        PreparedStatement stm=null;
        List<shopinfos> shopInfo = new ArrayList<shopinfos>();
		try {
			con = DBConnection.getConnection();
			stm = con.prepareStatement("select * from t_shopinfos where si_typeid=?");
			stm.setInt(1,typeid);
			rs=stm.executeQuery();
			//Statement stm=con.createStatement();
			//rs=stm.executeQuery("select * from t_shopinfos where si_name = 't-shirt'");
			while(rs.next()){
				shopinfos sinfo = new shopinfos();
				// TODO ��Ʒ��Ϣ��ȡ
				sinfo.setSi_id(rs.getInt("si_id"));
				sinfo.setSi_name(rs.getString("si_name"));
				sinfo.setSi_typeid(rs.getInt("si_typeid"));
				sinfo.setSi_typename(rs.getString("si_typename"));
				sinfo.setSi_time(rs.getDate("si_time"));
				sinfo.setSi_photo(rs.getString("si_photo"));
				sinfo.setSi_bigphoto(rs.getString("si_bigphoto"));
				sinfo.setSi_price(rs.getDouble("si_price"));
				sinfo.setSi_allnum(rs.getInt("si_allnum"));
				sinfo.setSi_salenum(rs.getInt("si_salenum"));
				sinfo.setSi_content(rs.getString("si_content"));
				sinfo.setSi_remark(rs.getString("si_remark"));
				sinfo.setSi_count(rs.getDouble("si_count"));
				shopInfo.add(sinfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection();
		}
		return shopInfo;
	}
	
	
	/*
	 * ����ͼƬ
	 */
	public static List<picture> findtu(int id){
		ResultSet rs=null;
		Connection con = null;
		PreparedStatement stm=null;
        List<picture> clothtu = new ArrayList<picture>();
		try {
			con = DBConnection.getConnection();
			stm = con.prepareStatement("select * from t_picture where p_sid=?");
			stm.setInt(1, id);
			rs=stm.executeQuery();
			while(rs.next()){
				picture tu = new picture();
				tu.setP_id(rs.getInt("p_id"));
				tu.setP_path(rs.getString("p_path"));
				tu.setP_type(rs.getString("p_type"));
				tu.setS_sid(rs.getInt("p_sid"));
				clothtu.add(tu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection();
		}
		return clothtu;
	}
	
	/*
	 * t_picture ��ͨ��p_id��path
	 */
	public static String findpicpath(int id){
		ResultSet rs=null;
		Connection con = null;
		PreparedStatement stm=null;
		String path = null;
		try {
			con = DBConnection.getConnection();
			stm = con.prepareStatement("select p_path from t_picture where p_id=?");
			stm.setInt(1, id);
			rs=stm.executeQuery();
			while(rs.next()){
				path=rs.getString("p_path");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection();
		}
		return path;
	}
	
}
