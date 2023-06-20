<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布新闻</title>
    <script src="./ckeditor/ckeditor.js"></script>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background: white;
    }

    form {
        border: 1px solid #ccc;
        padding: 20px;
        background-color: #fff;
        width: 1000px;
        margin: 0 auto;
    }

    label {
        display: inline-block;
        width: 80px;
        font-weight: bold;
    }

    input[type="text"], select {
        padding: 5px;
        border-radius: 3px;
        border: 1px solid #ccc;
        width: 200px;
    }

    select {
        width: 210px;
    }

    input[type="file"] {
        padding: 5px;
    }

    textarea {
        padding: 5px;
        border-radius: 3px;
        border: 1px solid #ccc;
        width: 100%;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        border: none;
        padding: 10px 20px;
        border-radius: 3px;
        cursor: pointer;
        margin-top: 20px;
    }

    input[type="submit"]:hover {
        background-color: #007bff;
    }
</style>
<body>
<div>
    <form action="${pageContext.request.contextPath}/AddNew.do" method="post" enctype="multipart/form-data" onsubmit="return checkSubmit()">
        <label for="title">新闻标题：</label><input type="text" id="title" name="title" placeholder="请输入标题"><br><br>
        <label for="author">作者：</label><input type="text" id="author" name="author" placeholder="请输入作者"><br><br>
        <label for="type">类型：</label>
        <select id="type" name="category">
            <option value="时政新闻">时政新闻</option>
            <option value="经济新闻">经济新闻</option>
            <option value="法律新闻">法律新闻</option>
            <option value="军事新闻">军事新闻</option>
            <option value="科技新闻">科技新闻</option>
            <option value="文教新闻">文教新闻</option>
            <option value="体育新闻">体育新闻</option>
            <option value="社会新闻">社会新闻</option>
        </select><br><br>
        <div>请上传封面：<input type="file" name="cover"></div>
        <br>
        <label for="content">新闻内容：</label><br><br>
        <textarea id="content" name="content"></textarea><br><br>
        <input type="submit" value="立即发布">
    </form>
</div>
<script>
    CKEDITOR.replace("content");
</script>
<script>
    function checkSubmit() {
        var title = document.getElementById("title")[0].value;
        var author = document.getElementById("author")[0].value;
        var content = document.getElementById("content")[0].value;
        if (title == "" || author == "" || content == "") {
            alert("请填写完整信息！");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
