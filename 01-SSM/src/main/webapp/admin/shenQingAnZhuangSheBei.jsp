<%--
  Created by IntelliJ IDEA.
  User: 19277
  Date: 2022/3/11
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学院所需设备</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            max-width: 1200px;
            margin: 50px auto;
            padding: 30px;
            background-color: #ffffff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .table {
            margin-top: 20px;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .pagination {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center mb-4">设备管理</h1>

    <div class="row mt-4">
        <div class="col-12">
            <form id="myform" action="${pageContext.servletContext.contextPath}/admin/findAll.action" method="get">
                <input id="page" type="hidden" name="page" value="${info.pageNum}">
            </form>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-12">
            <table class="table table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">编号</th>

                    <th scope="col">设备名称</th>
                    <th scope="col">设备位置</th>
                    <th scope="col">详情</th>
                    <th scope="col">提交时间</th>
                    <th scope="col">处理进度</th>
                    <th scope="col">申请人详情</th>
                    <th scope="col">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${info.list}" var="c" varStatus="a">
                    <tr>
                        <td>${(info.pageNum - 1) * info.pageSize + a.index + 1}</td>

                        <td>${c.fname}</td>
                        <td>${c.location}</td>
                        <td>${c.test}</td>
                        <td>${c.createtime}</td>
                        <td>${c.plan}</td>
                        <td>
                            <button type="button" class="btn btn-info btn-sm" onclick="showUserDetail(${c.uid})">查看详情</button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="anzhuang('${c.fname}', '${c.id}','${c.location}')">申请安装通过</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="mb-3 text-center">
                总共: ${info.pages} 页, 当前第: ${info.pageNum} 页
            </div>
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <li class="page-item ${!info.hasPreviousPage ? 'disabled' : ''}">
                        <a class="page-link" href="#" onclick="ajax(${info.prePage}); return false;">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${info.pages}" var="i">
                        <li class="page-item ${info.pageNum == i ? 'active' : ''}">
                            <a class="page-link" href="#" onclick="ajax(${i}); return false;">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${!info.hasNextPage ? 'disabled' : ''}">
                        <a class="page-link" href="#" onclick="ajax(${info.nextPage}); return false;">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>

<!-- 提示信息模态框 -->
<div class="modal fade" id="dialogModal" tabindex="-1" role="dialog" aria-labelledby="dialogModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="dialogModalLabel">提示信息</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="dialogModalBody">
                <!-- 提示信息内容将动态插入到这里 -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 用户详情模态框 -->
<div class="modal fade" id="userDetailModal" tabindex="-1" role="dialog" aria-labelledby="userDetailModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="userDetailModalLabel">申请人详情</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="text-center" id="userDetailLoading">
                    <div class="spinner-border text-primary" role="status">
                        <span class="sr-only">加载中...</span>
                    </div>
                    <p>正在加载用户信息...</p>
                </div>
                <div id="userDetailContent" style="display: none;">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title" id="userName">用户名: </h5>
                            <p class="card-text" id="userRole">用户类型: </p>
                            <p class="card-text" id="userId">用户ID: </p>
                        </div>
                    </div>
                </div>
                <div id="userDetailError" class="alert alert-danger" style="display: none;"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script>
    function ajax(page) {
        $("#page").val(page);
        $("#myform").submit();
    }

    function anzhuang(fname, id, location) {
        window.location.href = "${pageContext.servletContext.contextPath}/admin/anzhuang.action?fname=" + fname + "&id=" + id + "&location=" + encodeURIComponent(location);
    }
    
    function showUserDetail(uid) {
        // 重置模态框状态
        $('#userDetailLoading').show();
        $('#userDetailContent').hide();
        $('#userDetailError').hide();
        
        // 显示模态框
        $('#userDetailModal').modal('show');
        
        // 发送AJAX请求获取用户详情
        $.ajax({
            url: "${pageContext.servletContext.contextPath}/admin/getUserDetail.action",
            type: "GET",
            data: { uid: uid },
            dataType: "json",
            success: function(response) {
                $('#userDetailLoading').hide();
                
                if (response.success) {
                    // 成功获取用户信息，显示在模态框中
                    var user = response.user;
                    
                    $('#userName').text("用户名: " + user.uname);
                    
                    var roleText = "未知";
                    switch(user.rols) {
                        case 1:
                            roleText = "管理员";
                            break;
                        case 2:
                            roleText = "维修人员";
                            break;
                        case 3:
                            roleText = "教师";
                            break;
                        case 4:
                            roleText = "学生";
                            break;
                    }
                    
                    $('#userRole').text("用户类型: " + roleText);
                    $('#userId').text("用户ID: " + user.uid);
                    
                    $('#userDetailContent').show();
                } else {
                    // 显示错误信息
                    $('#userDetailError').text(response.message || "获取用户信息失败").show();
                }
            },
            error: function(xhr, status, error) {
                $('#userDetailLoading').hide();
                $('#userDetailError').text("请求失败: " + error).show();
            }
        });
    }

    $(document).ready(function() {
        // 获取 msg 的值
        var msg = "${msg}";

        // 判断 msg 的值并显示提示信息
        if (msg) {
            $("#dialogModalBody").text(msg);
            $('#dialogModal').modal('show');
        }
    });
</script>
</body>
</html>