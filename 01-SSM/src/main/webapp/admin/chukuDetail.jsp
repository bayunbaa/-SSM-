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
    <title>设备详情信息</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="../js/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="../js/bootstrap.min.js" integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3" crossorigin="anonymous"></script>
    <script src="../js/jquery-3.6.0.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            font-weight: bold;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">设备详情信息</h1>
    <form action="${pageContext.servletContext.contextPath}/admin/chukuback.action" method="post" class="mt-4" id="checkoutForm">
        <div class="form-group">
            <label for="id">编号:</label>
            <input type="text" id="id" name="id" class="form-control" value="${facility.id}" readonly>
        </div>
        <div class="form-group">
            <label for="ftid">设备编号:</label>
            <input type="text" id="ftid" name="ftid" class="form-control" value="${facility.ftid}" readonly>
        </div>
        <div class="form-group">
            <label for="fname">设备名称:</label>
            <input type="text" id="fname" name="fname" class="form-control" value="${facility.fname}" readonly>
        </div>
        <div class="form-group">
            <label for="fnum">设备数量:</label>
            <input type="text" id="fnum" name="fnum" class="form-control" value="${facility.fnum}" readonly>
        </div>
        <div class="form-group">
            <label for="fnum2">出库数量:</label>
            <input type="number" id="fnum2" name="fnum2" class="form-control" min="0" max="${facility.fnum}" value="0" required>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary" id="submitBtn">出库</button>
            <button type="button" class="btn btn-secondary" onclick="goBack()">返回</button>
        </div>
    </form>
</div>

<script>
    $(function () {
        // 监听出库数量输入框的变化
        $("#fnum2").change(function () {
            validateCheckoutQuantity();
        });

        // 监听表单提交
        $("#checkoutForm").submit(function(event) {
            if (!validateCheckoutQuantity()) {
                event.preventDefault(); // 阻止表单提交
            }
        });

        // 验证出库数量
        function validateCheckoutQuantity() {
            var maxNum = ${facility.fnum}; // 最大可用数量为设备总数量
            var num = parseInt($("#fnum2").val());

            // 检查输入值是否为有效数字
            if (isNaN(num)) {
                alert("请输入有效的数字！");
                $("#fnum2").val(0);
                return false;
            }
            // 检查是否超过最大数量
            else if (num > maxNum) {
                alert("出库数量不能大于设备数量！");
                $("#fnum2").val(maxNum);
                return false;
            }
            // 检查是否为负数
            else if (num < 0) {
                alert("出库数量不能为负数！");
                $("#fnum2").val(0);
                return false;
            }
            // 检查是否为0
            else if (num === 0) {
                alert("出库数量不能为0！");
                return false;
            }
            return true;
        }
    });

    // 返回按钮功能
    function goBack() {
        window.location.href = "${pageContext.request.contextPath}/admin/fenye.action";
    }

    // 页面加载完成后检查是否有消息需要显示
    $(document).ready(function() {
        var message = "${message}";
        if (message) {
            alert(message);
        }
    });
</script>
</body>
</html>