<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 19277
  Date: 2022/2/20
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>
    <link rel="stylesheet" href="./css/pintuer.css">
    <link rel="stylesheet" href="./css/admin.css">
    <script src="<c:url value="/js/jquery-3.6.0.min.js"/>"></script>
    <script src="<c:url value="/js/pintuer.js"/>"></script>
</head>
<body >
<div class="bg" ></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">
            </div>
            <form action="${pageContext.servletContext.contextPath}/mainJsp.action" method="post">
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top"><h1>学校设备管理系统</h1></div>
                    <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <!--
                                    data-validate="required:请填写账号"
                                    验证用的， required 要求此输入框为必填，在未填写的情况，验证不通过   如果不输入显示 请填写账号
                                 -->
                                <input type="text" class="input input-big" name="uname" placeholder="登录账号" data-validate="required:请填写账号" />
                                <span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input input-big" name="upassword" placeholder="登录密码" data-validate="required:请填写密码" />
                                <span class="icon icon-key margin-small"></span>
                            </div>
                        </div>
                        <br>

                    </div>
                    <span style="color: red; margin-left: 30px">${msg}</span>
                    <div style="padding:30px;"><input type="submit" class="button button-block bg-main text-big input-big" value="登录">
                        <br/>
                        <input type="button" class="button button-block bg-main text-big input-big" id="zhuce" value="注册">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script>
    $(function(){
        $("#zhuce").click(function(){
            // 点击注册按钮跳到相应的注册页面
            window.location.href="./register.jsp"
        })
    })

</script>