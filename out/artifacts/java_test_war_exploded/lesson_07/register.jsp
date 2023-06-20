<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<%--	<link rel="stylesheet" href="css/register.css"/>--%>
	<title>用户注册</title>
</head>
<%
	// 在取参数之前，设置字符集，解决乱码问题
	request.setCharacterEncoding("utf-8");
%>
<style>
	body {
		background-color: #f2f2f2;
		font-family: Arial, sans-serif;
		color: #333;
	}

	.box_1 {
		margin: auto;
		width: 500px;
		height: 600px;
		background-color: #fff;
		padding: 20px;
		border: 1px solid #ccc;
		box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	}

	table {
		width: 100%;
		border-collapse: collapse;
	}

	label {
		/*display: block;*/
		margin-bottom: 10px;
		font-size: 16px;
		font-weight: bold;
		height: 10px;
	}

	input[type="text"],
	input[type="password"],
	input[type="number"],
	textarea {
		width: 100%;
		padding: 8px;
		border-radius: 4px;
		border: 1px solid #ccc;
		box-sizing: border-box;
		font-size: 16px;
		margin-bottom: 10px;
	}

	input[type="radio"],
	input[type="checkbox"] {
		margin-right: 10px;
	}

	input[type="submit"],
	input[type="reset"] {
		background-color: #007bff;
		color: #ccc;
		padding: 10px 20px;
		border: none;
		border-radius: 4px;
		cursor: pointer;
		margin-right: 10px;
	}

	input[type="submit"]:hover,
	input[type="reset"]:hover {
		background-color: #007bff;
	}

	a {
		color: #007bff;
		text-decoration: none;
		margin-left: 10px;
		font-size: 14px;
	}

	a:hover {
		text-decoration: underline;
	}
</style>
<body>
<div class="box_1">
	<table>
		<form action="${pageContext.request.contextPath}/RegisterUser.do" method="post" onsubmit="return checkForm()">
			姓名：<label><input type="text" name="username"/></label><br>
			密码：<label>
			<input type="password" name="passwd"/>
		</label><br>

			确认密码：<label>
			<input type="password" name="passwd2"/>
		</label><br>
			性别：<label><input type="radio" name="gender" value="男"/> 男 </label>
			<label><input type="radio" name="gender" value="女"/> 女 </label><br>

			爱好：<label><input type="checkbox" name="hobby" value="java"/> java </label>
			<label><input type="checkbox" name="hobby" value="C"/> C </label>
			<label><input type="checkbox" name="hobby" value="python"/> python </label><br>

			年龄：<label>
			<input type="number" name="age"/>
		</label><br>

			描述：<label>
			<textarea name="description" cols="30" rows="10"></textarea>
		</label><br>
			<input type="hidden" name="id" value="1"/><!-- 隐藏域 -->
			<input type="reset" value="重置"/>
			<input type="submit" value="提交"/><a href="login.jsp">已有账号？请登录</a>
			<br>
		</form>
	</table>
</div>
<script>
	function checkForm() {
		var username = document.getElementsByName("username")[0].value;
		var passwd = document.getElementsByName("passwd")[0].value;
		var passwd2 = document.getElementsByName("passwd2")[0].value;
		var age = document.getElementsByName("age")[0].value;
		var description = document.getElementsByName("description")[0].value;

		if (username == "" || passwd == "" || passwd2 == "" || age == "" || description == "") {
			alert("请填写完整信息！");
			return false;
		}
		if(passwd!=passwd2){
			alert("两次密码不一致，请重新输入")
		}
	}
</script>

</body>
</html>