<%@ page import="cqgcxy.javaweb.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>处理能否登录成功的程序</title>
</head>
<body>
	<%--登陆的业务流程 --%>
	<%
		// 在取参数之前，设置字符集，解决乱码问题
		request.setCharacterEncoding("utf-8");
	%>
		<p>
		用户名是:<%=request.getParameter("username")%>
	<p>
	<p>
		密码是:<%=request.getParameter("passwd")%>
	<p>

		<%-- 检验用户名和密码是否正确，从而决定能否登录成功 --%>
<%
	//1.获取登录页输入的用户名和密码

	//2.和已有的数据做比较
//	final String USER = "胡虞强";
//	final String PASS = "123456";
	String username = request.getParameter("username");
	String passwd = request.getParameter("passwd");

	List<User> userList = (List) application.getAttribute("userList");
			if (userList == null) { // 首次注册的时候，application没有对象
				// 跳转到fail.jsp
				response.sendRedirect("login_fail.jsp");
				return;
			}

			for (User user : userList) {
				if (user.getUsername().equals(username) && user.getPasswd().equals(passwd)) {
					// 跳转到index.jsp
					// 在跳转之前，将用户登录信息存入session，方便在其他页面提取
					session.setAttribute("username", username);
					// 用重定向跳转网页，避免表单被重放。
					response.sendRedirect("index.jsp");
					return; // 登录成功直接结束当前程序，防止继续执行后续代码
				}
			}

			// 跳转到fail.jsp
			response.sendRedirect("login_fail.jsp");
	%>
</body>
</html>