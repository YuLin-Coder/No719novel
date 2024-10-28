<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<table class="ptable_menu">
	<tr class="ptable_menu_title">
		<td>个人中心</td>
	</tr>
	<tr class="ptable_menu_text">
		<td><a href="page_myInfo.action">修改个人信息</a></td>
	</tr>
	<tr class="ptable_menu_text">
		<td><a href="page_myPwd.action">修改登录密码</a></td>
	</tr>
	<tr class="ptable_menu_text">
		<td><a href="page_listMyUpays.action">我的支付记录</a></td>
	</tr>
	<c:if test="${userFront.user_kind==2}">
	<tr class="ptable_menu_text">
		<td><a href="myMoney.jsp">会员余额查询</a></td>
	</tr>
	<tr class="ptable_menu_text">
		<td><a href="page_listMySblog2s.action">我的书评信息</a></td>
	</tr>
	<tr class="ptable_menu_text">
		<td><a href="page_listMyBookshelfs.action">我的书架管理</a></td>
	</tr>
	<tr class="ptable_menu_text">
		<td><a href="page_listMyCollects.action">我的图书收藏</a></td>
	</tr>
	<tr class="ptable_menu_text">
		<td><a href="page_listMyUhiss.action">我的阅读历史</a></td>
	</tr>
	</c:if>
</table>
