<%--
  Created by IntelliJ IDEA.
  User: 86135
  Date: 2023/5/7
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Result</title>
</head>
<style>
    ul{
        color: red;
    }

</style>
<%
    // 在取参数之前，设置字符集，解决乱码问题
    request.setCharacterEncoding("utf-8");
%>
<body>
    <p><c:out value="${name}"></c:out>&nbsp;&nbsp;喜欢的音乐:</p>

    <ul>
        <c:forEach var="music" items="${hobbys}">
            <li><c:out value="${music}"></c:out></li>
        </c:forEach>
    </ul>
</body>
</html>
