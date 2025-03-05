<%--
  Created by IntelliJ IDEA.
  User: 19277
  Date: 2022/3/11
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>报修设备信息</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery.slim.min.js" crossorigin="anonymous"></script>
    <script src="../js/popper.min.js" crossorigin="anonymous"></script>
    <script src="../js/bootstrap.min.js" crossorigin="anonymous"></script>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .form-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-title {
            text-align: center;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            font-weight: bold;
        }
        .form-control {
            border-radius: 5px;
        }
        .btn-primary {
            border-radius: 5px;
        }
        .alert {
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 form-container">
            <h1 class="form-title">报修设备信息</h1>
            <form action="${pageContext.servletContext.contextPath}/teacher/baoxiu.action" method="post" onsubmit="return validateForm()">
                <div class="form-group">
                    <label for="fname">设备名称:</label>
                    <input type="text" name="fname" id="fname" class="form-control" placeholder="设备名称" required>
                </div>
                <div class="form-group">
                    <label for="location">设备位置:</label>
                    <input type="text" name="location" id="location" class="form-control" placeholder="如:303教室" required>
                </div>
                <div class="form-group">
                    <label for="details">详情:</label>
                    <textarea name="details" id="details" class="form-control" placeholder="请描述设备问题" required></textarea>
                </div>
                <div class="form-group text-center">
                    <input type="submit" class="btn btn-primary btn-lg" value="提交报修">
                </div>
            </form>

            <!-- 显示消息 -->
            <c:if test="${not empty message}">
                <div id="messageAlert" class="alert alert-info" role="alert">
                        ${message}
                </div>
            </c:if>
        </div>
    </div>
</div>

<script>
    function validateForm() {
        var fname = document.getElementById('fname').value.trim();
        var location = document.getElementById('location').value.trim();
        var details = document.getElementById('details').value.trim();

        if (!fname) {
            alert('设备名称不能为空');
            return false;
        }
        if (!location) {
            alert('设备位置不能为空');
            return false;
        }
        if (!details) {
            alert('详情不能为空');
            return false;
        }

        return true;
    }

    $(document).ready(function() {
        // 自动隐藏消息
        setTimeout(function() {
            $('#messageAlert').fadeOut('slow');
        }, 5000); // 5秒后自动隐藏
    });
</script>
</body>
</html>