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
    <title>注册</title>
    <style>
        *{margin: 0;padding: 0;}
        #bg_img{position: fixed; right: 0; bottom: 0; min-width: 100%; min-height: 100%;z-index: -100;}
        #regist {width: 460px; height: 600px;margin: auto;margin-top: 10px; background-color: rgba(36, 36, 36, 0.36);}
        #regist h1 {font-size: 30px;color: #FF8150;margin-left: 170px;}
        #logo {height: 100px;width: 100px;}
        .id_number {height: 35px;width: 240px;background-color: rgba(255,255,255,0.3);border: 1px solid rgba(255,255,255,0.5);}
        .password_number {height: 35px;width: 240px;background-color: rgba(255,255,255,0.3);border: 1px solid rgba(255,255,255,0.5);}
        #write{width: 240px; height: 100px;border: 1px solid rgba(255,255,255,0.5);background-color: rgba(255,255,255,0.3);margin-top: 10px;}
        #regist_submit {font-size: 16px;background: #FF7F50;width:  240px;height: 35px;border: none;margin-top: 5px;}
        #register {font-size: 15px;margin-left: 100px; margin-top: 15px;}
        input{margin-top: 10px;}
        a{text-decoration: none; color: black;}
        form{margin-left: 100px;}
        input{padding: 5px;}
    </style>
    <script>
        var _account;
        var _passWord;
        var _rePassWord;
        var _age;

        var accountMess;
        var passWordMess;
        var rePassWordMess;
        var ageMess;
        window.onload = function() {
            _account = document.getElementById("_account");
            _passWord = document.getElementById("_passWord");
            _rePassWord = document.getElementById("_rePassWord");
            _age = document.getElementById("_age");

            accountMess = document.getElementById("accountMess");
            passWordMess = document.getElementById("passWordMess");
            rePassWordMess = document.getElementById("rePassWordMess");
            ageMess = document.getElementById("ageMess");
        }
        function checkForm() {          //验证表单
            var _checkAccount = checkAccount();
            var _checkPassWord = checkPwd();
            var _checkRep = checkRePwd();
            var _checkAge = checkAge();
            return _checkAccount && _checkPassWord && _checkRep && _checkAge ;    //return false后, 事件将被取消
        }
        function checkAccount() {       //验证用户账号
            var regex = /^.{6,16}$/;
            var value = _account.value;
            var mess = "";
            if(!value) {
                mess = "用户账号不能为空";
            }
            if(!regex.test(value)) {
                mess = "用户账号必须为任意字符6-16位"
            }
            accountMess.innerHTML = mess;
            return mess == "";
        }
        function checkPwd() {       //验证用户密码
            var regex = /^.{6,16}$/;//任意字符6-16位
            var value = _passWord.value;
            var mess = "";
            if(!value) {
                mess = "用户密码不能为空";
            }
            if(!regex.test(value)) {
                mess = "用户密码必须为任意字符6-16位"
            }
            passWordMess.innerHTML = mess;
            return mess == "";
        }
        function checkRePwd() {       //验证用户确认密码
            var value1 = _passWord.value;
            var value2 = _rePassWord.value;
            var mess = "";
            if(!value2) {
                mess = "确认密码不能为空";
            }
            if(value1!=value2) {
                mess = "两次输入的密码不一致";
            }
            rePassWordMess.innerHTML = mess;
            return mess == "";
        }
        function checkAge() {       //验证用户年龄
            var regex = /^([1-9]|[1-9][0-9]?|1[01][0-9]|120)$/;
            var value = _age.value;
            var mess = "";
            if (!regex.test(value)) {
                mess = "年龄不合法";
            }
            ageMess.innerHTML = mess;
            return mess == "";
        }
    </script>
</head>
<body>
    <div id="regist">
        <span style="font-size: 60px; color: #FF8150;">注 册</span>
    <hr>
    <div>
        <form action="${pageContext.request.contextPath }/AddUser" method="post" onsubmit="return checkForm();">
            <input type="text" class="id_number" id="_account" placeholder="账号" maxlength="16" style="font-size: 18px;"  onkeyup="checkAccount();" name="account">
            <span id="accountMess" style="width: 100px;"></span>
            <br>
            <input type="password" class="password_number" id="_passWord" placeholder="密码" maxlength="16" style="font-size: 18px;" onkeyup="checkPwd();" name="passWord">
            <span id="passWordMess" style="width: 100px;"></span>
            <br>
            <input type="password" class="password_number" id="_rePassWord" placeholder="重复密码" maxlength="16" style="font-size: 18px;" onkeyup="checkRePwd();" name="_rePassWord">
            <span id="rePassWordMess" style="width: 100px;"></span>
            <div>
                性别:
                <input type="radio" name="sex" id="man" value="男">男
                <input type="radio" name="sex" id="woman" value="女">女
            </div>
            <input type="text" class="id_number" id="_age" placeholder="年龄" maxlength="3" style="font-size: 18px;" onkeyup="checkAge();" name="age">
            <span id="ageMess" style="width: 100px;"></span>
            <br>
            <textarea id="write" cols="35" rows="10" id="_introduction" placeholder="请输入个人说明:" style="font-size: 18px;" name="introduce"></textarea>
            <br>
            <div>
                <input type="submit" id="regist_submit" value="注册">
            </div>
        </form>
        <div id="register">
            <span style="font-size: 18px;">已有账号?马上<a href="${pageContext.request.contextPath}/jsp/user/login.jsp"><b>登录</b></a></span>
        </div>
    </div>
</div>
</body>
</html>
