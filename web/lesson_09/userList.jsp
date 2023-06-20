<%@ page import="cqgcxy.javaweb.User" %>
<%@ page import="java.util.List" %>
<%@ page import="cqgcxy.javaweb.dao.UserDao" %><%--使用jstl的core标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户列表</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        font-size: 16px;
        line-height: 1.5;
        margin: 0;
        padding: 0;
    }

    p {
        margin: 10px 0;
    }

    .form {
        margin: 10px 0;
    }

    form {
        display: flex;
        flex-wrap: wrap;
    }

    input[type="text"] {
        margin-right: 10px;
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
        width: 200px;
    }

    input[type="submit"] {
        padding: 5px 10px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }

    .main {
        margin: 20px 0;
    }

    table {
        border-collapse: collapse;
        width: 100%;
    }

    td {
        padding: 10px;
        border: 1px solid #ccc;
    }

    td:last-child {
        text-align: center;
    }

    td a {
        color: #007bff;
        text-decoration: none;
    }

    td a:hover {
        text-decoration: underline;
    }

</style>
<body>
<p>当前人数：${userList.size() }</p>
<div class="form">
    <form action="${pageContext.request.contextPath}/Userqueryk.do" method="get">
        <input type="text" value="" name="username" placeholder="请输入你要查询的用户名">
        <input type="text" value="" name="gender" placeholder="请输入你要查询的性别">
        <input type="submit" value="🔍">
    </form>
</div>
<div class="main">
<%-- 将注册用户全部显示出来 --%>
<table class="gridtable">
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>性别</td>
        <td>爱好</td>
        <td>描述</td>
        <td>角色</td>
        <td>状态</td>
        <td>操作&nbsp;<a href="../lesson_07/register.jsp">+</a></td>
    </tr>


    <%-- 需要重复的内容，用forEach标签。items属性是集合对象，var属性是循环变量，varStatus是当次循环的状态 --%>
    <c:forEach items="${userList }" var="user" varStatus="row">
    <tr>
        <%--<td>${row.index+1}</td>--%>
        <td>${(currPage - 1) * pageSize + row.index + 1}</td>
        <%-- userList中放的是User类的对象，User类遵循的是javaBean规范，提供getter方法 --%>
        <td>${user.username}</td>
        <td>${user.gender}</td>
        <td>${user.favor}</td>
        <td>${user.description}</td>
        <c:if test="${user.role == 9}">
            <td>管理员</td>
        </c:if>
        <c:if test="${user.role == 1}">
            <td>用户</td>
        </c:if>
        <c:if test="${user.state==1}">
            <td>正常</td>
        </c:if>
        <c:if test="${user.state==0}">
            <td>禁用</td>
        </c:if>
        <td><a href="${pageContext.request.contextPath }/QueryUser.do?user_id=${user.userId}">详情</a>
            &nbsp;<a href="${pageContext.request.contextPath }/DeleteUser.do?user_id=${user.userId}" onclick="return confirm('确定要删除该用户吗？')">删除</a>
            &nbsp;<a href="${pageContext.request.contextPath }/UpdateUser.do?user_id=${user.userId}">修改</a>
        </td>
    </tr>
    </c:forEach>
</table>
</div>
<!-- 分页组件 -->
<div>
    <c:forEach begin="1" end="${pageNum}" var="pageIndex">
<%--        当前页高亮：class="${pageIndex == currPage?'highlight':''}"--%>
        <a href="${pageContext.request.contextPath}/UserListPage.do?currPage=${pageIndex}&pageSize=${pageSize}">${pageIndex}</a>
    </c:forEach>
</div>
</body>
</html>
