<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑通知</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">编辑通知</h2>
    <form action="${pageContext.request.contextPath}/editinform.action" method="post">
        <div class="form-group">
            <label for="ibody">通知内容</label>
            <textarea class="form-control" id="ibody" name="ibody" rows="10"
                      placeholder="请输入通知内容" required maxlength="1000">${inform.ibody}</textarea>
        </div>
        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary">提交内容</button>
            <a href="${pageContext.request.contextPath}/public/admininform.jsp" class="btn btn-secondary ml-2">取消</a>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
$(document).ready(function() {
    $('form').on('submit', function(e) {
        var ibody = $('#ibody').val().trim();
        if (ibody === '') {
            e.preventDefault();
            alert('通知内容不能为空');
        }
    });
});
</script>
</body>
</html>