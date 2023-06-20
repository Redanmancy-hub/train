<%--
  Created by IntelliJ IDEA.
  cqgcxy: 86135
  Date: 2023/3/30
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page isELIgnored="false" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    pageContext.setAttribute("age",17);
    request.setAttribute("age",18);
%>
<p>age:${requestScope.age}</p>
<p>age:${pageScope.age}</p>
<p>用户名是否为空:${empty username }</p>
<p>项目的根路径:${pageContext.request.contextPath }</p>
<P>访问者的ip：${pageContext.request.remoteAddr }</P>
</body>
</html>
