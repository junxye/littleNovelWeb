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
    <title>书架</title>
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
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
        th,td{text-align: center;font-size: 17px;}
        .book_img{width: 100px;height: 130px;}
        #center{flex: 1;}
        #bg3{ height: 100px;}
    </style>
    <script>
        $(function() {
            $("#checkAll").click(function() {
                $(".checkItem").prop("checked",this.checked);
            });
            $("#deleteSelect").click(function() {
                var flag = false;
                var boxs = $(".checkItem");
                //遍历所有单选框
                for(var i = 0; i < (boxs.length); i++) {
                    if(boxs[i].checked) {
                        flag = true;
                    }
                }
                if(flag) {
                    if(confirm("是否删除所选")) {
                        //提交表单
                        $("#deleteForm").submit();
                    }
                }
            });
        });
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
                        <li class="active"><a href="${pageContext.request.contextPath}/QueryNovelShelfByPage">书架</a></li>
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
<div class="container" style="background-color: #ffffff;" id="center">
    <div >
        <div style="float: left;font-size: 16px;">
            <form class="form-inline" style="margin-top: 15px;font-weight: 700;margin-bottom: 5px;" action="${pageContext.request.contextPath }/QueryNovelShelfByPage" method="post">
                <div class="form-group">
                    <label for="exampleInputName2" style="color: red;">作品名</label>
                    <input type="text" class="form-control" id="exampleInputName2" placeholder="请输入作品名" name = "_name" value="${novelShelfParamMap._name[0] }"
                           style="background-color:transparent;border: 1px solid yellow;color: turquoise;">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail2" style="color: red;">作者名</label>
                    <input type="text" class="form-control" id="exampleInputEmail2" placeholder="请输入作者名" name="_author" value="${novelShelfParamMap._author[0] }"
                           style="background-color:transparent;border: 1px solid yellow;color: turquoise;">
                </div>
                <button type="submit" class="btn btn-default" style="background-color: turquoise;">查询</button>
            </form>
        </div>
        <div style="float: right;margin-right: 60px;margin-top: 15px;margin-bottom: 5px;"><a href="javascript:void(0);" class="btn btn-primary" role="button" id="deleteSelect" >移出所选</a></div>
    </div>
    <div class="row mag_top" style="text-align: center;">
        <form action="${pageContext.request.contextPath}/DeleteNovelFromShelf" method="post" id="deleteForm">
            <table border="1" class="table table-bordered table-hover" style="text-align: center;">
                <tr class="success">
                    <th><input type="checkbox" id="checkAll"></th>
                    <th>编号</th>
                    <th>分类</th>
                    <th>作品信息</th>
                    <th>作者</th>
                    <th>加入时间</th>
                    <th>操作</th>
                </tr><tr>
                <c:if test="${novelShelfPage.getTotalData() == 0}">无</c:if></tr>
                <c:if test="${novelShelfPage.getTotalData() != 0}">
                <c:forEach items="${novelShelfPage.getPageLists()}" var="l" varStatus="s">
                    <tr>
                        <td><input type="checkbox" name="checkItem" value="${l.getNovelNumber() }" class="checkItem" style="width: 25px;height: 25px;"></td>
                        <td>${s.count }</td>
                        <td>${l.getNovel().getCategory() }</td>
                        <td>${l.getNovel().getNovelName() }</td>
                        <td>${l.getNovel().getAuthor() }</td>
                        <td>${l.getTime() }</td>
                        <td><a class="btn btn-default" href="${pageContext.request.contextPath}/DeleteNovelFromShelf? __number=${l.getNovelNumber() }" role="button">移出书架</a></td>
                    </tr>
                </c:forEach>
                </c:if>
            </table>
        </form>
    </div>
</div>
<footer>
    <div class="container" style="background-color: #ffffff;font-size: 20px;">
        <!-- 分页 -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${novelShelfPage.getCurrentPageNumber()<=1}">
                <li class="disabled">
                    </c:if>
                    <c:if test="${novelShelfPage.getCurrentPageNumber()>1}">
                <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/QueryNovelShelfByPage?_currentPageNumber=${novelShelfPage.getCurrentPageNumber()-1 }" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${novelShelfPage.getTotalPage() }" var="i" step="1">
                    <c:if test="${i == novelShelfPage.getCurrentPageNumber()}">
                        <li class="active">
                    </c:if>
                    <c:if test="${i != novelShelfPage.getCurrentPageNumber()}">
                        <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/QueryBookShelfByPageServlet?_currentPageNumber=${i} ">${i}</a></li>
                </c:forEach>

                <c:if test="${novelShelfPage.getCurrentPageNumber()>=novelShelfPage.getTotalPage()}">
                <li class="disabled">
                    </c:if>
                    <c:if test="${novelShelfPage.getCurrentPageNumber()<novelShelfPage.getTotalPage()}">
                <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/QueryNovelShelfByPage?_currentPageNumber=${novelShelfPage.getCurrentPageNumber()+1 }" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 20px;line-height: 45px;">共${novelShelfPage.getTotalData() }条数据,共${novelShelfPage.getTotalPage() }页</span>
            </ul>
        </nav>
    </div>
</footer>
</body>
</html>
