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
    <title>小说阅读</title>
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/iconfont.css">
    <link href="${pageContext.request.contextPath }/css/toastr.min.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath }/js/toastr.min.js"></script>
    <script>
        $(function() {
            $(".bookshelfbtn").click(function() {
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
                        if(${bookContent != null}) {
                            window.location.href = "${pageContext.request.contextPath}/AddNovelToShelf?_account=${user.getAccount() }&_number=${novelContent.getNovelNumber() }&_chapterNumber=${novelContent.getChapterNumber()}&_collectionTimes=${_collectionTimes +1}";
                        }else {
                            window.location.href = "${pageContext.request.contextPath}/AddNovelToShelf?_account=${user.getAccount() }&_number=${_number}&_chapterNumber=${_chapterNumber}&_collectionTimes=${_collectionTimes +1}";
                        }
                    }
                },1000);
            }
        });
    </script>
    <style>
        body,html{background-color: #E7E1D4;display: flex;min-height: 100vh;flex-direction: column;}
        .footer_span{height: 40px;;text-align: center;line-height: 40px;}
        footer{background-color: #434343;min-height: 80px;}
        #center{flex: 1;}
        .mag_top{margin-top: 15px;}

        .left_fw {position: fixed;top: 0px;left: 300px;background-color: #eee;border: 1px solid #ddd;}
        .left_fw_01 {width: 60px;height: 52px;border-bottom: 1px solid #aaa;line-height: 20px;padding-top: 8px;text-align: center;}
        .left_fw_01:last-child {border-bottom: none;}
        .left_fw_01 a {color: #666;text-decoration: none;}
        .left_fw_01 a:hover {color: #e00;}
        .right_fw {position: fixed;bottom: 0px;right: 300px;background-color: #eee;border: 1px solid #ddd;}
        .right_fw_01 {width: 60px;height: 50px;border-bottom: 1px solid #aaa;line-height: 20px;padding-top: 8px;text-align: center;}
        .right_fw_01:last-child {border-bottom: none;height: 40px;padding-top: 20px;line-height: 2px;}
        .right_fw_01 a {color: #666;text-decoration: none;}
        .right_fw_01 a:hover {color: #e00;}
        /* 设置图标 */
        .iconfont_1 {font-size: 20px;}
        .iconfont_2 {font-size: 35px;}
        .s1 {font-size: 13px;}

    </style>
</head>
<body>
<div id="center" style="background-color: #F7F2E8;width: 790px;margin: auto;">
    <div style="width: 750px;margin: auto;">
        <h2 style="text-align: center;">${novelContent.getChapterName() }</h2>
            <span style="font-size: 18px;">
                <c:if test="${novelContent!=null }">
                    ${novelContent.getChapterContent() }
                </c:if>
                <c:if test="${novelContent==null }">
                    <h3 style="text-align: center;">很抱歉，该小说还没更新...</h3>
                </c:if>
            </span>
    </div>
</div>
<!-- 左侧悬浮窗left_fw -->
<div class="left_fw">
    <!-- Button trigger modal -->
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary btn-lg left_fw_01" data-toggle="modal" data-target="#myModal">
        <i class="iconfont iconfont_1">&#xe629;<br><span class="s1">目录</span></i>
    </button>
    <div class="left_fw_01">
        <c:if test="${novelContent!=null}">
            <a href="${pageContext.request.contextPath }/QueryNovel?__number=${novelContent.getNovelNumber() } "><i class="iconfont iconfont_1">&#xe612;<br><span class="s1">返回</span></i></a>
        </c:if>
        <c:if test="${novelContent==null}">
            <a href="${pageContext.request.contextPath }/QueryNovel?__number=${_number} "><i class="iconfont iconfont_1">&#xe612;<br><span class="s1">返回</span></i></a>
        </c:if>
    </div>
    <div class="left_fw_01"><a href="#"><i class="iconfont iconfont_1">&#xe609;<br><span
            class="s1">设置</span></i></a></div>
    <div class="left_fw_01">
        <!-- 已在书架 -->
        <c:if test="${IsInNovelshelf !=null}">
            <a href="${pageContext.request.contextPath }/QueryNovelShelfByPage">&#xe611;<br><span class="s1">已在书架</span></a>
        </c:if>
        <!-- 不在书架 -->
        <c:if test="${IsInNovelshelf ==null }">
            <a href="javascript:void(0);" class="bookshelfbtn"><i class="iconfont iconfont_1">&#xe611;<br><span class="s1">书架</span></i></a>
            <!-- 书籍内容书籍内容是否为空 -->
            <%-- <c:if test="${bookContent!= null}">
                <a href="${pageContext.request.contextPath }/AddBookToBookshelf?bs_account=${user.getUaccount() }&bs_bnum=${bookContent.getBc_bnum() }&chapter_num=${bookContent.getChapter_num()}&collectiontimes=${collectiontimes +1}" class="bookshelfbtn"><i class="iconfont iconfont_1">&#xe611;<br><span class="s1">书架</span></i></a>
            </c:if>
            <c:if test="${bookContent== null}">
                <a href="${pageContext.request.contextPath }/AddBookToBookshelf?bs_account=${user.getUaccount() }&bs_bnum=${bc_bnum}&chapter_num=${chapter_num}&collectiontimes=${collectiontimes +1}" class="bookshelfbtn"><i class="iconfont iconfont_1">&#xe611;<br><span class="s1">书架</span></i></a>
            </c:if> --%>
        </c:if>
    </div>
</div>

<div style="background-color: #F7F2E8;width: 790px;margin:15px auto;">
    <c:if test="${novelContent.getChapterNumber() <=1 || novelContent==null}">
    <a class="btn btn-default disabled" href="javascript:void(0);" role="button" style="width: 255px;height: 50px;;">
        <span style="font-size: 20px;line-height: 38px;">上一章</span>
    </a>
    </c:if>
    <c:if test="${novelContent.getChapterNumber() >1 && novelContent!=null}">
    <a class="btn btn-default" href="${pageContext.request.contextPath }/NovelContentRead?__number=${novelContent.getNovelNumber()}&_chapterNumber=${novelContent.getChapterNumber()-1 } " role="button" style="width: 255px;height: 50px;;">
        <span style="font-size: 20px;line-height: 38px;">上一章</span>
    </a>
    </c:if>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" style="width: 255px;height: 50px;margin-left: 8px;">
        目录
    </button>
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="myModalLabel" style="text-align: center;font-weight: 700;">${novelContent.getNovelName() }目录</h3>
                </div>
                <div class="modal-body">
                    <ul class="list-group">
                        <c:forEach items="${novelContentList }" var="l">
                            <li class="list-group-item"><a href="${cs}?__number=${novelContent.getNovelNumber()}&_chapterNumber=${l.getChapterNumber() } " style="font-size: 16px;">${l.getChapterName() }</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <c:if test="${novelContent.getChapterNumber() >=novelContentTotal || novelContent==null}">
        <a class="btn btn-default disabled" href="javascript:void(0);" role="button" style="width: 255px;height: 50px;margin-left: 9px;">
            <span style="font-size: 20px;line-height: 38px;">下一章</span>
        </a>
    </c:if>
    <c:if test="${novelContent.getChapterNumber() <novelContentTotal && novelContent!=null}">
        <a class="btn btn-default" href="${pageContext.request.contextPath }/NovelContentRead?__number=${novelContent.getNovelNumber()}&_chapterNumber=${novelContent.getChapterNumber()+1 } " role="button" style="width: 255px;height: 50px;margin-left: 9px;">
            <span style="font-size: 20px;line-height: 38px;">下一章</span>
        </a>
    </c:if>
</div>
</body>
</html>
