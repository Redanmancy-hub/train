<%@ page import="cqgcxy.javaweb.dao.CommentDao" %>
<%@ page import="cqgcxy.javaweb.news.Comment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>评论列表</title>
</head>

<style>
  .form {
    text-align: center;
    margin-top: 20px;
  }
  .form input[type="text"] {
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 3px;
    font-size: 16px;
    width: 60%;
    margin-right: 10px;
  }
  .form input[type="submit"] {
    padding: 10px 20px;
    background-color: #1e90ff;
    color: #fff;
    border: none;
    border-radius: 3px;
    font-size: 16px;
    cursor: pointer;
  }
  .clist {
    margin-top: 20px;
  }
  .com {
    width: 100%;
    border-collapse: collapse;
    text-align: center;
  }
  .com th, .com td {
    border: 1px solid #ddd;
    padding: 10px;
  }
  .com th {
    background-color: #1e90ff;
    color: #fff;
    font-weight: normal;
  }
  .com tr:nth-child(odd) {
    background-color: #f5f5f5;
  }
</style>

<body>
<p>当前评论数量：${commentList.size() }</p>
<div class="form">
  <form action="${pageContext.request.contextPath}/commentQueryk.do" method="get">
    <input type="text" name="comcondition" placeholder="请输入你要查询的内容">
    <input type="submit" value="🔍">
  </form>
</div>
  <div class="clist">
    <table class="com">
      <tr>
        <td>序号</td>
        <td>用户</td>
        <td>新闻</td>
        <td>评论内容</td>
        <td>评论时间</td>
        <td>操作</td>
      </tr>

      <c:forEach items="${commentList}" var="comment" varStatus="row">
        <tr>
          <td>${(currPage - 1) * pageSize + row.index + 1}</td>
          <td>${comment.userId}</td>
          <td>${comment.newsId}</td>
          <td>${comment.commentText}</td>
          <td>${comment.publishTime}</td>
          <td><a style="text-decoration: none" href="${pageContext.request.contextPath}/DeleteComment.do?comment_id=${comment.commentId}" onclick="return confirm('确定要删除该信息吗？')">删除</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
<div>
  <c:forEach begin="1" end="${pageNum}" var="pageIndex">
    <%--        当前页高亮：class="${pageIndex == currPage?'highlight':''}"--%>
    <a href="${pageContext.request.contextPath}/commentPage.do?currPage=${pageIndex}&pageSize=${pageSize}">${pageIndex}</a>
  </c:forEach>
</div>
</body>
</html>
