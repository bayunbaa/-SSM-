<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>申请报废设备</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
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
            <h1 class="form-title">申请报废设备</h1>
            <form action="${pageContext.servletContext.contextPath}/teacher/delSheBei.action" method="post" onsubmit="return validateForm()">
                <div class="form-group">
                    <label for="fname">设备名称:</label>
                    <input type="text" id="fname" name="fname" class="form-control" placeholder="设备名称" required>
                </div>
                <div class="form-group">
                    <label for="location">设备位置:</label>
                    <input type="text" id="location" name="location" class="form-control" placeholder="如:303教室" required>
                </div>
                <div class="form-group">
                    <label for="reason">申请原因:</label>
                    <textarea id="reason" name="test" class="form-control" rows="4" placeholder="请描述申请原因" required></textarea>
                </div>
                <div class="form-group text-center">
                    <input type="submit" class="btn btn-primary btn-lg" value="提交申请">
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

<script src="../js/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="../js/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="../js/bootstrap.min.js" integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3" crossorigin="anonymous"></script>
<script>
    $(document).ready(function() {
        // 自动隐藏消息
        setTimeout(function() {
            $('#messageAlert').fadeOut('slow');
        }, 5000); // 5秒后自动隐藏
    });

    function validateForm() {
        var fname = document.getElementById('fname').value.trim();
        var location = document.getElementById('location').value.trim();
        var reason = document.getElementById('reason').value.trim();

        if (!fname) {
            alert('设备名称不能为空');
            return false;
        }
        if (!location) {
            alert('设备位置不能为空');
            return false;
        }
        if (!reason) {
            alert('申请原因不能为空');
            return false;
        }

        return true;
    }
</script>
</body>
</html>