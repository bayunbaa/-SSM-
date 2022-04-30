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
    <title>Title</title>
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


    <link rel="stylesheet" href="../css/jquery-ui.min.css">
    <script src="../js/bootstrap.min.js" integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3" crossorigin="anonymous"></script>
    <script src="../js/jquery-ui.min.js"></script>

    <%--<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js" integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3" crossorigin="anonymous"></script>--%>

    <style>
        tr {
            height: 40px;
        }
    </style>
</head>
<script>
    $(function() {
        $( "#dialog" ).dialog();
        // layer.confirm("恭喜你，秒杀成功！查看订单？", {btn: ["确定"]},
        //     function () {
        //         layer.closeAll();
        //     });

    })
</script>

<body>
<center>
    <div id="dialog" title="提示信息">
        <p>维修人员已收到设备安装信息</p>
    </div>
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
            <th scope="col">设备位置</th>
            <th scope="col">详情</th>
            <th scope="col">提交时间</th>
            <th scope="col">处理进度</th>
            <th scope="col">是否安装</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${info.list}" var="c" varStatus="a">
            <%--
                 private Integer id;

    private String uid;

    private String fname;

    private String location;

    private String createtime;

    private String plan;

    private String test;
            --%>
            <tr>
                <%--<input type="hidden" id="hidden1" value="${c.id}">--%>
                <td>${a.index+1}</td>
                <td>${c.fname}</td>
                <td>${c.location}</td>
                <td>${c.test}</td>
                <td>${c.createtime}</td>
                <td>${c.plan}</td>
                <td><button type="button" class="btn btn-secondary" onclick="anzhuang(${c.id})">安装</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%-- 分页 --%>
    总共:${info.pages}页,当前第:${info.pageNum}页
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item ">
                <c:if test="${!info.hasPreviousPage}">
                    <a class="page-link">Previous</a>
                </c:if>
                <c:if test="${info.hasPreviousPage}">
                    <%--<a class="page-link" href="${pageContext.servletContext.contextPath}/admin/fenye.action?page=${info.prePage}">Previous</a>--%>
                    <%--<a class="page-link"--%>
                       <%--onclick="ajax(${info.prePage})" href="${pageContext.servletContext.contextPath}/admin/fenye.action?page=${info.prePage}">Previous</a>--%>
                    <a class="page-link"
                       onclick="ajax(${info.prePage})">Previous</a>
                </c:if>
            </li>
            <c:if test="${info.hasPreviousPage}">
                <%--<li class="page-item"><a class="page-link"--%>
                                         <%--href="${pageContext.servletContext.contextPath}/admin/fenye.action?page=${info.prePage}">${info.prePage}</a>--%>
                <a class="page-link"
                   onclick="ajax(${info.prePage})">${info.prePage}
                   </a>
                </li>
            </c:if>
            <li class="page-item">
                <%--<a class="page-link" href="${pageContext.servletContext.contextPath}/admin/fenye.action?page=${info.pageNum}">${info.pageNum}</a>--%>
                    <a class="page-link" onclick="ajax(${info.pageNum})">${info.pageNum}</a>

            </li>
            <c:if test="${info.hasNextPage}">
                <li class="page-item">
                    <%--<a class="page-link" href="${pageContext.servletContext.contextPath}/admin/fenye.action?page=${info.nextPage}">${info.nextPage}</a>--%>
                        <a class="page-link" onclick="ajax(${info.nextPage})">${info.nextPage}</a>
                </li>
            </c:if>
            <li class="page-item">
                <c:if test="${info.isLastPage}">
                    <a class="page-link">Next</a>
                </c:if>
                <c:if test="${!info.isLastPage}">
                    <%--<a class="page-link"--%>
                       <%--href="${pageContext.servletContext.contextPath}/admin/fenye.action?page=${info.nextPage}">Next</a>--%>
                    <a class="page-link" onclick="ajax(${info.nextPage})">Next</a>
                </c:if>
            </li>
        </ul>
    </nav>
</center>

</body>
<script>
    function ajaxPanDuan(id) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/worker/editXinxi.action",
            data: {
                'id':id
            },
            success: function(){
                window.location.href="http://localhost:8080/worker/editBaoXiuState.action"

            }
        });
    }



    <%-- 为了多条件查询数据回显 --%>
   function ajax(page) {
       $("#page").val(page)
       $("#myform").submit();
   }


    /* 点击去安装设备 */
    function anzhuang(id) {
        window.location.href="http://localhost:8080/admin/anzhuang.action?id="+id;

    }


</script>

</html>
