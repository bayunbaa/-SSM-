<%--
  Created by IntelliJ IDEA.
  User: 19277
  Date: 2022/3/20
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <!-- Meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- Style -->
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
    <script src="./js/jquery-3.6.0.min.js"></script>
</head>

<body>
<!-- Signup form -->
<section class="w3l-signup">
    <div class="overlay">
        <div class="wrapper">
            <div class="logo">
                <a class="brand-logo" href="index.html">用户注册</a>
            </div>
            <div class="form-section">
                <h3 id="hh3"></h3>
                <form action="#" method="post" class="signin-form">
                    <div class="form-input">
                        <input type="text" id="uname" onchange="nameYanzheng()"  name="uname" placeholder="用户名" required="" autofocus>
                        <span id="s1" style="color: red"></span>
                    </div>
                    <div class="form-input" hidden="500px">

                        <select id="rols"  name="rols" onchange="typeYanzheng()">
                            <option value="-1">请选择身份信息</option>
                            <option value="3">教师</option>
                            <option value="2">维修员</option>
                            <option value="1">管理员</option>
                        </select>
                        <span id="s2" style="color: red"></span>
                    </div>
                    <div class="form-input">
                        <input type="password"   id="upassword1" name="upassword" placeholder="密码" required="">
                        <span id="s3" style="color: red"></span>
                    </div>
                    <div class="form-input">
                        <input type="password" onchange="passwordYanzheng()" id="password2" name="password2" placeholder="确认密码" required="">
                        <span id="s4"  style="color: red"></span>
                    </div>
                    <button type="button" id="btn" onclick="btnYanzheng()"  class="btn btn-primary theme-button mt-4">注册</button>
                </form><p class="signup">去登录界面 <a href="${pageContext.request.contextPath}/login.jsp" class="signuplink">Login now</a></p>
            </div>
        </div>
    </div>
    <div id='stars'></div>
    <div id='stars2'></div>
    <div id='stars3'></div>


    <!-- //copyright -->
</section>

<!-- /Signup form -->
</body>
<script>
    /* 判断用户名 */
    function nameYanzheng() {
        var  uname = $("#uname").val();
        $("#s1").empty();
        if (uname == null||uname ==''){
            $("#s1").html("用户名不能为空!");
            return;
        }
        /* 去数据库判断这个用户名是否存在 */
        $("#s1").empty();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/${pageContext.request.contextPath}/register/ajaxByName.action",
            data: "uname="+uname,
            success: function(data){

                if (data.uname==uname){
                    $("#s1").html("用户名已存在!");
                }
            }
        });
    }


    /* 判断用户类型 */
    function typeYanzheng() {
        $("#s2").empty();
        var  rols = $("#rols").val();
        if (rols==-1){
            $("#s2").html("请选择身份类型")
        }

    }

    /* 验证密码 */
    function passwordYanzheng() {
        $("#s4").empty();
        $("#s3").empty();
        var password = $("#upassword1").val();
        //确认密码
        var password2 = $("#password2").val();
        if (password==null ||password == ''||password.length<1){
            $("#s3").html("密码不能为空!");
            return;
        }
        if (password2==null ||password2 == ''||password.length<1){
            $("#s4").html("密码不能为空!");
            return;
        }
        if (password!=password2){
            $("#s4").html("两次密码不一致!");

        }


    }

    /* 点击注册验证 */
    function btnYanzheng() {

        var s1 = $("#s1").text();
        var s2 = $("#s2").text();
        var s3 = $("#s3").text();
        var s4 = $("#s4").text();
        var password = $("#upassword1").val();
        //确认密码
        var password2 = $("#password2").val();
        if (password.length<1){
            $("#s3").html("密码不能为空!");
            return;
        }
        if (password2.length<1){
            $("#s4").html("确认密码不能为空!");
            return;
        }

        if (s1.length>0) {
            return;
        }
        if (s2.length>0) {
            return;
        }
        if (s3.length>0) {
            return;
        }
        if (s4.length>0) {
            return;
        }



        $.ajax({
            type: "POST",
            url: "http://localhost:8080/register/zhuce.action",
            data: {
                'uname':$("#uname").val(),
                'rols':$("#rols").val(),
                'upassword':$("#upassword1").val()
            },
            success: function(data){
                if (data!=null){
                    $("#hh3").html("注册成功");
                } else {
                    $("#hh3").html("注册失败");
                }

            }
       });


    }




</script>

</html>