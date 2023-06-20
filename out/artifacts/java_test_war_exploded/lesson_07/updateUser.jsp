<%--
  Created by IntelliJ IDEA.
  User: 86135
  Date: 2023/5/19
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="cqgcxy.javaweb.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据库用户列表</title>
</head>
<style>
    body {
        background-color: #f2f2f2;
    }

    .main {
        margin: 50px auto;
        width: 500px;
        background-color: #fff;
        padding: 30px;
        box-shadow: 0px 0px 10px #ccc;
        border-radius: 5px;
    }

    h2 {
        text-align: center;
    }

    input[type="text"] {
        width: 100%;
        padding: 10px;
        margin-top: 10px;
        margin-bottom: 20px;
        border-radius: 5px;
        border: none;
        box-shadow: 0px 0px 5px #ccc;
    }

    input[type="button"] {
        padding: 10px 20px;
        background-color: #008CBA;
        color: #008CBA;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    input[type="button"]:hover {
        background-color: #006699;
    }

    p {
        text-align: center;
        margin-top: 20px;
    }

    a {
        text-decoration: none;
        color: #008CBA;
    }

    a:hover {
        color: #006699;
    }
</style>

<body>

<%-- 将注册用户全部显示出来 --%>
<form method="post" action="${pageContext.request.contextPath }/UpdateUser.do">
    <div class="main">
        <h2 style="text-align: center">修改用户详情</h2>
        <input type="hidden" name="userId" value="${userall.getUserId()}">
        姓名：<input type="text" name="username" value="${userall.getUsername()}">
        密码：<input type="text" name="passwd" value="${userall.getPasswd()}">
        性别：<input type="text" name="gender" value="${userall.getGender()}">
        爱好：<input type="text" name="hobby" value="${userall.getFavor()}">
        描述：<input type="text" name="description" value="${userall.getDescription()}">
        角色：<input type="text" name="role" value="${userall.getRole()}">
        状态：<input type="text" name="state" value="${userall.getState()}">
        <p><a style="float: left" href="../lesson_09/userList.jsp">返回</a><input style="float: right" type="submit" name="update" value="修改"></p>

    </div>
</form>
</body>
</html>
