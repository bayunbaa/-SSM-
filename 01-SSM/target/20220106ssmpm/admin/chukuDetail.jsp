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
    <%--<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js" integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3" crossorigin="anonymous"></script>--%>
    <%--<script src="../js/jquery.slim.min.js"/>--%>
    <%--<script src="../js/popper.min.js"/>--%>
    <%--<script src="../js/bootstrap.min.js"/>--%>
    <script src="../js/jquery-3.6.0.min.js"></script>
    <style>
        tr{
            height: 40px;
        }
    </style>
</head>

<body >
    <center>
        <h1>设备详情信息</h1>
        <form action="${pageContext.servletContext.contextPath}/admin/chukuback.action" method="post">
            <input type="hidden" name="ftime" value="${facility.ftime}">
            <input type="hidden" name="ftype" value="${facility.ftype}">
            <input type="hidden" name="ffctory" value="${facility.ffctory}">
            <input type="hidden" name="ftrange" value="${facility.ftrange}">
            <div class="input-group mb-3">
                <table style="margin-left: 360px">
                    <tr>
                        <td><p><h5>编号:</h5></p></td>
                        <td> <p></p>
                            <input type="text" name="id" class="form-control"  aria-label="Username" aria-describedby="basic-addon1" value="${facility.id}" readonly></td>
                    </tr>
                    <tr>
                    <td><p><h5>设备编号:</h5></p></td>
                    <td> <p></p>
                        <input type="text" name="ftid" class="form-control" aria-label="Username" aria-describedby="basic-addon1" value="${facility.ftid}" readonly></td>
                </tr>
                    <tr>
                        <td><p><h5>设备名称:</h5></p></td>
                        <td> <p></p>
                            <input type="text" name="fname" class="form-control" placeholder="设备名称"  aria-describedby="basic-addon1" value="${facility.fname}" readonly></td>
                    </tr>
                    <tr>
                        <td><p><h5>出库数量:</h5></p></td>
                        <td> <p></p>
                            <input type="number" name="fnum" id="fnum" class="form-control" aria-describedby="basic-addon1" min="0" max="${facility.fnum}"  value="0" ></td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center" style="padding-top: 10px">
                            <input  type="submit" class="btn btn-primary" value="出库">
                        </td>
                    </tr>

                </table>
            </div>
        </form>
    </center>
</body>
<script>
    $(function () {
       $("#fnum").change(function () {
           if ($("#fnum").val() >${facility.fnum}) {
               $("#fnum").val(${facility.fnum})
           }
           if ($("#fnum").val() < 0) {
               $("#fnum").val(1)
           }
       })
    });
</script>
</html>
