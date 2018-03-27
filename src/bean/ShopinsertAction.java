package bean;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.myTools;
/**
 * Servlet implementation class ShopinsertAction
 */
@WebServlet("/ShopinsertAction")
public class ShopinsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopinsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		shopcarDB beanDB=new shopcarDB();
		//��ͻ����ύ����
		BuyList buy=new BuyList();
		//buy.setOrderID(request.getParameter("orderID"));
		//buy.setNum(request.getParameter(""));
		//buy.setPrice(myTools.strToFloat(request.getParameter("")));
		//buy.setUserName(myTools.toChinese(request.getParameter("")));
		//buy.setShopname(myTools.toChinese(request.getParameter("")));
		Date date = new Date(System.currentTimeMillis());
		buy.setOrderTime(date);//��
		buy.setRealname(myTools.toChinese(request.getParameter("realname")));
		buy.setUserAddress(myTools.toChinese(request.getParameter("address")));
		buy.setOrderTel(request.getParameter("tel"));//��
		buy.setPostCode(request.getParameter("youbian"));//��
		buy.setDetail(request.getParameter("detail"));
		System.out.println(buy);
		  beanDB.insertShop(buy);
		  
		  response.sendRedirect("/cl/cl/doaction.jsp");  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
