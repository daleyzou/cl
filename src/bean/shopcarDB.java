package bean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.DBConnection;


public class shopcarDB {
	private Connection con = null;
	/* 获取所有订单信息 */
    public ArrayList<BuyList> getAllShops() {
        ResultSet rs=null;
        Statement sql=null;
        ArrayList<BuyList> orderList=new ArrayList<BuyList>();
        try {
        	con=DBConnection.getConnection();
            sql=con.createStatement();
	    	rs=sql.executeQuery("SELECT * from t_order");
	    	while(rs.next()){
	    		BuyList order=new BuyList();
	    		order.setOrderID(rs.getString("o_id"));
	    		order.setNum(rs.getInt("o_nums"));
	    		order.setPrice(rs.getFloat("o_shopprice"));
	    		//order.setUserName(rs.getString("o_"));
	    		order.setRealname(rs.getString("o_name"));
	    		order.setShopname(rs.getString("o_shopname"));
	    		order.setUserAddress(rs.getString("o_adress"));
	    		order.setOrderTel(rs.getString("o_phone"));
	    		order.setPostCode(rs.getString("o_youbian"));
	    		order.setDetail(rs.getString("o_memo"));
	    		orderList.add(order);	    		
	    	}  
			rs.close();
			sql.close();

        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("获取所有订单信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return orderList;
    }
	//存入客户信息
	public int insertShop(BuyList info) {
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	Date date = new Date(System.currentTimeMillis());
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("insert into t_order (o_name,o_adress,o_phone,o_youbian,o_memo,o_time) values (?,?,?,?,?,?)");
    		//pStmt.setString(3,info.getNum());
    		//pStmt.setString(4,info.getShopname());
    		//pStmt.setFloat(5,info.getPrice());	
    		pStmt.setString(1,info.getRealname());
    		pStmt.setString(2,info.getUserAddress());	
    		pStmt.setString(3,info.getOrderTel());
    		System.out.println(info.getOrderTel());
    		pStmt.setString(4,info.getPostCode());	
    		pStmt.setString(5,info.getDetail());
    		pStmt.setDate(6,date);	
    		count=pStmt.executeUpdate();  
			pStmt.close();
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("存入订单信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
	//删除购物车
	public int deleteShopcar(int id) {
     	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("delete from  t_order   where o_id=?");
    		pStmt.setInt(1,id);		
    		count=pStmt.executeUpdate();
    		pStmt.close();    		
        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("删除购物车信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    }
	/* 获取指定用户信息 */
    public BuyList getShopinfoById(int id) {
        ResultSet rs=null;
    	PreparedStatement pStmt=null; 
    	BuyList shopinfo=null;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement("SELECT * FROM t_order where o_id=?");
    		pStmt.setInt(1,id);		
    		rs = pStmt.executeQuery();
	    	if(rs.next()){
	    		shopinfo=new BuyList();
	    		shopinfo.setOrderID(rs.getString("o_id"));
	    		shopinfo.setUserName(rs.getString("VC_STUDENT_CODE"));
	    		//shopinfo.setOrderTime(rs.getString("VC_STUDENT_NAME"));
	    		//shopinfo.setSex(rs.getInt("N_SEX"));
	    		//shopinfo.setGrade(rs.getInt("N_GRADE"));
	    		//shopinfo.setMajor(rs.getInt("N_MAJOR"));
	    		//shopinfo.setDetail(rs.getString("VC_DETAIL"));
	    	} 
			rs.close();
			pStmt.close();

        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("获取指定信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return shopinfo;
    }
    /*//通过商品id得到图片路径
    public String getshoptupianbyID(int id){
    	ResultSet rs=null;
    	PreparedStatement pStmt=null; 
    	BuyList tupian=null;
        try {
        	con=DBConnection.getConnection();
        	pStmt = con.prepareStatement("SELECT * FROM t_order where o_shopid=?");
        	pStmt.setInt(1,id);		
    		rs = pStmt.executeQuery();
    		if(rs.next()){
	    		tupian=new BuyList();
	    		
    		}
        }catch(Exception e){
        	e.printStackTrace();
        	System.out.println("获取图片信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}	
       return tupian;
    }*/
        
}
