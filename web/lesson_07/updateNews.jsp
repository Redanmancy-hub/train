<%@ page import="cqgcxy.javaweb.news.News" %>
<%@ page import="cqgcxy.javaweb.dao.NewsDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .main {
        width: 80%;
        margin: 0 auto;
        background-color: #f5f5f5;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-shadow: 0 0 5px #ddd;
    }

    h2 {
        font-size: 24px;
        margin-bottom: 20px;
    }

    input[type="text"], input[type="file"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
        font-size: 16px;
    }

    textarea {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
        font-size: 16px;
    }

    input[type="submit"] {
        padding: 10px 20px;
        background-color:  #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 18px;
        transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
        background-color:  #007bff;
    }

    a {
        color: #333;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }
</style>
<body>


<%-- 将发布的新闻全部显示出来 --%>
<form method="post" action="${pageContext.request.contextPath }/UpdateNew.do" enctype="multipart/form-data">
    <div class="main">
        <h2 style="text-align: center">修改新闻详情</h2>
        <input type="hidden" name="newsId" value="${newsall.getNewsId()}">
        新闻标题：<input type="text" name="title" value="${newsall.getTitle()}">
        作者：<input type="text" name="author" value="${newsall.getAuthor()}">
        状态。0-下架，1-上架：<input type="text" name="state" value="${newsall.getState()}">
        类型：<select id="type" name="category">
        <%
            NewsDao newsDao = new NewsDao();
            ArrayList<News> title1 = newsDao.queryAllNews();
            for(News news:title1){
                out.println("<option value='"+news.getCategory()+"'>"+news.getCategory()+"</option>");
            }
        %>
    </select><br><br>
        请上传封面：<input type="file" name="cover">
        <input type="hidden" name="before_url" value="${newsall.getCoverUrl()}">
        内容：<textarea id="content" name="content" >${newsall.getContent()}</textarea>
        <p><a href="../lesson_09/newsList.jsp">返回</a></p>
        <input type="submit" name="update" value="修改">
    </div>
</form>
</body>
</html>
