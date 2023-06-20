<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login.css"/>
<title>登录</title>
</head>
<body style="text-align: center; background-color: #656161" >
<div class="box">
	<div class="login-box">
		<h1>登录</h1>
		<form action="${pageContext.request.contextPath}/LoginUser.do" method="post">
			<div class="user-box">
				<input type="text" name="username" required="">
				<label>账户</label>
			</div>
			<div class="user-box">
				<input type="password" name="passwd" required="">
				<label>密码</label>
			</div>
			<div>
			<button class="btn">立即登录</button>
			<p><a href="register.jsp" style="float:left;text-decoration: none">没有账号？请注册</a>
				<a style="float:right;text-decoration: none" href="reset_password.jsp">忘记密码</a>
			</p>
		</div>
		</form>
	</div>
</div>
</body>
</html>