package bean;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.shopcarDB;

/**
 * Servlet implementation class ShopDelAction
 */
@WebServlet("/ShopDelAction")
public class ShopDelAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopDelAction() {
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
		//1����ȡ�ͻ�������
		int id=Integer.parseInt(request.getParameter("id"));		//��ȡ�ͻ����ύ����
		//2���������ݴ���
	 	int count=beanDB.deleteShopcar(id);							//����ҵ����Bean�ķ�����������
	 	//3����ͻ���������Ӧ
		response.sendRedirect(request.getContextPath()+"shopcar.jsp" );//�ض���
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
