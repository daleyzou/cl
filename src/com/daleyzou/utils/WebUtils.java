package com.daleyzou.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ת��ͨ�÷���
 * ��װ������ת�������ض��򶼵�������
 * @author 11543
 *
 */
public class WebUtils {
	
	public static void goTo(HttpServletRequest request, HttpServletResponse response, Object uri)
		throws ServletException, IOException {
		if (uri instanceof RequestDispatcher){
			((RequestDispatcher)uri).forward(request, response);
		} else if (uri instanceof String) {
			response.sendRedirect(request.getContextPath() + uri);
		}
	}
}
