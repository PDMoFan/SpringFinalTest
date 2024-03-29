<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/9
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="../static/layuiAdmin-master/static/admin/layui/css/layui.css " />
    <link rel="stylesheet" type="text/css" href="../static/layuiAdmin-master/static/admin/css/login.css" />
</head>

<body>
<div class="m-login-bg">
    <div class="m-login">
        <h3>图书系统登录</h3>
        <div class="m-login-warp">
            <form class="layui-form" action="/test/tologin/login" method="post">
                <div class="layui-form-item">
                    <input type="text" name="id" required lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="text" name="password" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">

                        <input type="text" name="verity"  required lay-verify="required" placeholder="验证码" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline">
                        <%--src="../static/layuiAdmin-master/static/admin/images/login/yzm.jpg"--%>
                        <img class="verifyImg" onclick="this.src=this.src+'?c='+Math.random();" src="./captche" />
                    </div>
                </div>
                <div class="layui-form-item m-login-btn">
                    <div class="layui-inline">

                        <button class="layui-btn layui-btn-normal" lay-submit lay-filter="login">登录</button>
                    </div>
                    <div class="layui-inline">
                        <button type="reset" class="layui-btn layui-btn-primary">取消</button>
                    </div>
                </div>
            </form>
            <p style="color: #00b5f9;text-align: center" >
                <a href="./toRegister">注册&nbsp;&nbsp;&nbsp;&nbsp;</a>
                <a href="./tochangepass">&nbsp;&nbsp;&nbsp;&nbsp;忘记密码？</a>
            </p>


        </div>
        <p class="copyright">16级软件工程6班——林为国</p>
    </div>
</div>
<script src="../static/layuiAdmin-master/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form(),
            layer = layui.layer;


        //自定义验证规则
        form.verify({
            title: function(value) {
                if(value.length < 5) {
                    return '标题至少得5个字符啊';
                }
            },
            password: [/(.+){6,12}$/, '密码必须6到12位'],
            verity: [/(.+){6}$/, '验证码必须是6位'],

        });


        //监听提交
      /*  form.on('submit(login)', function(data) {
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;
        });*/

    });
</script>
</body>

</html>