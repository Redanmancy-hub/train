<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新闻详情</title>
</head>
<style>
    .xwpl {
        margin-top: 20px;
        border: 1px solid #ccc;
        padding: 10px;
    }

    .wypl {
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 10px;
    }

    .pl_box {
        width: 100%;
        height: 100px;
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
        resize: none;
    }

    .right_box {
        float: right;
    }

    .login {
        display: inline-block;
        margin-right: 10px;
    }

    .login a {
        color: #666;
        text-decoration: none;
        margin-right: 10px;
    }

    .login button {
        background-color: #f90;
        border: none;
        color: #fff;
        padding: 5px 10px;
        border-radius: 5px;
        cursor: pointer;
        width: 80px;
    }

    .login button:hover {
        background-color: #ff6600;
    }

    #spy {
        /*        background-image: url(../lesson_07/news/images/map.jpg);*/
        background-repeat: repeat-x;
        animation: slideleft 20000s infinite linear;
        -webkit-animation: slideleft 20000s infinite linear;
        background-size: cover;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        -ms-background-size: cover;
        position: fixed;
        width: 100%;
        z-index: 0;
    }

    #spy:before {
        position: absolute;
        width: 100%;
        height: 50px;
        background: -webkit-linear-gradient(rgb(0, 198, 214), rgba(176, 49, 217, 0.77));
        background: -moz-linear-gradient(rgb(0, 198, 214), rgba(176, 49, 217, 0.77));
        background: -o-linear-gradient(rgb(0, 198, 214), rgba(176, 49, 217, 0.77));
        content: '';
        left: 0;
        top: 0;
        z-index: -1;
    }

    .nav {
        display: flex;
        flex-direction: row; /* 将列表项横向排列 */
        justify-content: space-around;
    }

    li {
        list-style: none;
    }


    .plun {
        margin-top: 20px;
        padding: 10px;
        background-color: #f5f5f5;
        border-radius: 5px;
    }

    .plun p {
        margin: 0;
        padding: 5px;
    }

    .plun p:nth-child(odd) {
        background-color: #fff;
    }

    .plun p:nth-child(even) {
        background-color: #f9f9f9;
    }

    .plun p:first-child {
        font-weight: bold;
    }

    .plun p:last-child {
        margin-bottom: 10px;
    }

</style>
<body>
<jsp:useBean id="strBean" class="cqgcxy.javaweb.util.NewsUtil"></jsp:useBean>
<jsp:setProperty name="strBean" property="str" value="${newsDetailAll.getContent()}"/>

<div id="spy">
    <ul class="nav nav-pills flex-column mt-lg-5">
        <li class="nav-item"><a class="nav-link active" href="../lesson_07/news/new.jsp#home" style="text-decoration: none">主页</a></li>
        <li class="nav-item"><a class="nav-link" href="../lesson_07/news/new.jsp#myCarousel" style="text-decoration: none">热点新闻</a></li>
        <li class="nav-item"><a class="nav-link" href="../lesson_07/news/new.jsp#services" style="text-decoration: none">新闻类型</a></li>
        <li class="nav-item"><a class="nav-link" href="../lesson_07/news/new.jsp#glance" style="text-decoration: none">新闻列表</a></li>
        <li class="nav-item"><a class="nav-link" href="../lesson_07/news/new.jsp#explore" style="text-decoration: none">关于我们</a></li>
        <c:if test="${Login_role == 9}">
            <li class="nav-item">
                <a class="nav-link" href="../lesson_09/adminM.jsp" style="text-decoration: none">后台管理</a>
            </li>
        </c:if>
    </ul>
</div>

<div class="main">

    <h2 style="text-align: center;padding-top: 60px">${newsDetailAll.getTitle()}</h2>
    <p style="text-align: center"><span style="float: left">浏览量:<%=session.getAttribute("view")%></span>${newsDetailAll.getAuthor()}发布于 ${newsDetailAll.getCreateTime()}</p>
    <hr>
    <div style="text-indent: 2em">
        <img src="${pageContext.request.contextPath}${newsDetailAll.getCoverUrl()}" alt=""
             style="text-align: center"><br>
        <p style="text-indent: 36px">
            <jsp:getProperty property="str" name="strBean"/>
        </p>
    </div>
    <hr>
</div>
<form action="${pageContext.request.contextPath}/AddComment.do" method="post">
    <div class="xwpl" style="border: none">
        <div class="wypl">
            <span>网友评论</span>
        </div>
        <div class="">
            <textarea class="pl_box" placeholder="我有话说..." name="comment_text"></textarea>
        </div>
        <div class="bto_box">
            <div class="right_box">
                <div class="login">
                    <c:if test="${empty username}">
                        <a href="login.jsp">登录</a>
                        <span>|</span>
                        <a href="register.jsp">注册</a>
                    </c:if>
                    <c:if test="${not empty username}">
                        <button id="fbpl">发布</button>
                    </c:if>
                </div>
            </div>
        </div>
            <!-- 评论 -->
            <div class="plun" style="padding-top: 5px">
                <c:if test="${not empty username}">
                    <c:forEach items="${newsIdComments}" var="comment">
                        <p>姓名：${comment.userName}
                            <span style="float: right">评论时间：${comment.publishTime}</span>
                          </p>
                        <p style="text-indent: 36px;" >内容：${comment.commentText}
                        </p>
                    </c:forEach>
                </c:if>
            </div>
    </div>
</form>
<script>
    document.getElementById("fbpl").addEventListener("click", function() {
        var commentContent = document.getElementById("comment").value.trim();

        if (commentContent === "") {
            alert("内容不能为空");
        } else {
            alert("发布成功");
        }
    });

</script>
</body>
</html>
