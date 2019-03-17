<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/11
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
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
<div class="wrap-container">
    <form class="layui-form" style="width: 90%;padding-top: 20px;" action="../updatebook" method="post">

        <div class="layui-form-item" style="display:none;">
            <label class="layui-form-label">ID：</label>
            <div class="layui-input-block">
                <input type="text" name="id" required lay-verify="required" value="${book.id}" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">名称：</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required" value="${book.name}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">库存：</label>
            <div class="layui-input-block">
                <input type="text" name="inventory" required lay-verify="required" value="${book.inventory}" autocomplete="off" class="layui-input">
            </div>

        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">状态：</label>
            <div class="layui-input-block">
                <c:if test="${book.status==true}" >
                <input type="radio" name="status" value="true" title="可借阅" checked>
                <input type="radio" name="status" value="false" title="不可借阅">
                </c:if>
                <c:if test="${book.status==false}" >
                <input type="radio" name="status" value="true" title="可借阅" >
                <input type="radio" name="status" value="false" title="不可借阅" checked>
                </c:if>
            </div>

        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<p style="color: red">${updatebkmsg}</p>
<script src="../../static/layuiAdmin-master/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    //Demo
    layui.use(['form'], function() {
        var form = layui.form();
        form.render();
        //监听提交
        /*    form.on('submit(formDemo)', function(data) {
                layer.msg(JSON.stringify(data.field));
                return false;
            });*/
    });
</script>
</body>

</html>