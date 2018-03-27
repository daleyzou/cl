<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.BuyList"%>
<%@ page import="java.util.*,java.io.*" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>购物车</title>
	<link rel="stylesheet" href="/cl/css/cl.css">
	<link rel="stylesheet" href="/cl/css/regist.css">
	<link rel="stylesheet" href="/cl/css/login.css">
	<link rel="stylesheet" href="/cl/css/shop.css">
	<link rel="stylesheet" href="/cl/css/shopcar.css">
	<link rel="stylesheet" href="/cl/bootstrap/bootstrap.min.css">
	<script src="/cl/bootstrap/bootstrap.min.js"></script>
	<style>
		#car li{
			border-bottom:1px solid #eee;
		}
	</style>
</head>
<body>
<div id="top">
		<p id="userinfo" style="margin-top:20px">欢迎来到潮流</p>
		<a href="/cl/user/userinfo.jsp"><p id="user" style="float: left; margin-left: 10px;margin-top:15px">
			<c:if test="${not empty sessionScope.user }">
				${sessionScope.user.u_name }	
			</c:if>	
		</p></a>
		<ul>
			<li><a href="/cl/cl/shopcar.jsp"><img src="/cl/img/u51.png" alt="">购物车</a></li>
			<c:if test="${empty sessionScope.user }">
				<li><a href="/cl/cl/regist.jsp">注册</a></li>
			<li><a href="/cl/cl/login.jsp">登陆</a></li>	
			</c:if>	
		</ul>
</div>
	<div class="container">
        <div id="logo">
			<a href="/cl/index.jsp">
				<div class="logo-img">
					<br>
					<span class="logo-span">C</span>
					<span class="logo-span">L</span>
					<br>
					<span class="logo-span-1">潮流</span>
				</div>
			</a>
			<div id="logozi" 
				style="visibility: visible;">
	         	<p><span>购物车</span></p>
	        	</div>
			<div class="search" style="margin-top: -80px;padding-top: 0">
				<input type="text" placeholder="请输入关键字">
				<div style="visibility: visible;" class="butt" class="col-md-2">
	          		<button id="searchinfo" onclick="">搜索<img src="/cl/img/u41.png" alt=""></button>
	        	</div>
			</div>
		</div>
		<div style="clear: both;"><br><hr></div>
		<%
		ArrayList buylist = (ArrayList) session.getAttribute("buylist");
		float total = 0;
	%>
	<table border="1" width="100%" rules="none" cellspacing="0"
		cellpadding="0" align="center">
	<tr height="50">
		<td colspan="5" align="center">购买商品如下</td>
	</tr>
	<tr align="center" height="30" bgcolor="lightgrey">
	    <td>商品名</td>
		<td>价格（元/件）</td>
		<td>数量</td>
		<td>总价（元）</td>
		<td>移除（-1/次）</td>
	</tr>
	<%
		if (buylist == null || buylist.size() == 0) {
			/* out.print(<script language="JavaScript"> function check(){ alert("请先购物!" );} </script>); */
	%>
	<tr height="100">
		<td colspan="5" align="center" id="tishi"><font color="#FF0000" size="6">您的购物车为空！</font></td>
	</tr>
	<%
		} else {
			for (int i = 0; i < buylist.size(); i++) {
				BuyList single = (BuyList) buylist.get(i);
				String shopID = single.getShopID();                    //获得商品ID
				float price = single.getPrice();
				int num = single.getNum();
				float money = ((int) ((price * num + 0.05f) * 10)) / 10f;
				total += money;
	%>
	<tr align="center" height="50">
		<td ><%=shopID%></td><td><!-- <img src=""></td> -->
		<td><%=price%></td>
		<td><%=num%></td>
		<td><%=money%></td>
		<td><a href="../ShopDelAction">移除</a></td>
	</tr>
	<%
		}

		}
	%>
	<tr height="50" align="center">
		<td colspan="5">应付金额：<%=total%></td>
	</tr>
	<tr height="50" align="center">
		<td colspan="2"><a href="../ShopDelAction">清空购物车</a></td>
		<td colspan="3"><input type="button" value="下单" onclick="check()"><!-- <a href="checkout.jsp">下单</a> --></td>
	</tr>
	</table>
	</div>
	</div>
<div id="foot">
	<hr>
	<p>本网站开发人员为全体潮流员工</p>
	<p>本网站版权所有为潮流，最终法律解释权为潮流</p>
	<p>联系我们:QQ群639195798</p>
	<p>友情链接 <a href="">天猫</a>
	<a href="">淘宝</a>
	<a href="">京东</a>
	<a href="">蘑菇街</a></p>
</div>
<script language="JavaScript">
var num=document.getElementByID("tishi");
function check(){
	 if (num==null){
		alert("请先购物!");
		//form.realname.focus();
		return ;
	}
	else{
		window.location.href="checkout.jsp";
	}
	
}
function fun3(){
	window.location.href="../ShopDelAction";
}
</script>
</body>
</html>