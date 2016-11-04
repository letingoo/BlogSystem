<%--
  Created by IntelliJ IDEA.
  User: letingoo
  Date: 2016/10/30
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SpringMVC 添加用户</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <script type="text/javascript">
        function loadComments(blogId) {
            $.get("/comment/comments/" + ${blog.id}, function(comments) {
                alert( $.parseJSON(comments) );
            })
        }

    </script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>





    <![endif]-->
</head>
<body onload="loadComments(${blog.id})">

<h1>${blog.title}</h1>

<p>${blog.content}</p>

<br>






<br>



<form method="post" action="/comment/comment/${blog.id}">
    <div class="form-group">
        <textarea name="content" type="text" id="commentContent"></textarea>
    </div>

    <div class="form-group">
       <button type="submit" class="btn btn-sm btn-success">>评论</button>
    </div>

</form>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>
