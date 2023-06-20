<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>3D旋转相册</title>
    <style type="text/css">
        *{padding: 0;
            margin: 0;}
        body,html{height:100%;}
        body{background-size: 100% 100%;background-attachment: fixed;}
        #box{width: 250px;
            height: 400px;
            position: fixed;
            left: 0;
            right: 0;
            bottom: 20px;
            margin: auto;
            transform-style: preserve-3d;
            transform: rotateX(0deg) rotateY(0deg);
            animation: go 45s linear infinite;
        }
        #box img{width: 100px;
            position: absolute;
            left: 0;
            top: 0;
        }
        #box img:nth-child(1){
            transform: rotateY(0deg) translateZ(650px);}
        #box img:nth-child(2){
            transform: rotateY(36deg) translateZ(650px);}
        #box img:nth-child(3){
            transform: rotateY(72deg) translateZ(650px);}
        #box img:nth-child(4){
            transform: rotateY(108deg) translateZ(650px);}
        #box img:nth-child(5){
            transform: rotateY(144deg) translateZ(650px);}
        #box img:nth-child(6){
            transform: rotateY(180deg) translateZ(650px);}
        #box img:nth-child(7){
            transform: rotateY(216deg) translateZ(650px);}
        #box img:nth-child(8){
            transform: rotateY(252deg) translateZ(650px);}
        #box img:nth-child(9){
            transform: rotateY(288deg) translateZ(650px);}
        #box img:nth-child(10){
            transform: rotateY(324deg) translateZ(650px);}
        #box img:nth-child(11){
            transform: rotateY(360deg) translateZ(650px);}
        @keyframes go {
            0%{transform: rotateX(0deg) rotateY(0deg);}
            25%{transform: rotateX(20deg) rotateY(180deg);}
            50%{transform: rotateX(0deg) rotateY(360deg);}
            75%{transform: rotateX(-20deg) rotateY(540deg);}
            100%{transform: rotateX(0deg) rotateY(720deg);}

        }
        #box img:hover{
            transform: scale(1.3);
        }
    </style>
</head>
<body>
<div id="box">
    <img src="../lesson_07/image/84222605ee68670.jpg">
    <img src="../lesson_07/image/1647319911735.jpeg">
    <img src="../lesson_07/image/1647319938012.jpeg">
    <img src="../lesson_07/image/1647319941306.jpeg">
    <img src="../lesson_07/image/1647319944291.jpeg">
    <img src="../lesson_07/image/1658376246439.jpeg">
    <img src="../lesson_07/image/-17b17435f99a37d9.jpg">
    <img src="../lesson_07/image/IMG_20221120_225231.jpg">
    <img src="../lesson_07/image/mmexport1678028747566.jpg">
    <img src="../lesson_07/image/mmexport1682350558387.jpg">
</div>
</body>
</html>
