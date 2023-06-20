<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="cqgcxy.javaweb.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--使用jstl的core标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  cqgcxy: 86135
  Date: 2023/4/1
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户列表</title>
    <style type="text/css">
        div.main {
            margin: 20px;
            border: 1px solid red;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 16px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        tr:not(:first-child):hover {background-color: coral;}
    </style>
</head>
<body>
<%
    //执行删除的jdbc
    // 1.注册驱动程序
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        System.out.println("驱动注册成功！可以进行数据库连接了");
    } catch (Exception e) {
        System.out.println("注册驱动失败！原因:" + e.getMessage());
        e.printStackTrace();
    }
    //1.连接MySQL
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
        System.out.println("<br>链接数据库成功！");
        try{
            //2.写sql语句（显示所有信息）
            String sql = "SELECT * FROM user ";
            //3.创建编译对象
            Statement pstmt = conn.createStatement();
            //4.执行sql
            ResultSet rs = pstmt.executeQuery(sql);
            //5.获取SQL结果
            ///将一行数据转换为User对象，在将User对象添加到集合中
            List<User> userList = new ArrayList<User>();
            while (rs.next()){//有数据才转换
                //转换
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPasswd(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setFavor(rs.getString("favor"));
//                user.setAge(rs.getInt("age"));
                user.setDescription(rs.getString("description"));
                userList.add(user);
            }

            // 将List存储到中pageContext
            pageContext.setAttribute("userList", userList);
        } catch (Exception e){
            out.print("sql执行失败：" + e.getMessage());
            e.printStackTrace();
        }finally {
            conn.close();
        }
    } catch (Exception e){
        out.print("数据库连接失败：" + e.getMessage());
        e.printStackTrace();
    }
%>
<p>当前人数：${userList.size() }</p>
<div class="main">
<%-- 将注册用户全部显示出来 --%>
<table class="gridtable">
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>性别</td>
        <td>爱好</td>
        <td>描述</td>
        <td>操作</td>
    </tr>

    <%-- 需要重复的内容，用forEach标签。items属性是集合对象， var属性是循环变量，varStatus是当次循环的状态 --%>
    <c:forEach items="${userList }" var="user" varStatus="row">
    <tr>
        <td>${row.index+1}</td>
        <%-- userList中放的是User类的对象，User类遵循的是javaBean规范，提供getter方法 --%>
        <td>${user.username}</td>
        <td>${user.gender}</td>
<%--        <td>${user.age}</td>--%>
        <td>${user.favor}</td>
        <td>${user.description}</td>
        <td><a href="${pageContext.request.contextPath }/QueryUser.do?user_id=${user.userId}">详情</a>&nbsp;<a href="${pageContext.request.contextPath }/DeleteUser.do?user_id=${user.userId}">删除</a></td>
    </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
