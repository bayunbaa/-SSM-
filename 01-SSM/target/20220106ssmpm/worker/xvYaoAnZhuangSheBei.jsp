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
    <title>需要安装设备信息</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            max-width: 1200px;
            margin: 50px auto;
            padding: 30px;
            background-color: #ffffff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .table {
            margin-top: 20px;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .pagination {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center mb-4">需要安装设备信息</h1>

    <!-- 提示信息模态框 -->
    <div class="modal fade" id="installModal" tabindex="-1" aria-labelledby="installModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="installModalLabel">提示信息</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p id="installMessage"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-12">
            <form id="myform" action="${pageContext.servletContext.contextPath}/worker/installshebeiPre.action" method="get">
                <input id="page" type="hidden" name="page" value="${info.pageNum}">
            </form>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-12">
            <table class="table table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">编号</th>
                    <th scope="col">设备名称</th>
                    <th scope="col">安装位置</th>
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
                            <a href="${pageContext.servletContext.contextPath}/worker/installshebei.action?id=${c.id}&page=${info.pageNum}">安装</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- 显示消息 -->
    <c:if test="${not empty message}">
        <script>
            $(document).ready(function() {
                $('#installMessage').text('${message}');
                $('#installModal').modal('show');
            });
        </script>
    </c:if>

    <%-- 分页 --%>
     <div>
        总共: ${info.pages} 页, 当前第: ${info.pageNum} 页
    </div>
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item ${!info.hasPreviousPage ? 'disabled' : ''}">
                <a class="page-link" href="<c:if test="${info.hasPreviousPage}"><c:url value='/worker/installshebeiPre.action'/>?page=${info.prePage}</c:if>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <c:set var="startPage" value="${info.pageNum - 2 > 0 ? info.pageNum - 2 : 1}"/>
            <c:set var="endPage" value="${startPage + 4 < info.pages ? startPage + 4 : info.pages}"/>
            <c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
                <li class="page-item ${info.pageNum == pageNum ? 'active' : ''}">
                    <a class="page-link" href="<c:url value='/worker/installshebeiPre.action'/>?page=${pageNum}">${pageNum}</a>
                </li>
            </c:forEach>
            <li class="page-item ${!info.hasNextPage ? 'disabled' : ''}">
                <a class="page-link" href="<c:if test="${info.hasNextPage}"><c:url value='/worker/installshebeiPre.action'/>?page=${info.nextPage}</c:if>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>
    </nav>
        </div>
    </div>
</div>

<script>
    function ajax(page) {
        $("#page").val(page);
        $("#myform").submit();
    }
</script>
</body>
</html>