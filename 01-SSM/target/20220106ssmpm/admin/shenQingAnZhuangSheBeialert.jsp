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
    <h1 class="text-center mb-4">设备管理</h1>

    <!-- 提示信息模态框 -->
    <div class="modal fade" id="dialogModal" tabindex="-1" aria-labelledby="dialogModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="dialogModalLabel">提示信息</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>仓库没有该设备，请去设备进库添加</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-12">
            <form id="myform" action="${pageContext.servletContext.contextPath}/admin/findAll.action" method="get">
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
                    <th scope="col">设备位置</th>
                    <th scope="col">详情</th>
                    <th scope="col">提交时间</th>
                    <th scope="col">处理进度</th>
                    <th scope="col">操作</th>
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
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="anzhuang(${c.id}, '${c.location}')">安装</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%-- 分页 --%>
    <div class="row mt-4">
        <div class="col-12 text-center">
            <div class="mb-3">
                总共: ${info.pages} 页, 当前第: ${info.pageNum} 页
            </div>
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <li class="page-item ${!info.hasPreviousPage ? 'disabled' : ''}">
                        <a class="page-link" href="#" onclick="ajax(${info.prePage})">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${info.pages}" var="i">
                        <li class="page-item ${info.pageNum == i ? 'active' : ''}">
                            <a class="page-link" href="#" onclick="ajax(${i})">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${!info.hasNextPage ? 'disabled' : ''}">
                        <a class="page-link" href="#" onclick="ajax(${info.nextPage})">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>

<script>
    $(function() {
        $('#dialogModal').modal('show');
    });

    function ajax(page) {
        $("#page").val(page);
        $("#myform").submit();
    }

    function anzhuang(id, location) {
        window.location.href = "${pageContext.servletContext.contextPath}/admin/anzhuang.action?id=" + id + "&location=" + encodeURIComponent(location);
    }
</script>
</body>
</html>