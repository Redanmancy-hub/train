<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>界面</title>
<style type="text/css">
.banner{
	border:1px solid red;
	width:100%;
	height:80px;
}

.nav{
	border:1px solid blue;
	width:10%;
	height:600px;
	float:left;
}

.main{
	border:1px solid green;
	width:100%;
	height:600px;
    text-align: center;
}

</style>

</head>
<body>
<c:if test="${not empty username}">
    <div class="banner">
    <%@include file="top.jsp" %>
    </div>

    <div class="nav">
    <%@include file="nav.jsp" %>
    </div>

    <div class="main" style="text-align: center;">
        <iframe src="main.jsp" name="_main"></iframe>
    </div>
</c:if>

<c:if test="${empty username}">
    <c:redirect url="login.jsp"></c:redirect><%--等价与response.sendRedirect()方法
    直接跳转到登录界面--%>
</c:if>
</body>
</html>