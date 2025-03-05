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

    <%--<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js" integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3" crossorigin="anonymous"></script>--%>

    <style>
        tr {
            height: 40px;
        }
    </style>
</head>

<body>
<center>
    <div id="condition" style="text-align: center">
        <form id="myform" action="${pageContext.request.contextPath}/admin/caigou.action" method="get">
            <input id="page" type="hidden" name="page" value="${info.pageNum}">
            <%--<input type="hidden" id="pn" value="${adminVo.pname}">--%>
            <%--<input type="hidden" id="ty" value="${adminVo.typeid}">--%>
            <%--设备名称：<input name="pname" id="pname">&nbsp;&nbsp;&nbsp;--%>
            <%--设备类型：<select name="typeid" id="typeid">--%>
            <%--<option value="-1">请选择</option>--%>
            <%--<option value="1" ${adminVo.typeid==1?'selected':''}>电脑</option>--%>
            <%--<option value="2" ${adminVo.typeid==2?'selected':''}>多功能黑板</option>--%>
            <%--<option value="3" ${adminVo.typeid==3?'selected':''}>空调</option>--%>
            <%--<option value="4" ${adminVo.typeid==4?'selected':''}>其他</option>--%>
        <%--</select>&nbsp;&nbsp;&nbsp;--%>
            <%--<input type="submit"/>--%>
        </form>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">编号</th>
            <th scope="col">设备名称</th>
            <th scope="col">需要安装设备位置</th>
            <th scope="col">申请时间</th>
            <th scope="col">原因</th>
            <%--<th scope="col">操作</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${info.list}" var="c" varStatus="a">
            <%--

    private String fname;

    private String location;

    private String createtime;

    private String plan;

    private String test;
            --%>
            <tr>
                <td>${a.index +1}</td>
                <td>${c.fname}</td>
                <td>${c.location}</td>
                <td>${c.createtime}</td>
                <td>${c.test}</td>
                <%--<td><a href="${pageContext.servletContext.contextPath}/admin/chuku.action?id=${c.id}"><button type="button" class="btn btn-secondary" onclick="caigou(${c.id})">去采购</button></a></td>--%>
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
    <%-- 为了多条件查询数据回显 --%>
   function ajax(page) {
       $("#page").val(page)
       $("#myform").submit();
   }

   function caigou(id) {
       window.
   }

</script>

</html>
