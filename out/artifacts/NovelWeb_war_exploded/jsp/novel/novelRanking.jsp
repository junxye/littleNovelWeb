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
    <title>排行榜</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/ranking.css">
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
        a:hover{text-decoration: none;}
    </style>
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
                        <li class="active"><a href="${pageContext.request.contextPath}/NovelRank">排行</a></li>
                        <li><a href="${pageContext.request.contextPath}/QueryNovelShelfByPage">书架</a></li>
                        <li><a href="${pageContext.request.contextPath}/QueryUser">个人信息</a></li>
                        <li><c:if test="${user.getAccount() == 'admin'}">
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

<!-- 中间部分 -->
<div id="center" class="container" style="background-color: #ffffff;">
    <div class="row">
        <div class="col-md-3">
            <!-- 阅读榜 -->
            <div class="chartsSame">
                <div class="ListFirst">
                    <h3>阅读榜</h3>
                </div>
                <!-- 第一名 -->
                <div class="booksNo_01">
                    <!-- 书的文本部分 -->
                    <div class="bookText">
                        <!-- 书名 -->
                        <div class="bookName">
                            <span class="no01">1</span>&nbsp;
                            <a href="${pageContext.request.contextPath}/QueryNovel?__number=${readTimesRank.get(0).getNum() } "><span style="font-size: 18px;">${readTimesRank.get(0).getNovelName() }</span></a>
                        </div>
                        <!-- 作者 -->
                        <div class="bookAuthor"><span>${readTimesRank.get(0).getAuthor() }</span></div>
                        <!-- 类别 -->
                        <div class="bookCategory"><span>[${readTimesRank.get(0).getCategory() }]</span></div>
                        <span style="font-size: 13px;color: #999;margin-left: 30px;">${readTimesRank.get(0).getReadTime() }</span>
                    </div>
                    <!-- 书的图片 -->
                    <div class="bookImg"><a href="${pageContext.request.contextPath}/QueryNovel?__number=${readTimesRank.get(0).getNum() }"><img src="${pageContext.request.contextPath }/img/${readTimesRank.get(0).getImage() }" style="height: 90px;"></a></div>
                </div>
                <!-- 第二名 -->
                <div class="booksNo_02">
                    <span class="no02">2</span>&nbsp;
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=${readTimesRank.get(1).getNum() }">${readTimesRank.get(1).getNovelName() }</a>
                    <span class="monthlyVotesRight">${readTimesRank.get(1).getReadTime() }</span>
                </div>
                <!-- 第三名 -->
                <div class="booksNo_02">
                    <span class="no03">3</span>&nbsp;
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=${readTimesRank.get(2).getNum() }">${readTimesRank.get(2).getNovelName() }</a>
                    <span class="monthlyVotesRight">${readTimesRank.get(2).getReadTime() }</span>
                </div>
                <!-- 前三名后面的 -->
                <c:forEach begin="3" end="${readTimesRank.size() -1}" var="r" varStatus="s" step="1">
                    <div class="booksNo_02">
                        <span class="no04">${s.count+3}</span>&nbsp;
                        <a href="${pageContext.request.contextPath}/QueryNovel?__number=${readTimesRank.get(r).getNum() }">${readTimesRank.get(r).getNovelName() }</a>
                        <span class="monthlyVotesRight">${readTimesRank.get(r).getReadTime() }</span>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="col-md-3">
            <!-- 收藏榜 -->
            <div class="chartsSame">
                <div class="ListFirst">
                    <h3>收藏榜</h3>
                </div>
                <!-- 第一名 -->
                <div class="booksNo_01">
                    <!-- 书的文本部分 -->
                    <div class="bookText">
                        <!-- 书名 -->
                        <div class="bookName">
                            <span class="no01">1</span>&nbsp;
                            <a href="${pageContext.request.contextPath}/QueryNovel?__number=${collectionTimesRank.get(0).getNum() }"><span style="font-size: 18px;">${collectionTimesRank.get(0).getNovelName() }</span></a>
                        </div>
                        <!-- 作者 -->
                        <div class="bookAuthor"><span>${collectionTimesRank.get(0).getAuthor() }</span></div>
                        <!-- 类别 -->
                        <div class="bookCategory"><span>[${collectionTimesRank.get(0).getCategory() }]</span></div>
                        <span style="font-size: 13px;color: #999;margin-left: 30px;">${collectionTimesRank.get(0).getCollectionTimes() }</span>
                    </div>
                    <!-- 书的图片 -->
                    <div class="bookImg"><a href="${pageContext.request.contextPath}/QueryNovel?__number=${collectionTimesRank.get(0).getNum() }"><img src="${pageContext.request.contextPath }/img/${collectionTimesRank.get(0).getImage() }" style="height: 90px;"></a></div>
                </div>
                <!-- 第二名 -->
                <div class="booksNo_02">
                    <span class="no02">2</span>&nbsp;
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=${collectionTimesRank.get(1).getNum() }">${collectionTimesRank.get(1).getNovelName() }</a>
                    <span class="monthlyVotesRight">${collectionTimesRank.get(1).getCollectionTimes() }</span>
                </div>
                <!-- 第三名 -->
                <div class="booksNo_02">
                    <span class="no03">3</span>&nbsp;
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=${collectionTimesRank.get(2).getNum() }">${collectionTimesRank.get(2).getNovelName() }</a>
                    <span class="monthlyVotesRight">${collectionTimesRank.get(2).getCollectionTimes() }</span>
                </div>
                <!-- 前三名后面的 -->
                <c:forEach begin="3" end="${collectionTimesRank.size() -1}" var="r" varStatus="s" step="1">
                    <div class="booksNo_02">
                        <span class="no04">${s.count+3 }</span>&nbsp;
                        <a href="${pageContext.request.contextPath}/QueryNovel?__number=${collectionTimesRank.get(r).getNum() }">${collectionTimesRank.get(r).getNovelName() }</a>
                        <span class="monthlyVotesRight">${collectionTimesRank.get(r).getCollectionTimes() }</span>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="col-md-3">
            <!-- 新书榜 -->
            <div class="chartsSame">
                <div class="ListFirst">
                    <h3>新书榜</h3>
                </div>
                <!-- 第一名 -->
                <div class="booksNo_01">
                    <!-- 书的文本部分 -->
                    <div class="bookText">
                        <!-- 书名 -->
                        <div class="bookName">
                            <span class="no01">1</span>&nbsp;
                            <a href="${pageContext.request.contextPath}/QueryNovel?__number=${timeRank.get(0).getNum() }"><span style="font-size: 18px;">${timeRank.get(0).getNovelName() }</span></a>
                        </div>
                        <!-- 作者 -->
                        <div class="bookAuthor"><span>${timeRank.get(0).getAuthor() }</span></div>
                        <!-- 类别 -->
                        <div class="bookCategory"><span>[${timeRank.get(0).getCategory() }]</span></div>
                    </div>
                    <!-- 书的图片 -->
                    <div class="bookImg"><a href="${pageContext.request.contextPath}/QueryNovel?__number=${timeRank.get(0).getNum() }"><img src="${pageContext.request.contextPath }/img/${timeRank.get(0).getImage() }" style="height: 90px;"></a></div>
                </div>
                <!-- 第二名 -->
                <div class="booksNo_02">
                    <span class="no02">2</span>&nbsp;
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=${timeRank.get(1).getNum() }">${timeRank.get(1).getNovelName() }</a>
                </div>
                <!-- 第三名 -->
                <div class="booksNo_02">
                    <span class="no03">3</span>&nbsp;
                    <a href="${pageContext.request.contextPath}/QueryNovel?__number=${timeRank.get(2).getNum() }">${timeRank.get(2).getNovelName() }</a>
                </div>
                <!-- 前三名后面的 -->
                <c:forEach begin="3" end="${timeRank.size() -1}" var="r" varStatus="s" step="1">
                    <div class="booksNo_02">
                        <span class="no04">${s.count+3 }</span>&nbsp;
                        <a href="${pageContext.request.contextPath}/QueryNovel?__number=${timeRank.get(r).getNum() }">${timeRank.get(r).getNovelName() }</a>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="col-md-3">
            <!-- 推荐榜 -->
            <div class="newBook chartsSame">
                <div class="ListFirst">
                    <h3>推荐榜</h3>
                </div>
                <!-- 第一名 -->
                <div class="booksNo_01">
                    <!-- 书的文本部分 -->
                    <div class="bookText">
                        <!-- 书名 -->
                        <div class="bookName">
                            <span class="no01">1</span>&nbsp;
                            <a href="#"><span style="font-size: 18px;">出人头地</span></a>
                        </div>
                        <!-- 作者 -->
                        <div class="bookAuthor"><span>荒唐镜</span></div>
                        <!-- 类别 -->
                        <div class="bookCategory"><span>[都市]</span></div>
                    </div>
                    <!-- 书的图片 -->
                    <div class="bookImg"><a href="#"><img src="${pageContext.request.contextPath }/img/book/d5.jpg" style="height: 90px;"></a></div>
                </div>
                <!-- 第二名 -->
                <div class="booksNo_02">
                    <span class="no02">2</span>&nbsp;
                    <a href="#">赘婿神医</a>
                </div>
                <!-- 第三名 -->
                <div class="booksNo_02">
                    <span class="no03">3</span>&nbsp;
                    <a href="#">废婿当道</a>
                </div>
                <!-- 剩下的 -->
                <div class="booksNo_02">
                    <span class="no04">4</span>&nbsp;
                    <a href="#">梦武轮回</a>
                </div>
                <div class="booksNo_02">
                    <span class="no04">5</span>&nbsp;
                    <a href="#">都市至尊仙帝</a>
                </div>
                <div class="booksNo_02">
                    <span class="no04">6</span>&nbsp;
                    <a href="#">武道剑主</a>
                </div>
                <div class="booksNo_02">
                    <span class="no04">7</span>&nbsp;
                    <a href="#">三界改造计划</a>
                </div>
                <div class="booksNo_02">
                    <span class="no04">8</span>&nbsp;
                    <a href="#">地府引路人</a>
                </div>
                <div class="booksNo_02">
                    <span class="no04">9</span>&nbsp;
                    <a href="#">霸气纵横九万里</a>
                </div>
                <div class="booksNo_02">
                    <span class="no04">10</span>&nbsp;
                    <a href="#">都市绝品狂尊</a>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>
