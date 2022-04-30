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
    <%--<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js" integrity="sha384-IjeXbuVdL81ilB5LykkImU8JN0WPja/i9uZAt2qjo2TnYk9NJ2MPfN3vzMH0R8n3" crossorigin="anonymous"></script>--%>
    <%--<script src="../js/jquery.slim.min.js"/>--%>
    <%--<script src="../js/popper.min.js"/>--%>
    <%--<script src="../js/bootstrap.min.js"/>--%>
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
            <p>设备添加失败，请输入进库设备的数量</p>
        </div>
        <h1>添加进库设备信息</h1>
        <form action="${pageContext.servletContext.contextPath}/admin/addShebei.action" method="post">
            <div class="input-group mb-3">
                <table style="margin-left: 360px">
                    <tr>
                    <td><p><h5>设备编号:</h5></p></td>
                    <td> <p></p>
                        <input type="text" name="ftid" class="form-control" placeholder="设备编号" aria-label="Username" aria-describedby="basic-addon1"></td>
                </tr>
                    <tr>
                        <td><p><h5>设备名称:</h5></p></td>
                        <td> <p></p>
                            <input type="text" name="fname" class="form-control" placeholder="设备名称"  aria-describedby="basic-addon1"></td>
                    </tr>
                    <tr>
                        <td><p><h5>入库时间:</h5></p></td>
                        <td> <p></p>
                            <input type="date" name="ftime" class="form-control"  aria-describedby="basic-addon1"></td>
                    </tr>
                    <tr>
                        <td><p><h5>设备数量:</h5></p></td>
                        <td> <p></p>
                            <input type="number" name="fnum" class="form-control" aria-describedby="basic-addon1" min="0"></td>
                    </tr>
                    <tr>
                        <td><p><h5>设备类型:</h5></p></td>
                        <td>
                            <select name="ftype"  class="form-control">
                                <option value="-1">--请选择--</option>
                                <option value="1">电脑</option>
                                <option value="2">多媒体黑板</option>
                                <option value="3">空调</option>
                                <option value="4">其他</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><p><h5>设备厂家:</h5></p></td>
                        <td> <p></p>
                            <input type="type" name="ffctory" class="form-control"  aria-describedby="basic-addon1" min="0"></td>
                    </tr>
                    <tr>
                        <td><p><h5>备注:</h5></p></td>
                        <td>
                            <textarea name="ftrange" class="form-control"></textarea>
                           </td>
                    </tr>


                    <tr>
                        <td colspan="2" align="center" style="padding-top: 10px">
                            <input  type="submit" class="btn btn-primary" value="添加设备">
                        </td>
                    </tr>

                </table>
            </div>
        </form>
    </center>
</body>
</html>
