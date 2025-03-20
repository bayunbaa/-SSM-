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
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            max-width: 1200px;
            margin: 50px auto;
            padding: 20px;
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
            justify-content: center;
            margin-top: 20px;
        }
        .pagination .page-item.active .page-link {
            background-color: #007bff;
            border-color: #007bff;
        }
        .pagination .page-link {
            color: #007bff;
        }
        .pagination .page-link:hover {
            color: #0056b3;
        }
        .form-control {
            border-radius: 8px;
            border: 1px solid #ced4da;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
            padding: 12px;
            font-size: 16px;
        }
        .form-control:focus {
            border-color: #80bdff;
            box-shadow: 0 0 0 .2rem rgba(0,123,255,.25);
        }
        .form-label {
            font-weight: 600;
            color: #495057;
        }
    </style>
</head>

<body>
<div class="container">
    <h1 class="text-center mb-4">设备管理</h1>
    <div id="condition" style="text-align: center">
        <form id="myform" action="${pageContext.request.contextPath}/admin/ajaxSplit.action" method="get">
            <input id="page" type="hidden" name="page" value="${info.pageNum}">
            设备名称：<input name="pname" id="pname" class="form-control" style="width: 200px; display: inline-block;">&nbsp;&nbsp;&nbsp;
            设备类型：<select name="typeid" id="typeid" class="form-control" style="width: 200px; display: inline-block;">
            <option value="-1">请选择</option>
            <option value="1" ${adminVo.typeid==1?'selected':''}>电脑</option>
            <option value="2" ${adminVo.typeid==2?'selected':''}>多功能黑板</option>
            <option value="3" ${adminVo.typeid==3?'selected':''}>课桌</option>
            <option value="4" ${adminVo.typeid==4?'selected':''}>其他</option>
        </select>&nbsp;&nbsp;&nbsp;
            设备等级：<select name="gradeid" id="gradeid" class="form-control" style="width: 200px; display: inline-block;">
            <option value=''>请选择</option>
            <option value="一级" ${adminVo.gradeid=='一级'?'selected':''}>一级</option>
            <option value="二级" ${adminVo.gradeid=='二级'?'selected':''}>二级</option>
            <option value="三级" ${adminVo.gradeid=='三级'?'selected':''}>三级</option>
            <option value="四级" ${adminVo.gradeid=='四级'?'selected':''}>四级</option>
        </select>
            <input type="submit" class="btn btn-primary" value="查询"/>
        </form>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">编号</th>
            <th scope="col">设备名称</th>
            <th scope="col">设备等级</th>
            <th scope="col">设备类型</th>
            <th scope="col">入库时间</th>
            <th scope="col">设备数量</th>
            <th scope="col">设备厂家</th>
            <th scope="col">备注</th>
            <th scope="col">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${info.list}" var="c" varStatus="a">
            <tr>
                <td>${(info.pageNum - 1) * info.pageSize + a.index + 1}</td>
                <td>${c.fname}</td>
                <td>${c.fgrade}</td>
                <td>
                    <c:choose>
                        <c:when test="${c.ftype == 1}">电脑</c:when>
                        <c:when test="${c.ftype == 2}">多功能黑板</c:when>
                        <c:when test="${c.ftype == 3}">课桌</c:when>
                        <c:when test="${c.ftype == 4}">其它</c:when>
                        <c:otherwise>未知</c:otherwise>
                    </c:choose>
                </td>
                <td>${c.ftime}</td>
                <td>${c.fnum}</td>
                <td>${c.ffctory}</td>
                <td>${c.ftrange}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/admin/findFacility.action?id=${c.id}" class="btn btn-sm btn-primary">修改</a>
                    <a href="${pageContext.servletContext.contextPath}/admin/chuku.action?id=${c.id}" class="btn btn-sm btn-primary">出库</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

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
    <%-- 为了多条件查询数据回显 --%>
    function ajax(page) {
        $("#page").val(page);
        $("#myform").submit();
    }

    // 检查是否有成功消息
    $(document).ready(function() {
        var message = "${message}";
        if (message) {
            alert(message);
        }
    });
</script>

</body>
</html>