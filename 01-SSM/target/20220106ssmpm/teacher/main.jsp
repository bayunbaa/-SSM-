<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 19277
  Date: 2022/2/21
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
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
    <title>教师后台管理中心</title>
    <link rel="stylesheet" href="<c:url value="/css/pintuer.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/admin.css"/>">
    <script src="<c:url value="/js/jquery-3.6.0.min.js"/>"></script>
</head>
<body style="background-color: #f2f9fd;">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1 style="color: black;">
            <img src="${pageContext.servletContext.contextPath}/images/lao-shi.jpg"
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
        <strong><span class="icon-list"></span>菜单列表</strong>
    </div>

    <h2>
        <span class="icon-user"></span>公告管理
    </h2>
    <ul style="display: block">
        <li><a href="<c:url value="/public/inform.jsp"/>" target="right"><span
                class="icon-caret-right"></span>公告</a></li>
    </ul>
    <h2>
    <span class="icon-user"></span>设备维修管理
</h2>
    <ul style="display: block">
        <li><a href="${pageContext.servletContext.contextPath}/teacher/sheBeiBaoXiu.jsp" target="right"><span
                class="icon-caret-right"></span>报修设备</a></li>
        <li><a href="${pageContext.request.contextPath}/teacher/baoXiuJinDu.action" target="right"><span
                class="icon-caret-right"></span>查看报修进度</a></li>

    </ul>

    <h2>
        <span class="icon-user"></span>设备报废管理
    </h2>
    <ul style="display: block">

        <li><a href="${pageContext.servletContext.contextPath}/teacher/sheBeiBaoFei.jsp" target="right"><span
                class="icon-caret-right"></span>申请报废设备</a></li>
        <li><a href="${pageContext.request.contextPath}/worker/findAlreadyDelete.action" target="right"><span
                class="icon-caret-right"></span>查看报废的设备信息</a></li>
    </ul>

    <h2>
        <span class="icon-user"></span>设备安装管理
    </h2>
    <ul style="display: block">
        <li><a href="${pageContext.servletContext.contextPath}/teacher/addSheBei.jsp" target="right"><span
                class="icon-caret-right"></span>申请安装设备</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/teacher/addSheBeiJindu.action" target="right"><span
                class="icon-caret-right"></span>查看申请安装设备进度</a></li>
    </ul>


    <h2>
        <span class="icon-user"></span>用户管理
    </h2>
    <ul style="display: block">
        <li><a href="${pageContext.servletContext.contextPath}/teacher/teacherEdit.jsp" target="right"><span
                class="icon-caret-right"></span>修改个人资料</a></li>
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
    <iframe scrolling="auto" rameborder="0" src="${pageContext.servletContext.contextPath}/inform.action"
            name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align: center;"></div>
</body>
</html>
