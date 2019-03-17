<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/17
  Time: 20:46
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
        <form class="layui-form" action="../rentbook" method="post">
            <div class="layui-form-item">
                <a class="layui-btn layui-btn-normal" href="../index" >返回图书管理首页</a>
            </div>
                <p style="font-size: large;color: #00b5f9">
                    请输入要借阅该图书的用户的id：
                </p>
            <div class="layui-inline">
                <input type="text" name="bkid" lay-verify="required" placeholder="请输入要借阅图书的id" autocomplete="off" value="${bkid}" style="display: none">
                <input type="text" name="uid" lay-verify="required" placeholder="输入用户id" autocomplete="off" class="layui-input">
            </div>
            <button class="layui-btn layui-btn-normal" type="submit">借阅</button>
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

                    <th class="hidden-xs">ID</th>
                    <th class="hidden-xs">书名</th>
                    <th class="hidden-xs">用户ID</th>
                    <th>用户名</th>
                    <th class="hidden-xs">借阅日期</th>
                    <th class="hidden-xs">归还日期</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${books}" var="book">
                    <c:forEach items="${book.users}" var="user">
                <tr>

                    <td class="hidden-xs">${book.id}</td>
                    <td class="hidden-xs">${book.name}</td>

                    <td class="hidden-xs">${user.id}</td>
                    <td>${user.name}</td>

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