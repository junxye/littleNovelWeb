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
    <meta charset="utf-8" >
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style>
        body{background-color: #F5F5F5;}
        *{margin: 0;padding: 0;}
        #logo{width: 120px;height: 60px;}
        .mag_top{margin-top: 10px;}
        .second{font-weight: 700; color: black;font-size: 18px;}
        .author{border-radius: 50%; border: 2px solid #FF8D7B;background-color: #FF8D7B;}
        .books{width: 120px;}
        .footer_span{height: 40px;;text-align: center;line-height: 40px;}
        header{background: #ffffff;}
        footer{background-color: #434343;}
    </style>
    <header class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="top1" style="margin-top: 12px; margin-left: 40px; height: 48px;">
                    <!--
                    <button class="btn btn-default author" type="submit">欢迎您来到书酷</button>
                    -->
                    <a class="btn " href="javascript:void(0);" role="button"><span style="font-weight: 700;font-size: 20px;">${user.getAccount() }</span></a>
                    <a class="btn " href="${pageContext.request.contextPath}/LogOut" role="button"><span style="font-weight: 700;font-size: 20px;">注销</span></a>
                </div>
            </div>
        </div>
        <div class="row second">
            <nav class="navbar navbar-default" style="background-color: #ffffff;">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/user/index_2.jsp"><span style="color: black;">首页</span></a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="${pageContext.request.contextPath}/QueryNovelByPage">书库</a></li>
                            <li><a href="${pageContext.request.contextPath}/NovelRank">排行</a></li>
                            <li><a href="${pageContext.request.contextPath}/QueryNovelShelfByPage">书架</a></li>
                            <li><a href="${pageContext.request.contextPath}/QueryUser">个人信息</a></li>
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
    <div class="container">
        <!-- 轮播图 -->
        <div class="row">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                </ol>
                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="${pageContext.request.contextPath}/img/bg/Carousel_1.png" alt="1" style="width: 100%;">
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/img/bg/Carousel_2.png" alt="2" style="width: 100%;">
                    </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                </a>
            </div>
        </div>
    </div>
    <div class="container mag_top">
        <div class="row">
            <img src="${pageContext.request.contextPath}/img/bg/frame_1.png" alt="frame_1" style="width: 100%;height: 50%;">
        </div>
    </div>
    <div class="container mag_top" style="background: #ffffff;">
        <div class="row">
            <div class="col-md-2">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=1"><img src="${pageContext.request.contextPath}/img/book/a1.jpg" alt="book_a1" class="books" style="height: 160px;"></a>
                    <div style="text-align: center;height: 35px;margin-top: 5px;">
                        <span><b>元尊 作者:天蚕土豆</b></span>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=16"><img src="${pageContext.request.contextPath}/img/book/b1.jpg" alt="book_b1" class="books" style="height: 160px;"></a>
                    <div style="text-align: center;height: 35px;margin-top: 5px;">
                        <span><b>九星毒奶 作者:育</b></span>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=31"><img src="${pageContext.request.contextPath}/img/book/c1.jpg" alt="book_c1" class="books" style="height: 160px;"></a>
                    <div style="text-align: center;height: 35px;margin-top: 5px;">
                        <span><b>凡人修仙之仙界篇 作者:忘语</b></span>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=46"><img src="${pageContext.request.contextPath}/img/book/d1.jpg" alt="book_d1" class="books" style="height: 160px;"></a>
                    <div style="text-align: center;height: 35px;margin-top: 5px;">
                        <span><b>都市极品医神 作者:风会笑</b></span>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=61"><img src="${pageContext.request.contextPath}/img/book/e1.jpg" alt="book_e1" class="books" style="height: 160px;"></a>
                    <div style="text-align: center;height: 35px;margin-top: 5px;">
                        <span><b>轮回乐园 作者:那一只蚊子</b></span>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=76"><img src="${pageContext.request.contextPath}/img/book/f1.jpg" alt="book_f1" class="books" style="height: 160px;"></a>
                    <div style="text-align: center;height: 35px;margin-top: 5px;">
                        <span><b>邪王追妻：废材逆天小姐 作者:苏小暖</b></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</head>
<body>

</body>
</html>
