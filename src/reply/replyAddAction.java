package reply;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daleyzou.entity.Order;
import com.daleyzou.service.IOrderService;
import com.daleyzou.service.impl.OrderService;

import users.userEntity;

public class replyAddAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public replyAddAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		userEntity user=(userEntity) session.getAttribute("user");
		String content=request.getParameter("content");
		int shopid=Integer.parseInt(request.getParameter("shopid"));
		Date currentDate = new Date(System.currentTimeMillis());
		userreplyEntity ue=new userreplyEntity();
		replyDB rd=new replyDB();
		ue.setR_userid(user.getU_id());
		ue.setR_username(user.getU_name());
		ue.setR_content(content);
		ue.setR_shopid(shopid);
		ue.setR_time(currentDate);
		
		try {
			rd.inserUserReply(ue);
			//�ҽ����Ķ���״̬Ϊ������
			IOrderService orderService = new OrderService();
			Order order = new Order();
			int o_id = Integer.parseInt(request.getParameter("orderid"));
			order.setO_status(4);
			order.setO_id(o_id);
			orderService.update(order);
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		response.sendRedirect("user/reply.jsp");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
