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
    <title>管理员</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style>
        body,html{background-color: #F5F5F5;display: flex;min-height: 100vh;flex-direction: column;}
        *{margin: 0;padding: 0;}
        .mag_top{margin-top: 10px;}
        .author{border-radius: 50%; border: 2px solid #FF8D7B;background-color: #FF8D7B;}
        header{background: #ffffff;border-bottom: 1px solid #F5F5F5;}
        .footer_span{height: 40px;;text-align: center;line-height: 40px;}
        footer{min-height: 164px;}
        #footer{background-color: #434343;}
        th,td{text-align: center;font-size: 17px;}
        #center{flex: 1;}
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
        <div class="col-md-8">
            <a href="${pageContext.request.contextPath}/jsp/user/index_2.jsp">
                <span>首页</span>
            </a>
            <a class="btn" href="${pageContext.request.contextPath}/QueryUserByPage" role="button"><span style="font-weight: 700;font-size: 20px;">用户信息</span></a>
        </div>
        <div class="col-md-4">
            <div class="top1" style="margin-top: 12px; margin-left: 40px; height: 48px;">
                <a class="btn" href="#" role="button"><span style="font-weight: 700;font-size: 20px;">${user.getAccount() }</span></a>
                <a class="btn" href="${pageContext.request.contextPath}/LogOut" role="button"><span style="font-weight: 700;font-size: 20px;">注销</span></a>
            </div>
        </div>
    </div>
</header>
<div class="container" style="background-color: #ffffff;" id="center">
    <div class="row" style="text-align: center;">
        <span style="font-size: 27px;font-weight: 700;">用户信息列表</span>
    </div>
    <br>
    <div class="row" id="bg3">
        <div style="float: left;font-size: 15px;">
            <form class="form-inline" action="${pageContext.request.contextPath}/QueryUserByPage" method="post">
                <div class="form-group">
                    <label for="exampleInputName2" >账号</label>
                    <input type="text" class="form-control" id="exampleInputName2" placeholder="请输入账号" name="account" value="${userParamMap.account[0] }">
                </div>
                <button type="submit" class="btn btn-default" style="background-color: turquoise;">查询</button>
            </form>
        </div>
        <div style="float: right;"><a href="javascript:void(0);" class="btn btn-primary" role="button" id="deleteSelect" >移出所选</a></div>
    </div>
    <div class="row mag_top" style="text-align: center;">
        <form action="${pageContext.request.contextPath}/DeleteUser" method="post" id="deleteForm">
            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th><input type="checkbox" id="checkAll"></th>
                    <th>编号</th>
                    <th>用户账号</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>权限</th>
                    <th>注册时间</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${userPages.getPageLists() }" var="l" varStatus="s">
                    <tr>
                        <td>
                            <c:if test="${l.getAccount() == 'admin'}">
                            </c:if>
                            <c:if test="${l.getAccount() != 'admin'}">
                                <input type="checkbox" name="selectBox" value="${l.getAccount() }" class="checkItem" style="width: 25px;height: 25px;">
                            </c:if>
                        </td>
                        <td>${s.count }</td>
                        <td>${l.getAccount() }</td>
                        <td>${l.getSex() }</td>
                        <td>${l.getAge() }</td>/
                        <td>${l.getRole() }</td>
                        <td>${l.getAddTime() }</td>
                        <td>
                            <c:if test="${l.getRole() == '管理员'}">
                            </c:if>
                            <c:if test="${l.getRole() != '管理员'}">
                                <a class="btn btn-default deleteThis" href="${pageContext.request.contextPath }/DeleteUser?_account=${l.getAccount() }" role="button">删除</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
</div>
<footer>
    <div class="container" style="background-color: #ffffff;font-size: 20px;">
        <!-- 分页 -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${userPages.getCurrentPageNumber() <= 1}">
                <li class="disabled">
                    </c:if>
                    <c:if test="${userPages.getCurrentPageNumber() >1 }">
                <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/QueryUserByPage?_currentPageNumber=${userPages.getCurrentPageNumber()-1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${userPages.getTotalPage() }" var="i" step="1">
                    <c:if test="${i == userPages.getCurrentPageNumber()}">
                        <li class="active">
                    </c:if>
                    <c:if test="${i != userPages.getCurrentPageNumber()}">
                        <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/QueryUserByPage?_currentPageNumber=${i}">${i }</a></li>
                </c:forEach>

                <c:if test="${userPages.getCurrentPageNumber() >= userPages.getTotalPage()}">
                <li class="disabled">
                    </c:if>
                    <c:if test="${userPages.getCurrentPageNumber() < userPages.getTotalPage()}">
                <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/QueryUserByPage?_currentPageNumber=${userPages.getCurrentPageNumber()+1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 20px;line-height: 45px  ;">共条${userPages.getTotalData() }数据,共${userPages.getTotalPage() }页</span>
            </ul>
        </nav>
    </div>
</footer>
</body>
</html>
