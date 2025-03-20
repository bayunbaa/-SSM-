<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看当前登录用户的设备进度</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <style>
        tr {
            height: 40px;
        }
    </style>
</head>
<body>
<center>
    <div id="condition" style="text-align: center">
        <form id="myform" action="<c:url value='/teacher/baoXiuJinDu.action'/>" method="get">
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
                <td>${c.details}</td>
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
                <a class="page-link" href="<c:if test="${info.hasPreviousPage}"><c:url value='/teacher/baoXiuJinDu.action'/>?page=${info.prePage}</c:if>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <c:set var="startPage" value="${info.pageNum - 2 > 0 ? info.pageNum - 2 : 1}"/>
            <c:set var="endPage" value="${startPage + 4 < info.pages ? startPage + 4 : info.pages}"/>
            <c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
                <li class="page-item ${info.pageNum == pageNum ? 'active' : ''}">
                    <a class="page-link" href="<c:url value='/teacher/baoXiuJinDu.action'/>?page=${pageNum}">${pageNum}</a>
                </li>
            </c:forEach>
            <li class="page-item ${!info.hasNextPage ? 'disabled' : ''}">
                <a class="page-link" href="<c:if test="${info.hasNextPage}"><c:url value='/teacher/baoXiuJinDu.action'/>?page=${info.nextPage}</c:if>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>
    </nav>
</center>

<script>
    $(document).ready(function() {
        var message = "${message}";
        if (message) {
            alert(message);
        }
        // 初始时设置当前页码的高亮状态
        highlightActivePage(${info.pageNum});
    });

    function ajax(page) {
        $("#page").val(page);
        // 发送AJAX请求，而不是提交表单
        $.ajax({
            url: $("#myform").attr("action"),
            type: "get",
            data: {page: page},
            success: function(response) {
                // 更新表格内容
                $("table tbody").html($(response).find("table tbody").html());
                // 更新分页导航
                $(".pagination").html($(response).find(".pagination").html());
                // 设置当前页码的高亮状态
                highlightActivePage(page);
            },
            error: function() {
                // 处理错误情况
                alert("请求失败，请稍后再试。");
            }
        });
    }

    // 设置当前页码的高亮状态
    function highlightActivePage(pageNum) {
        $(".pagination .page-item").removeClass("active");
        $('a[href*="' + pageNum + '"]').closest('.page-item').addClass('active');
    }
</script>
</body>
</html>