<%--
  Created by IntelliJ IDEA.
  User: 19277
  Date: 2022/3/11
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>设备管理</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="../js/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="../js/bootstrap.min.js"
            integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3"
            crossorigin="anonymous"></script>
    <script src="../js/jquery-3.6.0.min.js"></script>

    <style>
        tr {
            height: 40px;
        }
    </style>
</head>

<body>
<center>
    <div id="condition" style="text-align: center">
        <form id="myform" action="${pageContext.request.contextPath}/admin/findAddfacilityByPlan.action" method="get">
            <input id="page" type="hidden" name="page" value="${info.pageNum}">
        </form>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">编号</th>
            <th scope="col">设备名称</th>
            <th scope="col">设备位置</th>
            <th scope="col">详情</th>
            <th scope="col">提交时间</th>
            <th scope="col">处理进度</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${info.list}" var="c" varStatus="a">
            <tr>
                <td>${(info.pageNum - 1) * info.pageSize + a.index + 1}</td>
                <td>${c.fname}</td>
                <td>${c.location}</td>
                <td>${c.test}</td>
                <td>${c.createtime}</td>
                <td>${c.plan}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%-- 分页 --%>
    总共:${info.pages}页,当前第:${info.pageNum}页
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item ${!info.hasPreviousPage ? 'disabled' : ''}">
                <a class="page-link" onclick="ajax(${info.prePage})">Previous</a>
            </li>
            <c:forEach begin="1" end="${info.pages}" var="i">
                <li class="page-item ${info.pageNum == i ? 'active' : ''}">
                    <a class="page-link" onclick="ajax(${i})">${i}</a>
                </li>
            </c:forEach>
            <li class="page-item ${!info.hasNextPage ? 'disabled' : ''}">
                <a class="page-link" onclick="ajax(${info.nextPage})">Next</a>
            </li>
        </ul>
    </nav>
</center>

</body>
<script>
    function ajax(page) {
        $("#page").val(page);
        $("#myform").submit();
    }
</script>
</html>