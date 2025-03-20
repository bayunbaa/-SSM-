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
    <script src="../js/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="../js/popper.min.js"integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="../js/bootstrap.min.js" integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3" crossorigin="anonymous"></script>
    <script src="../js/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="../js/popper.min.js"integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="../js/bootstrap.min.js" integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3" crossorigin="anonymous"></script>
    <script src="../js/jquery.slim.min.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <style>
        tr{
            height: 40px;
        }
    </style>
</head>

<body >
    <center>
        <h1>报修设备信息</h1>
        <form action="${pageContext.servletContext.contextPath}/student/baoxiu.action" method="post">
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
                            <input type="text" name="location" class="form-control" placeholder="如:9号楼3楼东洗衣机"  aria-describedby="basic-addon1"></td>
                    </tr>
                    <tr>
                        <td><p><h5>详情:</h5></p></td>
                        <td>
                            <textarea name="details" class="form-control"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center" style="padding-top: 10px">
                            <input  type="submit" class="btn btn-primary" value="提交报修">
                        </td>
                    </tr>

                </table>
            </div>
        </form>
    </center>
</body>
<section>

</section>
</html>
