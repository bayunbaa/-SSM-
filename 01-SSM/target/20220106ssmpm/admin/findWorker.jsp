<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户列表</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <style>
        tr {
            height: 40px;
        }
    </style>
</head>
<body>
<center>
    <div style="float: right;margin: 5px;">
        <a class="btn btn-default" href="<c:url value="/admin/addWorker.jsp"/>" type="button" value="添加人员">添加人员</a>
    </div>
    <div id="condition" style="text-align: center">
        <form id="myform" action="${pageContext.request.contextPath}/admin/findWork.action" method="get">
            <input id="page" type="hidden" name="page" value="${info.pageNum}">
        </form>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">编号</th>
            <th scope="col">用户名</th>
            <th scope="col">用户密码</th>
            <th scope="col">操作</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${info.list}" var="c" varStatus="a">
            <tr>
                <td>${(info.pageNum - 1) * info.pageSize + a.index + 1}</td>
                <td>${c.uname}</td>
                <td>******</td>
                <td>
                    <a class="btn btn-danger btn-sm" href="javascript:void(0);" onclick="updateUser(${c.uid})">修改</a>

                    <a class="btn btn-danger btn-sm" href="javascript:void(0);" onclick="deleteUser(${c.uid})">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%-- 分页 --%>
     <div>
        总共: ${info.pages} 页, 当前第: ${info.pageNum} 页
    </div>
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item ${!info.hasPreviousPage ? 'disabled' : ''}">
                <a class="page-link" href="<c:if test="${info.hasPreviousPage}"><c:url value='/admin/findWork.action'/>?page=${info.prePage}</c:if>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <c:set var="startPage" value="${info.pageNum - 2 > 0 ? info.pageNum - 2 : 1}"/>
            <c:set var="endPage" value="${startPage + 4 < info.pages ? startPage + 4 : info.pages}"/>
            <c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
                <li class="page-item ${info.pageNum == pageNum ? 'active' : ''}">
                    <a class="page-link" href="<c:url value='/admin/findWork.action'/>?page=${pageNum}">${pageNum}</a>
                </li>
            </c:forEach>
            <li class="page-item ${!info.hasNextPage ? 'disabled' : ''}">
                <a class="page-link" href="<c:if test="${info.hasNextPage}"><c:url value='/admin/findWork.action'/>?page=${info.nextPage}</c:if>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>
    </nav>
</center>


<script>
    function deleteUser(uid) {
        if (confirm("确定要删除该用户吗？")) {
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/deleteUser.action",
                type: "POST",
                data: { uid: uid },
                success: function(response) {
                    if (response.success) {
                        alert("删除成功！");
                        // 重新加载当前页数据
                        ajax(${info.pageNum});
                    } else {
                        alert("删除失败：" + response.message);
                    }
                },
                error: function() {
                    alert("删除请求失败，请稍后再试。");
                }
            });
        }
    }

    function updateUser(uid) {
        window.location.href = "${pageContext.request.contextPath}/admin/updateUser.action?uid=" + uid;
    }

    function ajax(page) {
        $("#page").val(page);
        $("#myform").submit();
    }
</script>
</body>
</html>