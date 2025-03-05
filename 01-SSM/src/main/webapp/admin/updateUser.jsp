<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改用户信息</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h1 class="text-center">修改用户信息</h1>
            <form id="updateForm" action="${pageContext.request.contextPath}/admin/updateUser2.action" method="post">
                <input type="hidden" name="uid" value="${user.uid}">
                <div class="form-group">
                    <label for="uname">用户名:</label>
                    <input type="text" id="uname" name="uname" value="${user.uname}" class="form-control" readonly>
                </div>
                <div class="form-group">
                    <label for="upassword">用户密码:</label>
                    <input type="password" id="upassword" name="upassword" value="${user.upassword}" class="form-control" required>
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-primary">保存修改</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $("#updateForm").submit(function(event) {
            event.preventDefault(); // 阻止表单默认提交行为

            $.ajax({
                url: $(this).attr("action"), // 表单的 action 属性
                type: "POST", // 请求类型
                data: $(this).serialize(), // 序列化表单数据
                success: function(response) {
                    if (response.success) {
                        alert("修改成功！"); // 显示成功提示

                    } else {
                        alert("修改失败：" + response.message); // 显示失败提示
                    }
                },
                error: function(xhr, status, error) {
                    alert("请求失败：" + error); // 处理 AJAX 请求失败的情况
                }
            });
        });
    });
</script>
</body>
</html>