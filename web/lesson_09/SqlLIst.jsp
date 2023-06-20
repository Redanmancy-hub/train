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
        background-color: #f5f5f5;
        font-family: Arial, sans-serif;
        font-size: 16px;
        color: #333;
    }

    .main {
        width: 80%;
        margin: auto;
        background-color: #fff;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0px 0px 10px #ccc;
    }

    .main p {
        margin: 5px;
        padding: 5px;
        border-bottom: 1px solid #ccc;
    }

    .main p:last-child {
        border-bottom: none;
    }

    .main h1 {
        text-align: center;
        font-size: 30px;
        color: #333;
        margin: 20px 0;
    }
</style>

<body>
    <%-- 将注册用户全部显示出来 --%>
    <div class="main">
        <h2 style="text-align: center">注册用户详情</h2>
        姓名：<p>${userall.getUsername()}</p>
        密码：<p>${userall.getPasswd()}</p>
        性别：<p>${userall.getGender()}</p>
        爱好：<p>${userall.getFavor()}</P>
        描述：<p>${userall.getDescription()}</p>
        角色：<p>${userall.getRole()}</p>
        状态：<p>${userall.getState()}</p>
        <p><a href="userList.jsp">返回</a></p>
</div>
</body>
</html>
