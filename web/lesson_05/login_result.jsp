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
	request.setCharacterEncoding("UTF-8");
	//1.获取登录页输入的用户名和密码
	String username = request.getParameter("username");
	String passwd = request.getParameter("passwd");
	out.print("用户名为:" + username);
	out.print("密码为:" + passwd);
	//2.和已有的数据做比较
	final String USER = "胡虞强";
	final String PASS = "123456";
	if (USER.equals(username) && PASS.equals(passwd)) {
		//3.如果匹配成功，就跳转主页
		//out.print("<br>用户密码正确");
		
		session.setAttribute("username",username);
		//为了避免表单请求被重放，所以需要更改重定向的方法跳转网页
		response.sendRedirect("index.jsp");//重定向会产生要给全新的request对象，上一次请求中的数据全部清除
	} else {
		//4.如果匹配失败，就跳转错误页
		//out.print("<br>用户密码错误");
		response.sendRedirect("login_fail.jsp");
	}
	%>
</body>
</html>