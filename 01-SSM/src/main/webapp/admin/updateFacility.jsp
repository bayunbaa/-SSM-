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
    <title>修改设备详情信息</title>
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
    <h1 class="text-center">修改设备详情信息</h1>
    <form action="${pageContext.servletContext.contextPath}/admin/updateFacility.action" method="post" class="mt-4">

       <div class="form-group">
            <input type="hidden" id="id" name="id" class="form-control" value="${facility.id}">
        </div>
        <div class="form-group">
            <input type="hidden" id="ftid" name="ftid" class="form-control" value="${facility.ftid}">
        </div>



        <div class="form-group">
            <label for="fname">设备名称:</label>
            <input type="text" id="fname" name="fname" class="form-control" value="${facility.fname}">
        </div>

<%--        <div class="form-group">--%>
<%--            <label for="fgrade">设备等级:</label>--%>
<%--            <input type="text" id="fgrade" name="fgrade" class="form-control" value="${facility.fgrade}">--%>
<%--        </div>--%>
        <div class="form-group row">
            <label for="fgrade" class="col-sm-3 col-form-label form-label">设备等级</label>
            <div class="col-sm-9">
                <select class="form-control" id="fgrade" name="fgrade" required>
                    <!-- 默认选项，根据 ${facility.fgrade} 动态选中 -->
                    <option value="一级" ${facility.fgrade == '一级' ? 'selected' : ''}>一级</option>
                    <option value="二级" ${facility.fgrade == '二级' ? 'selected' : ''}>二级</option>
                    <option value="三级" ${facility.fgrade == '三级' ? 'selected' : ''}>三级</option>
                    <option value="四级" ${facility.fgrade == '四级' ? 'selected' : ''}>四级</option>
                </select>
            </div>
        </div>



        <div class="form-group">
            <label for="ftime">设备入口时间:</label>
            <input type="text" id="ftime" name="ftime" class="form-control" value="${facility.ftime}" readonly>
        </div>


<%--        <div class="form-group">--%>
<%--            <label for="ftype">设备类型:</label>--%>
<%--            <input type="text" id="ftype" name="ftype" class="form-control" value="${facility.ftype}">--%>
<%--        </div>--%>
        <div class="form-group row">
            <label for="ftype" class="col-sm-3 col-form-label form-label">设备类型</label>
            <div class="col-sm-9">
                <select class="form-control" id="ftype" name="ftype" required>
                    <!-- 默认选项，根据 ${facility.ftype} 动态选中 -->
                    <option value="1" ${facility.ftype == '1' ? 'selected' : ''}>电脑</option>
                    <option value="2" ${facility.ftype == '2' ? 'selected' : ''}>多媒体黑板</option>
                    <option value="3" ${facility.ftype == '3' ? 'selected' : ''}>课桌</option>
                    <option value="4" ${facility.ftype == '4' ? 'selected' : ''}>其他</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="ffctory">设备厂家:</label>
            <input type="text" id="ffctory" name="ffctory" class="form-control" value="${facility.ffctory}">
        </div>
        <div class="form-group">
            <label for="ftrange">备注:</label>
            <input type="text" id="ftrange" name="ftrange" class="form-control" value="${facility.ftrange}">
        </div>

        <div class="form-group">
            <label for="fnum">设备数量:</label>
            <input type="number" id="fnum" name="fnum" class="form-control" value="${facility.fnum}">
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">保存修改</button>
            <button type="button" class="btn btn-secondary" onclick="goBack()">返回</button>
        </div>
    </form>
</div>

<script>
    $(function () {
        $("#fnum").change(function () {
            var maxNum = ${facility.fnum}; // 使用模板字符串将变量插入JavaScript代码中
            var num = parseInt($(this).val());
            if (isNaN(num)) {
                $(this).val(0);
            } else if (num > maxNum) {
                $(this).val(maxNum);
            } else if (num < 0) {
                $(this).val(0);
            }
        });
    });
    function goBack() {
        window.location.href = "${pageContext.request.contextPath}/admin/fenye.action";
    }
    $(document).ready(function() {
        var message = "${message}";
        if (message) {
            alert(message);
        }
    });
</script>
</body>
</html>