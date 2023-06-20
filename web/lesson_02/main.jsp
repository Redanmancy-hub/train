<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
int n=7;
for(int i = 1; i <= n; i++) {
    // 打印空格
    for(int j = 1; j <= n - i; j++) {
        out.print("&nbsp");
    }
    // 打印星号
    for(int j = 1; j <= 2 * i - 1; j++) {
        out.print("*");

    }
    out.print("<br>");
    out.println();
} %>
</body>
</html>