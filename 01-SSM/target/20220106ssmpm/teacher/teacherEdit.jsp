<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改个人资料</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery.slim.min.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <style>
        .form-group.error .form-control {
            border-color: red;
        }
        .form-group.success .form-control {
            border-color: green;
        }
        #passwordMismatch,
        #successMessage {
            display: none;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h1 class="text-center">修改个人资料</h1>
            <form id="editForm" action="${pageContext.servletContext.contextPath}/teacher/teacherEdit.action" method="post" novalidate>
                <div class="form-group">
                    <label for="uname">用户名:</label>
                    <input type="text" id="uname" name="uname" value="${user.uname}" readonly class="form-control">
                </div>
                <div class="form-group">
                    <label for="newPassword">新密码:</label>
                    <input type="password" id="newPassword" name="newPassword" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="upassword">确认新密码:</label>
                    <input type="password" id="upassword" name="upassword" class="form-control" required>
                </div>
                <div class="form-group text-center">
                    <div id="passwordMismatch" class="text-danger">两个密码不匹配，请重试。</div>
                    <div id="successMessage" class="text-success">密码匹配，准备提交。</div>
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-primary">修改密码</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    (function () {
        // 缓存 DOM 元素
        const editForm = document.getElementById('editForm');
        const newPassword = document.getElementById('newPassword');
        const confirmPassword = document.getElementById('upassword');
        const passwordMismatch = document.getElementById('passwordMismatch');
        const successMessage = document.getElementById('successMessage');

        // 标志变量：初次进入不校验
        let isInteracted = false;

        // 验证密码匹配
        function validatePasswords() {
            if (!isInteracted || confirmPassword.value === '') return;

            const passwordsMatch = newPassword.value === confirmPassword.value;
            passwordMismatch.style.display = passwordsMatch ? 'none' : 'block';
            successMessage.style.display = passwordsMatch ? 'block' : 'none';
            confirmPassword.classList.toggle('is-invalid', !passwordsMatch);
            confirmPassword.classList.toggle('is-valid', passwordsMatch);
            return passwordsMatch;
        }

        // 用户交互触发校验逻辑
        function onInput() {
            isInteracted = true; // 标记用户已交互
            validatePasswords();
        }

        // 双向绑定事件
        newPassword.addEventListener('input', onInput);
        confirmPassword.addEventListener('input', onInput);

        // 表单提交事件
        editForm.addEventListener('submit', function (event) {
            if (!validatePasswords()) {
                event.preventDefault(); // 阻止表单提交
            } else {
                successMessage.textContent = '提交中，请稍候...';
            }
        });
    })();
</script>
</body>
</html>