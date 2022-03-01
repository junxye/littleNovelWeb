<%--
  Created by IntelliJ IDEA.
  User: Administrator
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Novel</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath }/css/toastr.min.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath }/js/toastr.min.js"></script>
    <script>
        $(function() {
            $("#comment_btn").click(function() {
                $("#comment_form").submit();
            });
            $("#bookshelfbtn").click(function() {
                toastr.options = {
                    closeButton: false, //是否显示关闭按钮
                    debug: false,  //是否为调试
                    positionClass: "toast-center-center",
                    onclick: jumpTo(),
                    showDuration: "300",  //显示动作时间
                    hideDuration: "1000",  //隐藏动作时间
                    timeOut: "1000",
                    showMethod: "fadeIn",
                    hideMethod: "fadeOut"
                };
                toastr.success("添加书架成功");
            });
            /* 点击加入书架按钮后等待一秒后再跳转 */
            function jumpTo() {
                var t=1;
                setInterval(function() {
                    t--;
                    if(t==0) {
                        window.location.href = "${pageContext.request.contextPath }/AddNovelToShelf?_account=${user.getAccount() }&_number=${novel.getNum() }&_collectionTimes=${novel.getCollectionTimes()+1 }";
                    }
                },1000);
            }
        });
    </script>
    <style>
        body,html{background-color: #F5F5F5;display: flex;min-height: 100vh;flex-direction: column;}
        *{margin: 0;padding: 0;}
        .mag_top{margin-top: 10px;}
        .author{border-radius: 50%; border: 2px solid #FF8D7B;background-color: #FF8D7B;}
        .second{font-weight: 700; color: black;font-size: 18px;}
        header{background: #ffffff;}
        .footer_span{height: 40px;;text-align: center;line-height: 40px;}
        footer{background-color: #434343;min-height: 80px;}
        #center{flex: 1;}
        .book_img{width: 240px;height: 320px;}
    </style>
</head>
<body>
<header class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="top1" style="margin-top: 12px; margin-left: 0px; height: 48px;">
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
                    <a class="navbar-brand" href="${pageContext.request.contextPath }/jsp/user/index_2.jsp"><span>首页</span></a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath }/QueryNovelByPage">书库</a></li>
                        <li><a href="${pageContext.request.contextPath}/NovelRank">排行</a></li>
                        <li><a href="${pageContext.request.contextPath }/QueryNovelShelfByPage">书架</a></li>
                        <li><a href="${pageContext.request.contextPath}/QueryUser">个人信息</a></li>
                        <li>
                        <c:if test="${user.getAccount() == 'admin'}">
                            <li>
                                <a href="${pageContext.request.contextPath}/QueryUserByPage">管理员界面</a>
                            </li>
                        </c:if>
                        </li>
                    </ul>
                    <!-- 搜索框 -->
                    <form class="navbar-form navbar-right" action="${pageContext.request.contextPath }/QueryNovelByPage" method="get">
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
<div class="container" style="background-color: #ffffff;" id="center">
    <div class="row margin-top thumbnail">
        <div class="col-md-3">
            <img src="${pageContext.request.contextPath }/jsp/${novel.getImage() }" alt="书籍" class="book_img">
        </div>
        <div class="col-md-7 ">
            <span style="font-weight: 700;font-size: 30px;">${novel.getNovelName() }</span><br><br>
            <span style="font-weight: 700;font-size: 20px;">${novel.getAuthor() }&nbsp;著&nbsp;&nbsp;${novel.getCategory() }</span><br><br>
            <span style="font-size: 16px;">字数:</span><span style="font-weight: 700;font-size: 18px;">${novel.getTotalWords() }万</span>&nbsp;&nbsp;
            <span style="font-size: 16px;">总阅读数:</span><span style="font-weight: 700;font-size: 18px;">${novel.getReadTime() }</span><br><br>
            <div style="height: 108px;"><span style="font-size: 16px;">${novel.getIntroduction() }</span></div>
            <br>
            <a class="btn btn-default" href="${pageContext.request.contextPath }/NovelContentRead?__number=${novel.getNum() }&_readTimes=${novel.getReadTime() +1}&_collectionTimes=${novel.getCollectionTimes() }" role="button" style="width: 220px; background-color: red;">开始阅读</a>

            <!-- 是否在书架 -->
            <c:if test="${IsInNovelShelf==null || IsInNovelShelf==''}">
                <a class="btn btn-default" id="bookshelfbtn" href="javascript:void(0);" role="button" style="width: 102px;margin-left: 10px;border: 1px solid red;">加入书架</a>
            </c:if>
            <c:if test="${IsInNovelShelf!=null && IsInNovelShelf!=''}">
                <a class="btn btn-default" href="${pageContext.request.contextPath }/QueryNovelShelfByPage" role="button" style="width: 102px;margin-left: 10px;border: 1px solid red;">已加入书架</a>
            </c:if>
        </div>
        <div class="cod-md-2"></div>
    </div>



</div>
</body>
</html>
