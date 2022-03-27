<%--
  Created by IntelliJ IDEA.
  User: 19277
  Date: 2022/2/21
  Time: 14:43
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
</head>
<body>
    <center>
        <form action="${pageContext.servletContext.contextPath}/editinform.action" method="post">
            <textarea class="form-control" name="ibody" cols="30" rows="17" aria-label="With textarea">${body}</textarea>
            <%--<textarea name="ibody" cols="100" rows="30" >${body}--%>
            <%--</textarea>--%>
            <br/>
            <input type="submit" value="提交内容">
        </form>
    </center>
</body>
</html>
