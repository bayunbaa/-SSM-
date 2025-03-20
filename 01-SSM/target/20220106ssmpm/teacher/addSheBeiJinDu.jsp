<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看申请安装设备进度</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.6.0.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .table-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .pagination {
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10 table-container">
            <h1 class="text-center mb-4">查看申请安装设备进度</h1>
            <div id="condition" style="text-align: center">
                <form id="myform" action="${pageContext.request.contextPath}/teacher/addSheBeiJindu.action" method="get">
                    <input id="page" type="hidden" name="page" value="${info.pageNum}">
                </form>
            </div>

            <table class="table table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">编号</th>
                    <th scope="col">设备名称</th>
                    <th scope="col">设备位置</th>
                    <th scope="col">详情</th>
                    <th scope="col">提交时间</th>
                    <th scope="col">处理进度</th>
                </tr>
                </thead>
                <tbody id="tableBody">
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

         <div>
        总共: ${info.pages} 页, 当前第: ${info.pageNum} 页
    </div>
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item ${!info.hasPreviousPage ? 'disabled' : ''}">
                <a class="page-link" href="<c:if test="${info.hasPreviousPage}"><c:url value='/teacher/addSheBeiJindu.action'/>?page=${info.prePage}</c:if>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <c:set var="startPage" value="${info.pageNum - 2 > 0 ? info.pageNum - 2 : 1}"/>
            <c:set var="endPage" value="${startPage + 4 < info.pages ? startPage + 4 : info.pages}"/>
            <c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
                <li class="page-item ${info.pageNum == pageNum ? 'active' : ''}">
                    <a class="page-link" href="<c:url value='/teacher/addSheBeiJindu.action'/>?page=${pageNum}">${pageNum}</a>
                </li>
            </c:forEach>
            <li class="page-item ${!info.hasNextPage ? 'disabled' : ''}">
                <a class="page-link" href="<c:if test="${info.hasNextPage}"><c:url value='/teacher/addSheBeiJindu.action'/>?page=${info.nextPage}</c:if>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>
    </nav>

<script>
    function ajax(page) {
        $.ajax({
            url: "${pageContext.request.contextPath}/teacher/addSheBeiJindu.action",
            type: "GET",
            data: { page: page },
            success: function(response) {
                // 更新表格内容
                $("#tableBody").html($(response).find("#tableBody").html());

                // 更新分页信息
                $("#pagination").html($(response).find("#pagination").html());

                // 更新当前页信息
                $(".text-center").html($(response).find(".text-center").html());
            }
        });
    }
</script>
</body>
</html>