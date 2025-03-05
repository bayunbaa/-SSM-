<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>公告</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        pre {
            white-space: pre-wrap;  /* 保留换行和空格 */
            word-wrap: break-word;  /* 长单词换行 */
            background-color: #f4f4f4;
            padding: 15px;
            border-radius: 5px;
            text-align: left;  /* 左对齐 */
            max-width: 80%;
            margin: 0 auto;
            line-height: 1.6;
        }
    </style>
</head>
<body>
    <div style="text-align: center;">
        <h2>公告</h2>
        <!-- 使用pre标签保留原始格式 -->
        <pre>${inform.ibody != null ? inform.ibody : "暂无公告内容"}</pre>
        <br/>
        <a href="${pageContext.servletContext.contextPath}/public/editinform.jsp">编辑内容</a>
    </div>
</body>
</html>