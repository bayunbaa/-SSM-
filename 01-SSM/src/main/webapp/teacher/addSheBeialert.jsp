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
    <link rel="stylesheet" href="../css/jquery-ui.min.css">
    <script src="../js/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="../js/popper.min.js"integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="../js/bootstrap.min.js" integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3" crossorigin="anonymous"></script>
    <script src="../js/jquery-3.6.0.min.js"></script>
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
        // layer.confirm("恭喜你，秒杀成功！查看订单？", {btn: ["确定"]},
        //     function () {
        //         layer.closeAll();
        //     });

    })
</script>


<body >
<center>
    <div id="dialog" title="提示信息">
        <p>申请添加设备提交成功</p>
    </div>
    <h1>申请安装设备</h1>
    <form action="${pageContext.servletContext.contextPath}/teacher/addSheBei.action" method="post">
        <div class="input-group mb-3">
            <table style="margin-left: 360px">
                <tr>
                    <td><p><h5>设备名称:</h5></p></td>
                    <td> <p></p>
                        <input type="text" name="fname" class="form-control" placeholder="设备名称" aria-label="Username" aria-describedby="basic-addon1"></td>
                </tr>
                <tr>
                    <td><p><h5>设备位置:</h5></p></td>
                    <td> <p></p>
                        <input type="text" name="location" class="form-control" placeholder="如:303教室"  aria-describedby="basic-addon1"></td>
                </tr>
                <tr>
                    <td><p><h5>申请原因:</h5></p></td>
                    <td>
                        <textarea name="test" class="form-control"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center" style="padding-top: 10px">
                        <input  type="submit" class="btn btn-primary" value="提交申请">
                    </td>
                </tr>

            </table>
        </div>
    </form>
</center>
</body>
<script>

</script>
</html>
