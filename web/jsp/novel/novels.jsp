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
    <title>书库</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style>
        body,html{background-color: #F5F5F5;display: flex;min-height: 100vh;flex-direction: column;}
        *{margin: 0;padding: 0;}
        .mag_top{margin-top: 10px;}
        .author{border-radius: 50%; border: 2px solid #FF8D7B;background-color: #FF8D7B;}
        .second{font-weight: 700; color: black;font-size: 18px;}
        header{background: #ffffff;}
        .footer_span{height: 40px;;text-align: center;line-height: 40px;}
        footer{min-height: 165px;}
        #footer{background-color: #434343;}
        .book_img{width: 100px;height: 130px;}
        #center{flex: 1;}
        a:hover{text-decoration: none;}
        .choose{color: red;}
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
                        <li class="active"><a href="${pageContext.request.contextPath}/QueryNovelByPage">书库</a></li>
                        <li><a href="${pageContext.request.contextPath}/NovelRank">排行</a></li>
                        <li><a href="${pageContext.request.contextPath}/QueryNovelShelfByPage">书架</a></li>
                        <li><a href="${pageContext.request.contextPath}/QueryUser">个人信息</a></li>
                        <c:if test="${user.getAccount() == 'admin'}">
                            <li>
                                <a href="${pageContext.request.contextPath}/QueryUserByPage">管理员界面</a>
                            </li>
                        </c:if>
                    </ul>
                    <!-- 搜索框 -->
                    <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/QueryNovelByPage" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="关键词、书名、作者名" name="_param" value="${f_param }">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div>
</header>
<div class="container" id="center" style="background-color: #ffffff;">
    <div style="margin-left: 37px;">
        <!-- Single button -->
        <div class="btn-group">
            <c:if test="${novelParamMap._category==null||novelParamMap._category==''}">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color:#0EBB9A">
                    全部<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=玄幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">玄幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=科幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">科幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=都市&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">都市</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=仙侠&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">仙侠</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=游戏&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">游戏</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=古言&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">古言</a></li>
                </ul>
            </c:if>
            <c:if test="${novelParamMap._category=='玄幻'}">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color:#0EBB9A">
                    玄幻<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">全部</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=科幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">科幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=都市&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">都市</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=仙侠&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">仙侠</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=游戏&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">游戏</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=古言&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">古言</a></li>
                </ul>
            </c:if>
            <c:if test="${novelParamMap._category=='科幻'}">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color:#0EBB9A">
                    科幻<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">全部</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=玄幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">玄幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=都市&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">都市</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=仙侠&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">仙侠</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=游戏&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">游戏</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=古言&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">古言</a></li>
                </ul>
            </c:if>
            <c:if test="${novelParamMap._category=='都市'}">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color:#0EBB9A">
                    都市<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">全部</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=玄幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">玄幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=科幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">科幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=仙侠&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">仙侠</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=游戏&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">游戏</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=古言&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">古言</a></li>
                </ul>
            </c:if>
            <c:if test="${novelParamMap._category=='仙侠'}">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color:#0EBB9A">
                    仙侠<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">全部</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=玄幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">玄幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=科幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">科幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=都市&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">都市</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=游戏&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">游戏</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=古言&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">古言</a></li>
                </ul>
            </c:if>
            <c:if test="${novelParamMap._category=='游戏'}">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color:#0EBB9A">
                    游戏<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">全部</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=玄幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">玄幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=科幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">科幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=都市&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">都市</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=仙侠&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">仙侠</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=古言&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">古言</a></li>
                </ul>
            </c:if>
            <c:if test="${novelParamMap._category=='古言'}">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color:#0EBB9A">
                    古言<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">全部</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=玄幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">玄幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=科幻&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">科幻</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=都市&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">都市</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=仙侠&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">仙侠</a></li>
                    <li><a href="${pageContext.request.contextPath}/QueryNovelByPage?_category=游戏&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">游戏</a></li>
                </ul>
            </c:if>
            <!-- 使用了字数排序 -->
            <c:if test="${totalWords !=null && totalWords != ''}">
                <a class="btn btn-default choose" href="javascript:void(0);" role="button" style="background-color: #0EBB9A;margin-left: 10px;border-radius: 10%;">字数</a>
            </c:if>
            <!-- 没使用了字数排序 -->
            <c:if test="${totalWords ==null || totalWords == ''}">
                <a class="btn btn-default" href="${pageContext.request.contextPath}/QueryNovelByPage?_category=${novelParamMap._category }&_param=${_param }&totalWords=totalWords&addTime=${addTime }" role="button" style="background-color: #0EBB9A;margin-left: 10px;border-radius: 10%;">字数</a>
            </c:if>
            <!-- 使用了时间排序 -->
            <c:if test="${addTime != null && addTime != ''}">
                <a class="btn btn-default choose" href="javascript:void(0);" role="button" style="background-color: #0EBB9A;margin-left: 10px;border-radius: 10%;">时间</a>
            </c:if>
            <!-- 没使用了时间排序 -->
            <c:if test="${addTime == null || addTime == ''}">
                <a class="btn btn-default" href="${pageContext.request.contextPath}/QueryNovelByPage?_category=${novelParamMap._category }&addTime=addTime&_param=${_param }&totalWords=${totalWords }" role="button" style="background-color: #0EBB9A;;margin-left: 10px;border-radius: 10%;">时间</a>
            </c:if>
        </div>
    </div>

    <!-- 书籍信息 -->
    <c:forEach items="${novelPages.getPageLists() }" var="l">
        <div class="row thumbnail">
            <div class="col-md-2">
                <div><a href="${pageContext.request.contextPath}/QueryNovel?__number=${l.__number } "><img src="${pageContext.request.contextPath}/all/${l.image }" alt="书籍" class="book_img" style="height: 133px;"></a></div>
            </div>
            <div class="col-md-10">
                <a href="${pageContext.request.contextPath}/QueryNovel?__number=${l.__number }"><span style="font-weight: 700;font-size: 27px;">${l._name }</span></a><br>
                <a href="${pageContext.request.contextPath}/QueryNovel?__number=${l.__number }"><span style="font-weight: 700;font-size: 18px;">${l._author }</span></a><br>
                <div style="height: 45px;"><a href="${pageContext.request.contextPath}/QueryNovel?__number=${l.__number }"><span style="color: black;">${l._introduction }</span></a></div>
                <span style="border: 1px solid red;color: red;font-size: 16px;">${l.category }</span>&nbsp;&nbsp;<span style="border: 1px solid blue;color: blue;font-size: 16px;">${l.totalWords }万</span>
                &nbsp;<span style="border: 1px solid green;color: green;font-size: 16px;">阅读量:${l.readTimes }</span>
                &nbsp;<span style="border: 1px solid orange;color: orange;font-size: 16px;">收藏量:${l.collectionTimes }</span>
            </div>
        </div>
    </c:forEach>
</div>
<footer><!--  navbar-fixed-bottom -->
    <div class="container" style="background-color: #ffffff;font-size: 20px;">
        <!-- 分页 -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${novelPages.getCurrentPageNumber() == 1 }">
                <li class="disabled">
                    </c:if>
                    <c:if test="${novelPages.getCurrentPageNumber() != 1 }">
                <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath }/QueryNovelByPage?_currentPageNumber=${novelPages.getCurrentPageNumber() -1}&_category=${novelParamMap._category }&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${novelPages.getTotalPage() }" var="i">
                    <c:if test="${novelPages.getCurrentPageNumber() == i }">
                        <li class="active">
                    </c:if>
                    <c:if test="${novelPages.getCurrentPageNumber() != i }">
                        <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath }/QueryNovelByPage?_currentPageNumber=${i }&_category=${novelParamMap._category }&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }">${i }</a></li>
                </c:forEach>
                <c:if test="${novelPages.getCurrentPageNumber() == novelPages.getTotalPage() }">
                <li class="disabled">
                    </c:if>
                    <c:if test="${novelPages.getCurrentPageNumber() != novelPages.getTotalPage() }">
                <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath }/QueryNovelByPage?_currentPageNumber=${novelPages.getCurrentPageNumber() +1 }&_category=${novelParamMap._category }&addTime=${addTime }&_param=${_param }&totalWords=${totalWords }" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 20px;line-height: 45px  ;">共${novelPages.getTotalData() }条数据,共${novelPages.getTotalPage() }页</span>
            </ul>
        </nav>
    </div>
</footer>

</body>
</html>
