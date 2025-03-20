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
    <title>仓库已有设备类型</title>
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
<div class="container">
    <h1 class="text-center mb-4">仓库已有设备类型</h1>
    <div id="condition" style="text-align: center">
        <form id="myform" action="${pageContext.request.contextPath}/worker/editBaoXiuState.action" method="get">
            <input id="page" type="hidden" name="page" value="${info.pageNum}">
        </form>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">编号</th>
            <th scope="col">设备名称</th>
            <th scope="col">进库时间</th>
            <th scope="col">数量</th>
            <th scope="col">厂家</th>
            <th scope="col">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${info.list}" var="c" varStatus="a">
            <tr>
                <td>${(info.pageNum - 1) * info.pageSize + a.index + 1}</td>
                <td>${c.fname}</td>
                <td>${c.ftime}</td>
                <td>${c.fnum}</td>
                <td>${c.ffctory}</td>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#installModal" onclick="prepareInstall(${c.id}, '${c.location}')">安装</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- 安装设备模态框 -->
    <div class="modal fade" id="installModal" tabindex="-1" aria-labelledby="installModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="installModalLabel">安装设备</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="installForm" action="${pageContext.request.contextPath}/worker/installDevice.action" method="post">
                        <input type="hidden" id="deviceId" name="id">
                        <div class="form-group">
                            <label for="location" class="form-label">安装位置</label>
                            <input type="text" class="form-control" id="location" name="location" required>
                        </div>
                        <div class="form-group">
                            <label for="notes" class="form-label">备注</label>
                            <textarea class="form-control" id="notes" name="notes" rows="3"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="submitInstallForm()">安装</button>
                </div>
            </div>
        </div>
    </div>

    <%-- 分页 --%>
    <div class="text-center mb-3">
        总共: ${info.pages} 页, 当前第: ${info.pageNum} 页
    </div>
    <nav aria-label="Page navigation example">
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

<script>
    function ajaxPanDuan(id) {
        $.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/worker/editXinxi.action",
            data: { 'id': id },
            success: function() {
                window.location.href = "${pageContext.servletContext.contextPath}/worker/editBaoXiuState.action";
            }
        });
    }

    function ajax(page) {
        $("#page").val(page);
        $("#myform").submit();
    }

    function prepareInstall(id, location) {
        $("#deviceId").val(id);
        $("#location").val(location);
    }

    function submitInstallForm() {
        $("#installForm").submit();
    }
</script>

</body>
</html>