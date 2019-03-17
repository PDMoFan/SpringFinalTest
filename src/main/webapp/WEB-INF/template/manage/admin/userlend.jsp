<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/18
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="iframe-h">

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>网站后台管理</title>
    <link rel="stylesheet" type="text/css" href="../../static/layuiAdmin-master/static/admin/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="../../static/layuiAdmin-master/static/admin/css/admin.css" />
</head>

<body>
<div class="wrap-container clearfix">
    <div class="column-content-detail">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <a class="layui-btn layui-btn-normal" href="../usermanage" >返回用户管理首页</a>
            </div>
        </form>
        <div class="layui-form" id="table-list">
            <table class="layui-table" lay-even lay-skin="nob">
                <colgroup>
                    <col width="50">
                    <col class="hidden-xs" width="50">
                    <col class="hidden-xs" width="100">
                    <col>
                    <col class="hidden-xs" width="150">
                    <col class="hidden-xs" width="150">
                    <col width="80">
                    <col width="150">
                </colgroup>
                <thead>
                <tr>

                    <th class="hidden-xs">用户ID</th>
                    <th class="hidden-xs">用户名</th>
                    <th class="hidden-xs">书籍ID</th>
                    <th>书名</th>
                    <th>还书</th>
                    <th class="hidden-xs">借阅日期</th>
                    <th class="hidden-xs">归还日期</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <c:forEach items="${user.books}" var="book">
                        <tr>

                            <td class="hidden-xs">${user.id}</td>
                            <td class="hidden-xs">${user.name}</td>

                            <td class="hidden-xs">${book.id}</td>
                            <td>${book.name}</td>
                            <td><a class="layui-btn" href="../comeback/${book.id}&${user.id}">归还</a> </td>
                            <td class="hidden-xs">${book.start_date}</td>
                            <td class="hidden-xs">${book.deadline}</td>


                        </tr>
                    </c:forEach>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>
<script src="../../static/layuiAdmin-master/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../../static/layuiAdmin-master/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
</body>

</html>