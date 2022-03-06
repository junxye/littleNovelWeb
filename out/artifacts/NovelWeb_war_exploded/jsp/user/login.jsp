<%--
  Created by IntelliJ IDEA.
  User: Administrator
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_regist.css">
    <style>
        *{margin: 0; padding: 0;}
        #login {width: 500px;margin-top: 120px;margin-right: auto;margin-left: auto;text-align: center;background-color: rgba(0, 0, 0, 0.36);}
        #logo {height: 100px;width: 100px; margin-top: 5px;}
        .id_number {height: 35px;width: 240px;background-color: rgba(255,255,255,0.4);border: 1px solid rgba(255,255,255,0.5); margin-top: 10px;}
        #password_number {height: 35px;width: 240px;background-color: rgba(255,255,255,0.4);border: 1px solid rgba(255,255,255,0.5);}
        #login_submit {width:  242px;height: 35px;font-size: 16px;background: #FF7F50;border: none;margin-top: 22px;}
        #span_for {margin-left:-70px; margin-top: 15px;}
        a{text-decoration: none; color: black;}
        #code{margin-left: -10px; width: 128px;}
        input{padding: 5px;}
    </style>
</head>
<body>
<div class="slideshow">
    <div id="login">
        <span style="font-size: 60px; color: #FF8150;">登 录</span>
        <br>
        <form action="${pageContext.request.contextPath }/LogIn" method="post">
            <input type="text" class="id_number" placeholder="请输入账号" maxlength="16" style="font-size: 18px;" name="account" required>
            <br><br>
            <input type="password" id="password_number" placeholder="请输入密码" maxlength="16" style="font-size: 18px;" name="passWord" required>
            <br>
            <div>
                <input type="submit" id="login_submit" value="登录">
            </div>
            <div id="span_for">
                <span style="font-size: 18px;">还没有账号?马上<a href="${pageContext.request.contextPath  }/jsp/user/register.jsp"><b>注册</b></a></span>
            </div>
            <br>
            <!-- 出错显示的信息框 -->
            <div class="alert alert-warning alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" >
                    <span>&times;</span>
                </button>
                <strong>
                    <c:if test="${loginMessage==nul }">
                        您还未登录，请登录!
                    </c:if>
                    <c:if test="${loginMessage!=nul }">
                        ${loginMessage }
                    </c:if>
                </strong>
            </div>
        </form>
    </div>
</div>
</body>
</html>
