<%--
  Created by IntelliJ IDEA.
  User: 19277
  Date: 2022/3/11
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加维修人员账号</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-control {
            border-radius: 5px;
            padding: 10px;
            font-size: 16px;
        }
        .btn {
            font-size: 16px;
            padding: 10px 20px;
            border-radius: 5px;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .btn-default {
            background-color: #6c757d;
            border-color: #6c757d;
            color: white;
        }
        .btn-default:hover {
            background-color: #5a6268;
            border-color: #5a6268;
        }
        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="text-center">添加维修人员账号</h1>
    <form id="f1" action="${pageContext.servletContext.contextPath}/admin/addWorker.action" method="post">
        <div class="form-group">
            <label for="uname">用户名:</label>
            <input type="text" class="form-control" id="uname" name="uname" onchange="ajaxUname()" aria-describedby="unameHelp">
            <small id="unameHelp" class="form-text text-muted">请输入唯一的用户名。</small>
        </div>
        <div class="form-group">
            <label for="upassword1">密码:</label>
            <input type="password" class="form-control" id="upassword1" name="upassword" placeholder="密码">
        </div>
        <div class="form-group">
            <label for="password2">确认密码:</label>
            <input type="password" class="form-control" id="password2" name="confirmPassword" placeholder="确认密码">
        </div>
        <div id="s3" class="error-message"></div>
        <div class="text-center">
            <button type="button" class="btn btn-default" onclick="goBack()">返回</button>
            <button type="button" class="btn btn-primary" onclick="tijiao()">添加</button>
        </div>
    </form>
    <% if (request.getAttribute("successMessage") != null) { %>
    <div class="alert alert-success mt-3" role="alert">
        <%= request.getAttribute("successMessage") %>
    </div>
    <% } else if (request.getAttribute("errorMessage") != null) { %>
    <div class="alert alert-danger mt-3" role="alert">
        <%= request.getAttribute("errorMessage") %>
    </div>
    <% } %>
</div>

<script src="../js/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="../js/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="../js/bootstrap.min.js" integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3" crossorigin="anonymous"></script>
<script src="../js/jquery-3.6.0.min.js"></script>
<script>
    function ajaxUname() {
        $("#s3").empty();
        var uname = $("#uname").val();
        $.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/admin/ajaxUname.action",
            data: {
                'uname': uname
            },
            success: function(data) {
                if (data) {
                    $("#s3").html("用户名重复!");
                }
            }
        });
    }

    function tijiao() {
        $("#s3").empty();
        var uname = $("#uname").val();
        var password = $("#upassword1").val();
        var password2 = $("#password2").val();
        if (!uname || uname.length < 1) {
            $("#s3").html("用户名不能为空!");
            return false;
        }
        if (!password) {
            $("#s3").html("密码不能为空!");
            return false;
        }
        if (!password2) {
            $("#s3").html("确认密码不能为空!");
            return false;
        }
        if (password !== password2) {
            $("#s3").html("两次密码不一致!");
            return false;
        }
        $("#f1").submit();
    }

    function goBack() {
        window.location.href = "${pageContext.servletContext.contextPath}/admin/findWork.action";
    }
</script>
</body>
</html>