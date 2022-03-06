<%--
  Created by IntelliJ IDEA.
  User: Administrator
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人信息</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/personal.css">
    <style>
        body,html{background-color: #F5F5F5;display: flex;min-height: 100vh;flex-direction: column;}
        *{margin: 0;padding: 0;}
        .mag_top{margin-top: 10px;}
        .author{border-radius: 50%; border: 2px solid #FF8D7B;background-color: #FF8D7B;}
        .second{font-weight: 700; color: black;font-size: 18px;}
        header{background: #ffffff;}
        .footer_span{height: 40px;;text-align: center;line-height: 40px;}
        footer{min-height: 80px;background-color: #434343;}
        #footer{background-color: #434343;}
        .book_img{width: 100px;height: 130px;}
        #center{flex: 1;}
        a:hover{text-decoration: none;}
        .choose{color: red;}
    </style>
    <script>
        var _age;

        var ageMess = "";
        window.onload = function() {
            _age = document.getElementById("age");

        }
        function checkForm() {          //验证表单
            var _checkAge = checkAge();
            return _checkAge ;    //return false后, 事件将被取消
        }
        function checkAge() {       //验证用户年龄
            var regex = /^([1-9]|[1-9][0-9]?|1[01][0-9]|120)$/;
            var value = _age.value;

            if (!regex.test(value)) {
                ageMess = "年龄不合法";
                _age.value = ageMess;
            }
            return ageMess == "";
        }

    </script>
</head>
<body>
<header class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="top1" style="margin-top: 12px; margin-left: 0px; height: 48px;">
                <a class="btn" href="javascript:void(0);" role="button"><span style="font-weight: 700;font-size: 20px;">${user.getAccount() }</span></a>
                <a class="btn" href="${pageContext.request.contextPath}/LogOut" role="button"><span style="font-weight: 700;font-size: 20px;">注销</span></a>
            </div>
        </div>
    </div>
    <div class="row second">
        <nav class="navbar navbar-default" style="background-color: #ffffff;">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/user/index_2.jsp"><span>首页</span></a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/QueryNovelByPage">书库</a></li>
                        <li><a href="${pageContext.request.contextPath}/NovelRank">排行</a></li>
                        <li><a href="${pageContext.request.contextPath}/QueryNovelShelfByPage">书架</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/QueryUser">个人信息</a></li>
                        <li>
                        <c:if test="${user.getAccount() == 'admin'}">
                            <li>
                                <a href="${pageContext.request.contextPath}/QueryUserByPage">管理员界面</a>
                            </li>
                        </c:if>
                        </li>
                    </ul>
                    <!-- 搜索框 -->
                    <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/QueryNovelByPage" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="关键词、书名、作者名" name="_param" value="${_param }">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div>
</header>

<div class="container" style="background-color: #FAFAFA;font-size: 17px;" id="center">
    <form action="${pageContext.request.contextPath }/UpdateUser" method="post" onsubmit="return checkForm();">
        <div class="col-xs-4 col-md-5 col-center-block">
            <div class="same">
                <label for="um">账号</label>
                <input id="um" name="account" type="text" value="${u.getAccount() }" readonly>
            </div>
            <div class="same">
                <%--@declare id="meal"--%><label for="meal">性别</label>
                <c:if test="${u.getSex() =='男'}">
                    <input id="male" type="radio" name="sex" value="男" checked="checked"><label for="male" class="label_1">男</label>
                    <input id="female" type="radio" name="sex" value="女"><label for="female" class="label_1">女</label>
                </c:if>
                <c:if test="${u.getSex() =='女'}">
                    <input id="male" type="radio" name="sex" value="男"><label for="male" class="label_1">男</label>
                    <input id="female" type="radio" name="sex" value="女" checked="checked"><label for="female" class="label_1">女</label>
                </c:if>
            </div>
            <div class="same">
                <%--@declare id=""--%><label for="">年龄</label>
                <input id="age" type="text" value="${u.getAge() }" name="age">
            </div>
            <div class="info_1">
                <%--@declare id="info"--%><label for="info">自我介绍</label>
                <textarea class="form-control" rows="3" name="introduce">${u.getIntroduce() }</textarea>
            </div>
            <div class="mag_top">
                <button type="submit" class="btn btn-default" style="margin-left: 180px;background-color: turquoise;">提交</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
