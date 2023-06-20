<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
    h3{
        display: inline-block;
        margin:25px;
        clear: right;
    }
    p{
        display: block;
        margin-top: 30px;
        margin-right: 20px;
        float: right;
    }
</style>
<body>
<div>
    <h3><%=session.getAttribute("username") %>&nbsp;&nbsp;您好:</h3>
    <p>当前在线人数:${userCount}</p>
</div>
</body>
</html>