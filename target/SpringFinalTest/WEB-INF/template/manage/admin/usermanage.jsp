<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/16
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="iframe-h">

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>网站后台管理</title>
    <link rel="stylesheet" type="text/css" href="../static/layuiAdmin-master/static/admin/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="../static/layuiAdmin-master/static/admin/layui/layui.js" />
    <link rel="stylesheet" type="text/css" href="../static/layuiAdmin-master/static/admin/css/admin.css" />
</head>

<body class="iframe-h gray-bg">
<div class="wrap-container column-wrap clearfix">
    <div class="column-edge">
        <ul class="layui-nav layui-nav-tree layui-nav-no-bg" lay-filter="column">
            <li class="layui-nav-item">
                <a href="./index">图书管理</a>
            </li>
            <li class="layui-nav-item layui-this">
                <a href="./usermanage">用户管理</a>
            </li>
        </ul>

    </div>

    <div class="column-content">
        <div class="column-content-detail">
            <h6 style="text-align:center;font-size: large;"><strong>用户管理</strong></h6>
            <p style="color: #00a0e9;font-size: large;text-align: right" >欢迎尊敬的${admin.name}管理员</p>
            <p style="font-size: large;text-align: right;color: #2B2E37" ><a href="./cancel">注销</a> </p>
            <div class="layui-form-item">
                <div class="layui-inline tool-btn">
                    <a class="layui-btn layui-btn-small layui-btn-normal addBtn" href="./adduser" ><i class="layui-icon">&#xe654;</i></a>

                    <%--onclick="window.location.href='/page2'"--%>
                </div>
                <form action="./searchuser" method="post">
                    <p style="font-size: large;color: #00b5f9">
                        请输入要查看的用户的id：
                    </p>
                    <div class="layui-inline">
                        <input type="text" name="id" lay-verify="required" placeholder="请输入用户id" autocomplete="off" class="layui-input">
                    </div>

                    <button class="layui-btn layui-btn-normal" type="submit">搜索</button>
                </form>
            </div>
            <form action="./deleteusers" method="post">
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
                            <%--<th><input type="checkbox" name="ids" lay-skin="primary" lay-filter="allChoose"></th>--%>
                            <th><button class="layui-btn layui-btn-small layui-btn-danger delBtn" type="submit"><i class="layui-icon">&#xe640;</i></button></th>
                            <th class="hidden-xs">ID</th>
                            <th>用户名</th>
                            <th class="hidden-xs">密码</th>
                            <th>查看借阅书籍</th>
                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>&nbsp;&nbsp;<input type="checkbox" name="ids" value="${user.id}" lay-skin="primary" data-id="1"></td>
                                <td class="hidden-xs">${user.id}</td>

                                <td>${user.name}</td>
                                <td class="hidden-xs">${user.password}</td>
                                <td><a href="./findbook/${user.id}" class="layui-btn layui-btn-xs">&nbsp;&nbsp;查询&nbsp;&nbsp;</a></td>
                                <td>
                                    <div class="layui-inline">
                                        <a class="layui-btn layui-btn-small layui-btn-normal edit-btn" data-id="1" href="./edituser/${user.id}"><i class="layui-icon">&#xe642;</i></a>
                                        <a class="layui-btn layui-btn-small layui-btn-danger del-btn" data-id="1"  href="./deleteuser/${user.id}"><i class="layui-icon">&#xe640;</i></a>
                                    </div>
                                </td>
                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>


                </div>
            </form>
            <p style="color: red">${delusermsg}</p>
        </div>
    </div>

</div>
<script src="../static/layuiAdmin-master/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../static/layuiAdmin-master/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>

</body>

</html>