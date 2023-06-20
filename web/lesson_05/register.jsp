<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>用户注册</title>

</head>
<body background="https://pic.3gbizhi.com/2019/0928/20190928012439343.jpg">

	<div  style="text-align: center;">
		<table>
			<form action="register.jsp" method="post">
			 	姓名：<input type="text" name="username"/><br><br>
			 	密码：<input type="password" name="passwd"/><br><br>
				确认密码:<input type="password" name="passwd2"/><br><br>
			 	性别：<label><input type="radio" name="gender" value="0"/> 男 </label>
					<label><input type="radio" name="gender" value="1"/> 女 </label><br><br>
				爱好：<label><input type="checkbox" name="hobby" value="java"/> java </label>
					<label><input type="checkbox" name="hobby" value="C"/> C </label>
					<label><input type="checkbox" name="hobby" value="python"/> python </label>（可多选）<br><br>
<%--				日期时间：<input type="datetime-local" name="datetime"/><br><br>--%>
<%--				邮箱：<input type="email" name="email"/><br><br>--%>
				年龄：<input type="number" name="age"/><br><br>
<%--				学历：<select name="degree">--%>
<%--					<option value="0">------请选择------</option>--%>
<%--					<option value="1">大专</option>--%>
<%--					<option value="2">本科</option>--%>
<%--					<option value="3">硕士</option>--%>
<%--					<option value="4">博士</option>--%>
<%--				</select><br><br>--%>
				描述：<textarea name="description" cols="30" rows="10"></textarea><br><br>
				<input type="hidden" name="id" value="1"/><!-- 隐藏域 -->
				<!-- 表单常见按钮 -->
				<input type="reset" value="重置"/>
			 	<input type="submit" value="立即注册"/><a href="login.jsp">已有账号？请登录</a>
			 	<br>
			</form>
		</table>
	</div>

</body>
</html>