<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>金字塔模型</title>
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
}

</style>

</head>
<body>
<div class="banner">
<%@include file="top.jsp" %>
</div>

<div class="nav">
<%@include file="nav.jsp" %>
</div>

<div class="main" style="text-align: center;">
<%@include file="main.jsp" %>
</div>

</body>
</html>