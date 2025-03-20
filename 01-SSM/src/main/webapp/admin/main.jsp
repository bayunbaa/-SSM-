<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var ctx = "/ssh_xybxsys";
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery-3.6.0.min.js"></script>
</head>
<body style="background-color: #f2f9fd;">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1 style="color: black;">
            <img src="${pageContext.servletContext.contextPath}/images/guan-li-yuan.jpg"
                 class="radius-circle rotate-hover" height="50" alt="" />
            后台管理中心
        </h1>
    </div>
    <div class="head-l">
          <a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span> 前台首页</a>  -->
        &nbsp;&nbsp;<a class="button button-little bg-red"
                       href="${pageContext.servletContext.contextPath}/tuichu.action"><span class="icon-power-off"></span>
        退出登录</a>
    </div>
</div>
<div class="leftnav">
    <div class="leftnav-title">
        <strong style="color: black;"><span class="icon-list"></span>菜单列表</strong>
    </div>

    <h2>
        <span class="icon-user"></span>公告管理
    </h2>
    <ul style="display: block">
        <li><a href="<c:url value="/public/admininform.jsp"/>" target="right"><span
                class="icon-caret-right"></span>公告</a></li>
    </ul>




    <h2>
        <span class="icon-user"></span>设备管理
    </h2>
    <ul style="display: block">
        <li><a href="<c:url value="/admin/statistics.jsp"/>" target="right"><span
                class="icon-caret-right"></span>数据管理</a></li>

        <li><a href="<c:url value="/admin/jinku.jsp"/>" target="right"><span
                class="icon-caret-right"></span>设备进库</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/fenye.action?page=1" target="right"><span
                class="icon-caret-right"></span>设备出库</a></li>
<%--        <li><a href="${pageContext.servletContext.contextPath}/admin/caigou.action" target="right"><span--%>
<%--                class="icon-caret-right"></span>需要采购设备</a></li>--%>
        <li><a href="${pageContext.servletContext.contextPath}/admin/findAll.action" target="right"><span
                class="icon-caret-right"></span>学院所需设备</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/findAddfacilityByPlan.action" target="right"><span
                class="icon-caret-right"></span>查看安装设备信息</a></li>
<%--    </ul>--%>


<%--    <h2>--%>
<%--        <span class="icon-user"></span>设备维修管理--%>
<%--    </h2>--%>
<%--    <ul style="display: block">--%>
        <li><a href="${pageContext.servletContext.contextPath}/admin/chaKanWeiXiuXinXi.action" target="right"><span
                class="icon-caret-right"></span>维修设备审批</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/chaKanYiXiuXinXi.action" target="right"><span
                class="icon-caret-right"></span>查看维修设备信息</a></li>

          <li><a href="${pageContext.servletContext.contextPath}/admin/deleteFacility.action" target="right"><span
                class="icon-caret-right"></span>报废设备审批</a></li>
         <li><a href="${pageContext.request.contextPath}/worker/findAlreadyDelete.action" target="right"><span
                class="icon-caret-right"></span>查看报废的设备信息</a></li>

    </ul>

    <h2>
        <span class="icon-user"></span>用户管理
    </h2>
    <ul style="display: block">
        <li><a href="${pageContext.servletContext.contextPath}/admin/findWork.action" target="right"><span
                class="icon-caret-right"></span>维修人员管理</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/findTeacher.action" target="right"><span
                class="icon-caret-right"></span>老师管理</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/admin/findAdmin.action" target="right"><span
                class="icon-caret-right"></span>管理员管理</a></li>
    </ul>

</div>
<script type="text/javascript">
    $(function() {
        $(".leftnav h2").click(function() {
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        })
        $(".leftnav ul li a").click(function() {
            $("#a_leader_txt").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        })
    });
</script>
<ul class="bread">
    <li><a href="##" id="a_leader_txt">首页</a></li>

</ul>
<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="${pageContext.servletContext.contextPath}/admininform.action"
            name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align: center;"></div>

</body>
</html>
