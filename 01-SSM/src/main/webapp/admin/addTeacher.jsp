<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加教师人员账号</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
            color: #495057;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 40px;
            background-color: #ffffff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            text-align: center;
        }
        .form-group {
            margin-bottom: 25px;
        }
        .form-control {
            border-radius: 5px;
            padding: 12px;
            font-size: 16px;
            border: 1px solid #ced4da;
            transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        }
        .form-control:focus {
            border-color: #80bdff;
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
        }
        .btn {
            font-size: 16px;
            padding: 12px 25px;
            border-radius: 5px;
            transition: background-color 0.15s ease-in-out, border-color 0.15s ease-in-out;
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
        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
            color: white;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
            border-color: #5a6268;
        }
        .error-message {
            color: #dc3545;
            font-size: 14px;
            margin-top: 10px;
        }
        h1 {
            font-size: 2.5rem;
            margin-bottom: 30px;
            color: #343a40;
        }
        label {
            font-size: 1.1rem;
            font-weight: 600;
            display: block;
            margin-bottom: 10px;
            color: #495057;
            text-align:left;
        }
        .alert {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>添加教师人员账号</h1>
    <form id="f1" action="${pageContext.servletContext.contextPath}/admin/addTeacher.action" method="post">
        <div class="form-group">
            <label for="uname">用户名:</label>
            <input type="text" class="form-control" id="uname" name="uname" onchange="ajaxUname()" placeholder="请输入用户名" required>
            <span id="s3" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="upassword1">密码:</label>
            <input type="password" class="form-control" id="upassword1" name="upassword" placeholder="请输入密码" required>
        </div>
        <div class="form-group">
            <label for="password2">确认密码:</label>
            <input type="password" class="form-control" id="password2" name="confirmPassword" placeholder="请确认密码" required>
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-primary mr-2" onclick="tijiao()">添加</button>
            <button type="button" class="btn btn-secondary" onclick="goBack()">返回</button>
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
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"></script>
<script>
    function ajaxUname() {
        $("#s3").empty();
        var uname = $("#uname").val();
        $.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/admin/ajaxUname.action",
            data: { 'uname': uname },
            success: function(data) {
                if (data) {
                    $("#s3").text("用户名重复!");
                }
            }
        });
    }

    function tijiao() {
        var uname = $("#uname").val();
        var password = $("#upassword1").val();
        var password2 = $("#password2").val();

        if (!uname || uname.length < 1) {
            $("#s3").text("用户名不能为空!");
            return false;
        }
        if (!password || password.length < 1) {
            $("#s3").text("密码不能为空!");
            return false;
        }
        if (!password2 || password2.length < 1) {
            $("#s3").text("确认密码不能为空!");
            return false;
        }
        if (password !== password2) {
            $("#s3").text("两次密码不一致!");
            return false;
        }
        $("#f1").submit();
    }

    function goBack() {
        window.location.href = "${pageContext.servletContext.contextPath}/admin/findTeacher.action";
    }
</script>
</body>
</html>