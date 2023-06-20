<%@ page import="cqgcxy.javaweb.news.News" %>
<%@ page import="java.util.List" %>
<%@ page import="cqgcxy.javaweb.dao.NewsDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新闻后台数据</title>
</head>
<%--<%
    NewsDao newsDao = new NewsDao();
    String search = "";
    if(request.getParameter("newscondition")!=null){
        search=request.getParameter("newscondition");
    }
    List<News> list = newsDao.queryNewsCondition(search);
    pageContext.setAttribute("listNews",list);
%>--%>
<style>
    body {
        font-family: Arial, sans-serif;
        color: #333;
    }
    .form {
        margin-bottom: 20px;
    }
    .form input[type="text"] {
        padding: 5px;
        font-size: 16px;
        border-radius: 5px;
        border: 1px solid #ccc;
    }
    .form input[type="submit"] {
        padding: 5px 10px;
        font-size: 16px;
        border-radius: 5px;
        border: none;
        background-color: #007bff;
        color: #fff;
        cursor: pointer;
    }
    .newsl {
        margin-top: 20px;
    }
    .newslist {
        width: 100%;
        border-collapse: collapse;
    }
    .newslist td, .newslist th {
        padding: 10px;
        border: 1px solid #ccc;
    }
    .newslist .tb {
        background-color: #f5f5f5;
        font-weight: bold;
    }
    .newslist tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    .newslist tr:hover {
        background-color: #e6e6e6;
    }
</style>
<body>
<p>当前新闻数量：${listNews.size() }</p>
<div class="form">
    <form action="${pageContext.request.contextPath}/newsQueryk.do" method="get">
        <input type="text" name="newscondition" placeholder="请输入你要查询的内容">
        <input type="submit" value="🔍">
    </form>
</div>
<div class="newsl">
    <%-- 将注册用户全部显示出来 --%>
    <table class="newslist">
        <tr class="tb">
            <td>序号</td>
            <td>标题</td>
            <td>作者/来源</td>
            <td>状态</td>
            <td>新闻类别</td>
            <td>点击次数</td>
            <td>发布日期</td>
            <td>修改时间</td>
            <td>操作</td>
        </tr>

        <%-- 需要重复的内容，用forEach标签。items属性是集合对象，var属性是循环变量，varStatus是当次循环的状态 --%>
        <c:forEach items="${listNews }" var="news" varStatus="row">
                <tr>
                    <td>${(currPage - 1) * pageSize + row.index + 1}</td>
                    <td>${news.title}</td>
                    <td>${news.author}</td>
                    <c:if test="${news.state == 1}">
                        <td>上架</td>
                    </c:if>
                    <c:if test="${news.state == 0}">
                        <td>下架</td>
                    </c:if>
                    <td>${news.category}</td>
                    <td>${news.viewCount}</td>
                    <td>${news.createTime}</td>
                    <td>${news.updateTime}</td>
                    <td><a href="${pageContext.request.contextPath }/QueryNew.do?news_id=${news.newsId}">Q</a>
                        &nbsp;<a href="${pageContext.request.contextPath }/DeleteNew.do?news_id=${news.newsId}" onclick="return confirm('确定要删除该新闻吗？')">D</a>
                        &nbsp;<a href="${pageContext.request.contextPath }/UpdateNew.do?news_id=${news.newsId}">U</a>
                    </td>
                </tr>
        </c:forEach>
    </table>
        <div>
            <c:forEach begin="1" end="${pageNum}" var="pageIndex">
                <%--        当前页高亮：class="${pageIndex == currPage?'highlight':''}"--%>
                <a href="${pageContext.request.contextPath}/NewsListPage.do?currPage=${pageIndex}&pageSize=${pageSize}">${pageIndex}</a>
            </c:forEach>
        </div>
</div>
</body>
</html>
