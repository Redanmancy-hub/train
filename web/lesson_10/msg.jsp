<%--
  Created by IntelliJ IDEA.
  User: 86135
  Date: 2023/5/7
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
</head>
<%
    // 在取参数之前，设置字符集，解决乱码问题
    request.setCharacterEncoding("utf-8");
%>
<body>
    <form action="${pageContext.request.contextPath}/TestServlet.do" method="post">
        <label>您的姓名:
        <input type="text" id="name" name="name"></label><br>
        <p>您喜欢哪种音乐:</p>
        <label><input type="checkbox" name="hobby" value="古典音乐"/> 古典音乐 </label>
        <label><input type="checkbox" name="hobby" value="民族音乐"/> 民族音乐 </label>
        <label><input type="checkbox" name="hobby" value="流行音乐"/> 流行音乐 </label>
        <label><input type="checkbox" name="hobby" value="摇滚乐"/> 摇滚乐 </label><br><br>
        <input type="submit" value="提交">
        <input type="reset" value="重置">
    </form>

</body>
</html>
