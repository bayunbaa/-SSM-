<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加学生人员账号</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 30px;
            background-color: #ffffff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .form-group {
            margin-bottom: 2rem;
        }
        .form-control {
            border-radius: .375rem;
            border: 1px solid #ced4da;
            padding: .75rem 1rem;
            font-size: 1rem;
        }
        .btn {
            border-radius: .375rem;
            padding: .75rem 1.5rem;
            font-size: 1rem;
            font-weight: 600;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            color: white;
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
            font-size: 0.875rem;
            margin-top: 0.5rem;
        }
        .alert {
            margin-top: 1.5rem;
        }
        h1 {
            margin-bottom: 2rem;
            font-weight: 600;
            color: #343a40;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">添加管理员账号</h1>
    <form id="f1" action="${pageContext.servletContext.contextPath}/admin/addStudent.action" method="post">
        <div class="form-group">
            <label for="uname">用户名:</label>
            <input type="text" name="uname" id="uname" onchange="checkUsername()" class="form-control" placeholder="请输入用户名">
            <span id="unameError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="upassword1">密码:</label>
            <input type="password" name="upassword" id="upassword1" class="form-control" placeholder="请输入密码">
            <span id="passwordError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="password2">确认密码:</label>
            <input type="password" id="password2" name="confirmPassword" class="form-control" placeholder="请确认密码">
            <span id="confirmPasswordError" class="error-message"></span>
        </div>
        <div class="text-center">
            <button type="button" class="btn btn-default mr-2" onclick="goBack()">返回</button>
            <button type="button" class="btn btn-primary" onclick="submitForm()">添加</button>
        </div>
    </form>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger mt-3" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success mt-3" role="alert">
                ${successMessage}
        </div>
    </c:if>
</div>

<!-- 确保 jQuery 库在调用 $.ajax 之前已经加载 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"></script>

<script>
    function checkUsername() {
        $("#unameError").empty();
        var uname = $("#uname").val();
        if (uname.length < 1) {
            $("#unameError").html("用户名不能为空!");
            return;
        }
        $.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/admin/ajaxUname.action",
            data: {
                'uname': uname
            },
            success: function(data) {
                if (data) {
                    $("#unameError").html("用户名重复!");
                }
            }
        });
    }

    function submitForm() {
        $("#unameError").empty();
        $("#passwordError").empty();
        $("#confirmPasswordError").empty();

        var uname = $("#uname").val();
        var password = $("#upassword1").val();
        var password2 = $("#password2").val();

        if (uname.length < 1) {
            $("#unameError").html("用户名不能为空!");
            return;
        }

        if (password.length < 1) {
            $("#passwordError").html("密码不能为空!");
            return;
        }

        if (password2.length < 1) {
            $("#confirmPasswordError").html("确认密码不能为空!");
            return;
        }

        if (password !== password2) {
            $("#confirmPasswordError").html("两次密码不一致!");
            return;
        }

        $("#f1").submit();
    }
    function goBack() {
        window.location.href = "${pageContext.servletContext.contextPath}/admin/findAdmin.action";
    }
</script>
</body>
</html>