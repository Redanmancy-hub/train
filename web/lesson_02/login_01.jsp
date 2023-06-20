<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.util.Random" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登陆界面</title>
</head>
<body>
<%
String username = request.getParameter("username");
Random random=new Random();
int rand=random.nextInt(100);
%>
<%
if(rand%2==0){
%>

<jsp:forward page="index.jsp">
	<jsp:param value="huyuqiang" name="username"/>
</jsp:forward>
<%
}else{
%>
0<jsp:forward page="error.jsp" />
<% 
}
%>
</body>
</html>