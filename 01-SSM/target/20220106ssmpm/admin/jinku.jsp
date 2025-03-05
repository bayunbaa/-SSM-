<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加进库设备信息</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 30px;
            background-color: #ffffff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        h1 {
            text-align: center;
            color: #343a40;
            margin-bottom: 30px;
            font-weight: 600;
        }
        .form-group {
            margin-bottom: 25px;
        }
        .form-control {
            border-radius: 8px;
            border: 1px solid #ced4da;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
            padding: 12px;
            font-size: 16px;
        }
        .form-control:focus {
            border-color: #80bdff;
            box-shadow: 0 0 0 .2rem rgba(0,123,255,.25);
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            border-radius: 8px;
            padding: 12px 24px;
            font-size: 18px;
            font-weight: 500;
            transition: background-color 0.3s, border-color 0.3s;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .form-label {
            font-weight: 600;
            color: #495057;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>添加进库设备信息</h1>
    <form action="${pageContext.servletContext.contextPath}/admin/addShebei.action" method="post" onsubmit="return validateForm()">
        <div class="form-group row">
            <label for="ftid" class="col-sm-3 col-form-label form-label">设备编号</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="ftid" name="ftid" placeholder="设备编号" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="fname" class="col-sm-3 col-form-label form-label">设备名称</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="fname" name="fname" placeholder="设备名称" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="ftime" class="col-sm-3 col-form-label form-label">入库时间</label>
            <div class="col-sm-9">
                <input type="date" class="form-control" id="ftime" name="ftime" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="fnum" class="col-sm-3 col-form-label form-label">设备数量</label>
            <div class="col-sm-9">
                <input type="number" class="form-control" id="fnum" name="fnum" min="0" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="ftype" class="col-sm-3 col-form-label form-label">设备类型</label>
            <div class="col-sm-9">
                <select class="form-control" id="ftype" name="ftype" required>
                    <option value="">--请选择--</option>
                    <option value="1">电脑</option>
                    <option value="2">多媒体黑板</option>
                    <option value="3">课桌</option>
                    <option value="4">其他</option>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <label for="fgrade" class="col-sm-3 col-form-label form-label">设备等级</label>
            <div class="col-sm-9">
                <select class="form-control" id="fgrade" name="fgrade" required>
                    <option value="">--请选择--</option>
                    <option value="一级">一级</option>
                    <option value="二级">二级</option>
                    <option value="三级">三级</option>
                    <option value="四级">四级</option>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <label for="ffctory" class="col-sm-3 col-form-label form-label">设备厂家</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="ffctory" name="ffctory" placeholder="设备厂家" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="ftrange" class="col-sm-3 col-form-label form-label">备注</label>
            <div class="col-sm-9">
                <textarea class="form-control" id="ftrange" name="ftrange" rows="3"></textarea>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-9 offset-sm-3">
                <button type="submit" class="btn btn-primary">添加设备</button>
            </div>
        </div>
    </form>
</div>

<script src="../js/jquery.slim.min.js"></script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        var message = "${message}";
        if (message) {
            alert(message);
        }

        $('#ftid').on('input', function() {
            this.value = this.value.replace(/[^0-9]/g, '');
        });
    });

    function validateForm() {
        var ftid = document.getElementById('ftid').value.trim();
        var fname = document.getElementById('fname').value.trim();
        var ftime = document.getElementById('ftime').value.trim();
        var fnum = document.getElementById('fnum').value.trim();
        var ftype = document.getElementById('ftype').value.trim();
        var fgrade = document.getElementById('fgrade').value.trim();
        var ffctory = document.getElementById('ffctory').value.trim();

        if (!ftid) {
            alert('设备编号不能为空');
            return false;
        }
        if (!fname) {
            alert('设备名称不能为空');
            return false;
        }
        if (!ftime) {
            alert('入库时间不能为空');
            return false;
        }
        if (!fnum) {
            alert('设备数量不能为空');
            return false;
        }
        if (!ftype) {
            alert('设备类型不能为空');
            return false;
        }
        if (!fgrade) {
            alert('设备等级不能为空');
            return false;
        }
        if (!ffctory) {
            alert('设备厂家不能为空');
            return false;
        }

        return true;
    }
</script>
</body>
</html>