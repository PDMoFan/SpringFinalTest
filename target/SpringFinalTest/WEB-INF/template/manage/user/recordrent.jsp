<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/18
  Time: 20:43
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
    <title>用户图书管理</title>
    <link rel="stylesheet" type="text/css" href="../static/layuiAdmin-master/static/admin/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="../static/layuiAdmin-master/static/admin/css/admin.css" />
</head>

<body class="iframe-h gray-bg">
<div class="wrap-container column-wrap clearfix">
    <div class="column-edge">
        <ul class="layui-nav layui-nav-tree layui-nav-no-bg" lay-filter="column">
            <li class="layui-nav-item">
                <a href="./index">所有图书</a>
            </li>
            <li class="layui-nav-item layui-this">
                <a href="./recordrent">借阅图书记录</a>
            </li>

        </ul>

    </div>

    <div class="column-content">
        <div class="column-content-detail">
            <h6 style="text-align:center;font-size: large;"><strong>已借阅书籍</strong></h6>
            <p style="color: #00a0e9;font-size: large;text-align: right" >欢迎尊敬的${tuser.name}用户</p>
            <p style="font-size: large;text-align: right;color: #2B2E37" ><a href="./cancel">注销</a> </p>

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
                        <th></th>
                        <th class="hidden-xs">书名</th>
                        <th class="hidden-xs">借阅日期</th>
                        <th>&nbsp;&nbsp;归还日期</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${users}" var="user">
                        <c:forEach items="${user.books}" var="book">
                            <tr>

                                <td class="hidden-xs">${book.id}</td>
                                <td></td>
                                <td class="hidden-xs">${book.name}</td>
                                <td>${book.start_date}</td>
                                <td>${book.deadline}</td>

                                <td>
                                    <div class="layui-inline">
                                        <c:if test="${book.deadline==null}">
                                            <a class="layui-btn layui-btn-lg layui-btn-normal" href="./comeback/${book.id}">归还</a>
                                        </c:if>

                                            <%--  <a class="layui-btn layui-btn-mini layui-btn-normal edit-btn" data-id="1" href="./editbook/${user.id}"><i class="layui-icon">&#xe642;</i></a>
                                              <a class="layui-btn layui-btn-mini layui-btn-danger del-btn" data-id="1"  href="./deletebook/${user.id}"><i class="layui-icon">&#xe640;</i></a>
      --%>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:forEach>

                    </tbody>
                </table>


            </div>

        </div>
    </div>

</div>
<script src="../static/layuiAdmin-master/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../static/layuiAdmin-master/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>

</body>

</html>