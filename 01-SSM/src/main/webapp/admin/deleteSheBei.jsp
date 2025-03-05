<%--
  Created by IntelliJ IDEA.
  User: 19277
  Date: 2022/3/11
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <style>
        tr {
            height: 40px;
        }
    </style>
</head>

<body>
<center>
    <div id="condition" style="text-align: center">
        <form id="myform" action="${pageContext.request.contextPath}/admin/deleteFacility.action" method="get">
            <input id="page" type="hidden" name="page" value="${info.pageNum}">
        </form>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">编号</th>
            <th scope="col">设备名称</th>
            <th scope="col">位置</th>
            <th scope="col">原因</th>
            <th scope="col">提交时间</th>
            <th scope="col">维修进度</th>
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
                <td>
                    <a href="${pageContext.servletContext.contextPath}/admin/deleteCheck.action?id=${c.id}">报废申请通过</a>
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
                <a class="page-link" href="<c:if test="${info.hasPreviousPage}"><c:url value='/admin/deleteFacility.action'/>?page=${info.prePage}</c:if>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <c:set var="startPage" value="${info.pageNum - 2 > 0 ? info.pageNum - 2 : 1}"/>
            <c:set var="endPage" value="${startPage + 4 < info.pages ? startPage + 4 : info.pages}"/>
            <c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
                <li class="page-item ${info.pageNum == pageNum ? 'active' : ''}">
                    <a class="page-link" href="<c:url value='/admin/deleteFacility.action'/>?page=${pageNum}">${pageNum}</a>
                </li>
            </c:forEach>
            <li class="page-item ${!info.hasNextPage ? 'disabled' : ''}">
                <a class="page-link" href="<c:if test="${info.hasNextPage}"><c:url value='/admin/deleteFacility.action'/>?page=${info.nextPage}</c:if>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>
    </nav>
</center>

<!-- 确保 jQuery 库在调用 $.ajax 之前已经加载 -->

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"></script>

<script>
    function ajaxPanDuan(id) {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/worker/editXinxi.action",
            data: {
                'id': id
            },
            success: function() {
                window.location.href = "${pageContext.request.contextPath}/worker/editBaoXiuState.action";
            }
        });
    }

    function ajax(page) {
        $("#page").val(page);
        $("#myform").submit();
    }

    function anzhuang(id) {
        window.location.href = "${pageContext.request.contextPath}/admin/anzhuangPost.action?id=" + id;
    }
</script>
</body>
</html>