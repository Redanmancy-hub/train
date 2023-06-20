<%--
  Created by IntelliJ IDEA.
  cqgcxy: 86135
  Date: 2023/3/30
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setAttribute("username","admin");
%>
<c:if test="true">
    这里显示的内容表示表达式为true
</c:if>
<c:if test="false">
    这里内容不会显示
</c:if>
<br>
<c:if test="${not empty username}">
    用户名不为空，代表用户已登录
</c:if>
<c:if test="${empty username}">
    用户名为空，代表用户未登录
</c:if>

</body>
</html>
