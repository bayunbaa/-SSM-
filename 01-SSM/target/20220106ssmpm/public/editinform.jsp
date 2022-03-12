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
</head>
<body>
    <center>
        <form action="${pageContext.servletContext.contextPath}/editinform.action" method="post">
            <textarea name="ibody" cols="100" rows="30" >
                ${body}
            </textarea>
            <br/>
            <input type="submit" value="提交内容">
        </form>
    </center>
</body>
</html>
