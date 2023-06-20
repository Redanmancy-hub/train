<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
</head>
<style>
    /* 左侧菜单栏样式 */
    .menu {
        float: left;
        width: 200px;
        height: 100%;
        background-color: #f1f1f1;
        overflow: auto;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    }

    .menu a {
        display: block;
        color: #333;
        padding: 12px 16px;
        text-decoration: none;
        font-size: 16px;
        border-bottom: 1px solid #ddd;
    }

    .menu a:hover {
        background-color: #ddd;
    }

    .active {
        background-color:#007bff;
        color: white;
    }

    /* 右侧管理页面样式 */
    .content {
        float: left;
        padding: 20px;
        width: 80%;
        height: 100%;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    }
</style>

<body>
<div class="menu">
    <a href="../lesson_07/news/new.jsp">返回主页</a>
    <a href="${pageContext.request.contextPath}/UserListPage.do" target="iframe_a" class="active">用户管理</a>
    <a href="${pageContext.request.contextPath}/NewsListPage.do" target="iframe_a" class="active">新闻管理</a>
    <a href="${pageContext.request.contextPath}/commentPage.do" target="iframe_a" class="active">评论管理</a>
    <a href="../lesson_07/publish_news.jsp" target="iframe_a" class="active">发布新闻</a>
</div>

<div class="content">
    <!-- 用户管理页面 -->
    <iframe name="iframe_a" style="height: 90%;width: 100%;border: none;"></iframe>
</div>

</body>
</html>
