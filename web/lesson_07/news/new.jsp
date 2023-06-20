<%@ page import="cqgcxy.javaweb.news.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cqgcxy.javaweb.dao.NewsDao" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zxx">
<head>
    <title>新闻发布系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8"/>
    <meta name="keywords" content="Arts Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
	SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design"/>
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- Custom Theme files -->
    <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" media="all">
    <link href="css/style.css" type="text/css" rel="stylesheet" media="all">
    <!-- font-awesome icons -->
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <!-- //Custom Theme files -->
    <!-- online-fonts -->
    <link href="//fonts.googleapis.com/css?family=Amaranth:400,400i,700,700i" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=PT+Sans:400,400i,700,700i" rel="stylesheet">
</head>
<style>
    #newslist {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
    }

    #newslist li {
        width: 48%;
        margin-bottom: 20px;
    }

    #newslist li div:first-child {
        width: 100%;
        height: 0;
        padding-bottom: 75%;
        position: relative;
    }

    #newslist li div:first-child img {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    #spy ul li ul {
        display: none;
    }

    #spy ul li:hover ul {
        display: block;
    }

    #spy ul li ul li {
        background-color: #f1f1f1;
    }

    #spy ul li ul li a {
        color: #000;
        text-decoration: none;
        display: block;
        padding: 10px;
    }

    #spy ul li ul li:hover {
        background-color: #ccc;
    }
</style>
<script src="${pageContext.request.contextPath}/lesson_07/css/jquery-3.7.0.min.js"></script>
<body>
<%
    NewsDao newsDao = new NewsDao();
    ArrayList<News> newsList = newsDao.queryAllNews();
    HashSet<String> newsList1 = new HashSet<>();
    for (News news : newsList) {
        newsList1.add(news.getCategory());
    }
    pageContext.setAttribute("newsList", newsList1);
    ArrayList<News> title = newsDao.queryAllNews();
    pageContext.setAttribute("titleList", title);
%>

<div class="container-fluid">
    <div class="row">
        <!-- header -->
        <div class="col-lg-2" id="spy">
            <div class="header-agile">
                <h1>
                    <a class="navbar-brand" href="new.html">
                        news
                    </a>
                </h1>
            </div>
            <ul class="nav nav-pills flex-column mt-lg-5">
                <li class="nav-item"><a class="nav-link active" href="#home">主页</a></li>
                <li class="nav-item"><a class="nav-link" href="#myCarousel">热点新闻</a></li>
                <li class="nav-item"><a class="nav-link" href="#services">新闻类型</a></li>
                <li class="nav-item"><a class="nav-link" href="#glance">新闻列表</a></li>
                <li class="nav-item"><a class="nav-link" href="#explore">关于我们</a></li>
                <c:if test="${not empty username and Login_role == 9}">
                    <li class="nav-item">
                        <a class="nav-link" href="../../lesson_09/adminM.jsp">后台管理</a>
                    </li>
                </c:if>
                <c:if test="${not empty username}">
                    <li class="nav-item"><a class="nav-link"
                                            href="${pageContext.request.contextPath }/Logout.do">退出登录</a></li>
                </c:if>
            </ul>
        </div>
        <!-- //header -->
        <!-- main -->
        <div class="col-lg-10 scrollspy-example pr-0" data-spy="scroll" data-target="#spy">
            <!-- banner -->

            <div id="home" class="w3ls-banner d-flex  align-items-center justify-content-center">
                <div id="login_btn"><c:if test="${not empty username}">
                    <h3><%=session.getAttribute("username") %>&nbsp;&nbsp;您好:</h3>
                </c:if>
                <c:if test="${empty username}">
                    <div class="btnc">
                        <button class="btn1"><a href="../register.jsp">注册</a></button>
                        <button class="btn2"><a href="../login.jsp">登录</a></button>
                    </div>
                </c:if>
                </div>
                <div class="bnr-w3pvt text-center">
                    <h2 class="bnr-txt-w3">欢迎来到新闻发布系统</h2>
                </div>
            </div>
            <!-- //banner -->
            <!-- about -->
            <section class="slider_section">
                <div id="myCarousel" class="carousel slide banner-main" data-ride="carousel">
                    <div class="row py-sm-5 pt-3 pb-4">
                        <div class="col-lg-5 w3pvt-head">
                            <span class="w3-line"></span>
                            <h3 class="agile-title d-inline">热点新闻</h3>
                        </div>
                    </div>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <a href="#"><img class="first-slide" style="text-align: center" src="../../newsim/1.jpg"
                                             alt="First slide"></a>
                            <div class="container">
                                <div class="carousel-caption relative" style="text-align: center">
                                    <a href="${pageContext.request.contextPath}/QueryNew.do?title=习近平会见俄罗斯总理米舒斯京&news_id=16">
                                        <h1 style="color: #0b2e13;margin-left: 450px;margin-bottom: 100px">
                                            习近平会见俄罗斯总理米舒斯京</h1>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <a href="#"><img class="second-slide" src="../../newsim/6.jpg" alt="Second slide"
                                             style="width: 550px;height: 394px"></a>
                            <div class="container">
                                <div class="carousel-caption relative" style="text-align: center">
                                    <a href="${pageContext.request.contextPath}/QueryNew.do?title=太阳有意举办全明星赛 将向NBA和WNBA提出申请&news_id=25">
                                        <h1 style="color: #0b2e13;margin-left: 450px;margin-bottom: 100px">太阳有意举办全明星赛
                                            将向NBA和WNBA提出申请</h1>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <a href="#"><img class="third-slide" src="../../newsim/4.png" alt="Third slide"
                                             style="width: 550px ;height: 394px"></a>
                            <div class="container">
                                <div class="carousel-caption relative" style="text-align: center">
                                    <a href="${pageContext.request.contextPath}/QueryNew.do?title=微软吹响AI集结号：全面打通ChatGPT携手冲击谷歌&news_id=19">
                                        <h1 style="color: #0b2e13;margin-left: 450px;margin-bottom: 100px">
                                            微软吹响AI集结号：全面打通ChatGPT携手冲击谷歌 </h1>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <a href="#"><img class="third-slide" src="../../newsim/9.jpg" alt="Third slide"
                                             style="width: 550px;height: 394px"></a>
                            <div class="container">
                                <div class="carousel-caption relative" style="text-align: center">
                                    <a href="${pageContext.request.contextPath}/QueryNew.do?title=北京互联网法院：未成年人直播打赏案件平均标的额超6万元&news_id=26">
                                        <h1 style="color: #0b2e13;margin-left: 450px;margin-bottom: 100px">
                                            北京互联网法院：未成年人直播打赏案件平均标的额超6万元 </h1>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
                        <i class='fa fa-angle-left'></i>
                    </a>
                    <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
                        <i class='fa fa-angle-right'></i>
                    </a>
                </div>
            </section>
            <!-- //about -->
            <!-- services -->
            <div id="services">
                <div class="container py-lg-5 py-4">
                    <div class="row py-sm-5 pt-3 pb-4">
                        <div class="col-lg-5 w3pvt-head">
                            <span class="w3-line"></span>
                            <h3 class="agile-title d-inline">新闻类型</h3>
                        </div>
                    </div>
                    <div class="row mb-lg-5 pt-md-5">
                        <c:forEach items="${newsList}" var="category" varStatus="row">
                            <div class="col-lg-3 col-md-6 my-md-0 my-4">
                                <div class="abt-block active-sgrid" name="szxw" style="border: none">
                                    <h4>${category}</h4>
                                    <c:forEach items="${titleList}" var="title">
                                        <c:if test="${category == title.category}">
                                            <ul>
                                                <li>
                                                    <c:if test="${title.state == 1}">
                                                    <hr>
                                                        <a href="${pageContext.request.contextPath}/QueryNew.do?title=${title.title}&news_id=${title.newsId}">·${title.title}</a>
                                                    </c:if>
                                                </li>
                                            </ul>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <!-- //services -->
            <!-- glance -->
            <div id="glance">
                <div class="services_agile py-lg-5 py-4">
                    <div class="container py-md-5">
                        <div class="row py-lg-5">
                            <div class="col-lg-5 w3pvt-head">
                                <span class="w3-line"></span>
                                <h3 class="agile-title d-inline">新闻列表</h3>
                            </div>
                            <ul id="newslist">
                                <li style="display: list-item;" class="borderno">
                                    <div>
                                        <a target="_blank"
                                           href="${pageContext.request.contextPath}/QueryNew.do?title=福满京城兆吉祥 寻访北京带福字的老地名&news_id=20">
                                            <img src="../../newsim/5.jpg"/>
                                        </a>
                                    </div>
                                    <div class="title_con">
                                        <a href="${pageContext.request.contextPath}/QueryNew.do?title=福满京城兆吉祥 寻访北京带福字的老地名&news_id=20"
                                           target="_blank" style="text-align: center">福满京城兆吉祥 寻访北京带福字的老地名</a>
                                    </div>
                                </li>

                                <li style="display: list-item;" class="borderno">
                                    <div>
                                        <a target="_blank"
                                           href="${pageContext.request.contextPath}/QueryNew.do?title=中新（西兰）两军举行第11次战略对话&news_id=18">
                                            <img src="../../newsim/3.png"/>
                                        </a>
                                    </div>
                                    <div class="title_con">
                                        <a href="${pageContext.request.contextPath}/QueryNew.do?title=中新（西兰）两军举行第11次战略对话&news_id=18"
                                           target="_blank" style="text-align: center">中新（西兰）两军举行第11次战略对话</a>
                                    </div>
                                </li>

                                <li style="display: list-item;" class="borderno">
                                    <div>
                                        <a target="_blank"
                                           href="${pageContext.request.contextPath}/QueryNew.do?title=北京互联网法院：未成年人直播打赏案件平均标的额超6万元&news_id=26">
                                            <img src="../../newsim/9.jpg"/>
                                        </a>
                                    </div>
                                    <div class="title_con">
                                        <a href="${pageContext.request.contextPath}/QueryNew.do?title=北京互联网法院：未成年人直播打赏案件平均标的额超6万元&news_id=26"
                                           target="_blank" style="text-align: center">北京互联网法院：未成年人直播打赏案件平均标的额超6万元</a>
                                    </div>
                                </li>

                                <li style="display: list-item;" class="borderno">
                                    <div>
                                        <a target="_blank"
                                           href="${pageContext.request.contextPath}/QueryNew.do?title=《速度与激情10》中国内地票房最新突破6亿元&news_id=15">
                                            <img src="../../newsim/10.jpg"/>
                                        </a>
                                    </div>
                                    <div class="title_con">
                                        <a href="${pageContext.request.contextPath}/QueryNew.do?title=《速度与激情10》中国内地票房最新突破6亿元&news_id=15"
                                           target="_blank" style="text-align: center">《速度与激情10》中国内地票房最新突破6亿元</a>
                                    </div>
                                </li>
                            </ul>
                            <div class="col-lg-7 mt-lg-0 mt-3">
                            </div>
                        </div>
                    </div>
                </div>
                <!-- //services -->
            </div>
            <!-- //glance -->
            <!-- explore -->
            <div id="explore">
                <div class="explore-sec py-5">
                    <div class="container pt-lg-5">
                        <div class="w3pvt-head">
                            <span class="w3-line"></span>
                            <h3 class="agile-title d-inline text-white">系统介绍</h3>
                            <div class="mt-lg-5 mt-3">
                                <p class="text-white slide-txt">新闻发布系统便利于用户进行新闻的查看和评论的系统.</p>
                            </div>
                        </div>
                        <div class="row py-sm-5 mt-sm-0 mt-4">
                            <div class="col-lg-3 col-sm-6 flex-wrap  border-right p-0 slide-sec">
                                <div class="d-flex  align-items-center justify-content-center wthree-list-grid">
                                    <div class="slide-icon">
                                        <span class="fa fa-thumbs-up theme-color" aria-hidden="true"></span>
                                    </div>
                                    <div class="wthree-list-desc">
                                        <h5 class="text-white">vision</h5>
                                        <p class="text-white">Consectetur adipiscing elit.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="flex-wrap col-lg-3 col-sm-6 border-right p-0 slide-sec">
                                <div class="d-flex  align-items-center justify-content-center wthree-list-grid">
                                    <div class="slide-icon">
                                        <span class="fa fa-money slide-icon theme-color" aria-hidden="true"></span>
                                    </div>
                                    <div class="wthree-list-desc">
                                        <h5 class="text-white">affordable</h5>
                                        <p class="text-white">Consectetur adipiscing elit..</p>
                                    </div>
                                </div>
                            </div>
                            <div class="wthree-list-grid flex-wrap col-lg-3 col-sm-6 border-right p-0 slide-sec">
                                <div class="d-flex  align-items-center justify-content-center wthree-list-grid">
                                    <div class="slide-icon">
                                        <span class="fa fa-picture-o slide-icon theme-color" aria-hidden="true"></span>
                                    </div>
                                    <div class="wthree-list-desc">
                                        <h5 class="text-white">quality</h5>
                                        <p class="text-white">Consectetur adipiscing elit.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="wthree-list-grid  flex-wrap col-lg-3 col-sm-6 p-0 slide-sec">
                                <div class="d-flex  align-items-center justify-content-center wthree-list-grid">
                                    <div class="slide-icon">
                                        <span class="fa fa-briefcase slide-icon theme-color" aria-hidden="true"></span>
                                    </div>
                                    <div class="wthree-list-desc">
                                        <h5 class="text-white">24*7 support</h5>
                                        <p class="text-white">Consectetur adipiscing elit.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- //explore -->

            <div class="section">
                <div class="map-grid">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d6509687.090753893!2d-123.76387427440008!3d37.18697025540024!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x808fb9fe5f285e3d%3A0x8b5109a227086f55!2sCalifornia%2C+USA!5e0!3m2!1sen!2sin!4v1491201047627"
                            style="border:0" allowfullscreen></iframe>
                    <div class="w3layouts-contact-pos" style="width: 90%">
                        <div class="w3layouts-contact-pos-grid row">
                            <div class="col-2 text-center">
                                <span class="field-icon fa fa-map-marker" aria-hidden="true"></span>
                            </div>
                            <div class="col-10">
                                <ul>
                                    <li>3515 Carriage Court,Palm Springs</li>
                                    <li>California.</li>
                                </ul>
                            </div>
                        </div>
                        <div class="w3layouts-contact-pos-grid sr1 row">
                            <div class="col-2 text-center">
                                <span class="field-icon fa fa-phone" aria-hidden="true"></span>
                            </div>
                            <div class="col-10">
                                <ul>
                                    <li>+12 345 6789</li>
                                    <li>+12 345 6789</li>
                                    <li>+12 345 6789</li>
                                </ul>
                            </div>
                        </div>
                        <div class="w3layouts-contact-pos-grid sr2">
                            <div class="row">
                                <div class="col-2 text-center">
                                    <span class="field-icon fa fa-envelope-o fa-lg" aria-hidden="true"></span>
                                </div>
                                <div class="col-10">
                                    <ul>
                                        <li>
                                            <a href="mailto:info@example.com">info@example.com</a>
                                        </li>
                                        <li>
                                            <a href="mailto:info@example.com">info@example.com</a>
                                        </li>
                                        <li>
                                            <a href="mailto:info@example.com">info@example.com</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- footer -->
                <div class="footer-bottom py-lg-5 py-3">
                    <div class="footerv2-w3ls  text-center">
                        <h6 class="w3f_title">Socialize with Us</h6>
                        <ul class="social-iconsv2 agileinfo pt-3">
                            <li>
                                <a href="#">
                                    <span class="fa fa-facebook"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="fa fa-twitter"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="fa fa-google-plus"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="fa fa-linkedin"></span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="footer-copy text-center">
                        <p class="text-dark">© 2018 Arts. All rights reserved | Design by
                            <a href="//w3layouts.com/" class="text-dark">W3layouts</a>
                        </p>
                    </div>
                </div>
                <!-- //footer -->
            </div>
        </div>
        <!-- //contact -->
    </div>
    <!-- //main -->
</div>
</div>

<!-- js -->
<script src="js/jquery-2.2.3.min.js"></script>
<!-- //js -->
<!-- start-smooth-scrolling -->
<script src="js/move-top.js"></script>
<script src="js/easing.js"></script>
<script>
    jQuery(document).ready(function ($) {
        $(".scroll").click(function (event) {
            event.preventDefault();

            $('html,body').animate({
                scrollTop: $(this.hash).offset().top
            }, 1000);
        });
    });
</script>
<!-- //end-smooth-scrolling -->
<!-- smooth-scrolling-of-move-up -->
<script>
    $(document).ready(function () {
        /*
        var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
        };
        */

        $().UItoTop({
            easingType: 'easeOutQuart'
        });

    });
</script>
<script src="js/SmoothScroll.min.js"></script>
<!-- //smooth-scrolling-of-move-up -->
<script>
    window.onload = function () {
        document.getElementById("password1").onchange = validatePassword;
        document.getElementById("password2").onchange = validatePassword;
    }

    function validatePassword() {
        var pass2 = document.getElementById("password2").value;
        var pass1 = document.getElementById("password1").value;
        if (pass1 != pass2)
            document.getElementById("password2").setCustomValidity("Passwords Don't Match");
        else
            document.getElementById("password2").setCustomValidity('');
        //empty string means no validation error
    }
</script>
<!-- script for password match -->


<script src="js/bootstrap.min.js"></script>
</body>

</html>