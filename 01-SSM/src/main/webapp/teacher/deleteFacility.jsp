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
<%--    <div id="condition" style="text-align: center">--%>
<%--        <form id="myform" action="${pageContext.request.contextPath}/admin/findAddfacilityByPlan.action" method="get">--%>
<%--            <input id="page" type="hidden" name="page" value="${info.pageNum}">--%>
<%--        </form>--%>
<%--    </div>--%>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">编号</th>
            <th scope="col">设备名称</th>
            <th scope="col">设备位置</th>
            <th scope="col">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${info.list}" var="c" varStatus="a">
            <tr>
                <td>${(info.pageNum - 1) * info.pageSize + a.index + 1}</td>
                <td>${c.fname}</td>
                <td>${c.location}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/teacher/delete.action?id=${c.id}">申请报废</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div>
        总共: ${info.pages} 页, 当前第: ${info.pageNum} 页
    </div>
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item ${!info.hasPreviousPage ? 'disabled' : ''}">
                <a class="page-link" href="<c:if test="${info.hasPreviousPage}"><c:url value='/admin/findAddfacilityByPlan2.action'/>?page=${info.prePage}</c:if>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <c:set var="startPage" value="${info.pageNum - 2 > 0 ? info.pageNum - 2 : 1}"/>
            <c:set var="endPage" value="${startPage + 4 < info.pages ? startPage + 4 : info.pages}"/>
            <c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
                <li class="page-item ${info.pageNum == pageNum ? 'active' : ''}">
                    <a class="page-link" href="<c:url value='/admin/findAddfacilityByPlan2.action'/>?page=${pageNum}">${pageNum}</a>
                </li>
            </c:forEach>
            <li class="page-item ${!info.hasNextPage ? 'disabled' : ''}">
                <a class="page-link" href="<c:if test="${info.hasNextPage}"><c:url value='/admin/findAddfacilityByPlan2.action'/>?page=${info.nextPage}</c:if>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
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