<%--
  Created by IntelliJ IDEA.
  User: 19277
  Date: 2022/3/11
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <style>
        tr{
            height: 40px;
        }
    </style>
</head>
<script>
    $(function() {
        $( "#dialog" ).dialog();


    })
</script>

<body >
    <center>
        <div id="dialog" title="提示信息">
            <p>用户名已存在</p>
        </div>
        <h1>添加维修人员账号</h1>
        <form id="f1" action="${pageContext.servletContext.contextPath}/admin/addWorker.action" method="post">
            <div class="input-group mb-3">
                <table style="margin-left: 360px">
                    <tr>
                        <td><p><h5>用户名:</h5></p></td>
                        <td> <p></p>
                            <input type="text" name="uname" id="uname" onchange="ajaxUname()"   class="form-control"  aria-label="Username" aria-describedby="basic-addon1"></td>
                    </tr>
                    <tr>
                        <td><p><h5>密码:</h5></p></td>
                        <td> <p></p>
                            <input type="password" name="upassword" id="upassword1" class="form-control" placeholder="密码"   aria-describedby="basic-addon1"></td>
                    </tr>
                    <tr>
                        <td><p><h5>确认密码:</h5></p></td>
                        <td> <p></p>
                            <input type="password" id="password2" name="location" class="form-control" placeholder="确认密码"  aria-describedby="basic-addon1"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><span id="s3" style="color: red"></span></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center" style="padding-top: 10px">
                            <button type="button" class="btn btn-primary" onclick="tijiao()">修改密码</button>
                            <%--<input  type="submit" class="btn btn-primary" value="修改密码">--%>
                        </td>
                    </tr>

                </table>
            </div>
        </form>
    </center>
</body>
<script>

    function ajaxUname() {
        $("#s3").empty();
        var uname = $("#uname").val();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/admin/ajaxUname.action",
            data: {
                'uname': uname
            },
            success: function(data){

                if (data!=null){
                    $("#s3").html("用户名重复!");
                }
            }
        });


    }

    function tijiao() {
        var s3 = $("#s3").val();
        if (s3.length > 0){
            return false;
        }

        $("#s3").empty();
        var password = $("#upassword1").val();
        var password2 = $("#password2").val();
        if (password==null ||password == ''){
            $("#s3").html("密码不能为空!");
            return;
        }
        if (password2==null ||password2 == ''){
            $("#s3").html("确认密码不能为空!");
            return;
        }
        if (password!=password2){
            $("#s3").html("两次密码不一致!");
            return;
        }
        $("#f1").submit();

    }

</script>
</html>
