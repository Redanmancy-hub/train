<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body style="text-align: center;">
	<!-- HTML表单,用于web程序的 -->
	<form action="login_result.jsp" method="post">
		<div>
			<input type="text" name="username" placeholder="用户名">
		</div>
		<div>
			<input type="password" name="passwd" placeholder="密码">
		</div>
		<div>
			<input type="submit" value="立即登录"><a href="register.jsp">没有账号？请注册</a>
		</div>

	</form>
</body>
</html>