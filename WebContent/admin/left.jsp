﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<title>管理页面</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">  
<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<style type="text/css">
.left{width:190px; height:280px; background-color:#EEF2FB}
table tr td{ font-size:12px; font-family:Arial, Helvetica, sans-serif;}
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}
#container {
	width: 190px;
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 190px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 190px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(images/menu_bgS2.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 190px;
	height: 26px;
	
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 190px;
	padding-left: 0px;
}
.MM {
	width: 190px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 190px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 190px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 190px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(images/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 190px;
	text-decoration: none;
}
</style>
</head>

<body>
<table width="190" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
  	<td height="23" style="text-align:center; line-height:23px; color:#BEDFF1; background:url(images/left_title.gif) no-repeat left bottom;font-weight:bold">功能模块管理</td>
  </tr>
  <tr>
    <td width="190" valign="top" style="background:#fff;">
    	<div class="left">
			 <table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
			  <tr>
				<td width="190" valign="top">
				<div id="container">
				  <h1 class="type"><a href="javascript:void(0)">个人信息中心</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="modifyInfo.jsp" target="MainFrame">个人信息</a></li>
					  <li><a href="modifyPwd.jsp" target="MainFrame">修改密码</a></li>
					</ul>
				  </div>
				  
				  <c:if test="${admin.user_type==2}">
				  <h1 class="type"><a href="javascript:void(0)">普通用户管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listUsers.action" target="MainFrame">普通用户查询</a></li>
					  <li><a href="Admin_addUserShow.action" target="MainFrame">新增普通用户</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">会员用户管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listUsers2.action" target="MainFrame">会员用户查询</a></li>
					  <li><a href="Admin_addUserShow2.action" target="MainFrame">新增会员用户</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">支付记录管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listUpays.action" target="MainFrame">支付记录查询</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">会员余额查询</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listUsers3.action" target="MainFrame">会员余额查询</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">图书类别管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listBookTypes.action" target="MainFrame">图书类别查询</a></li>
					  <li><a href="Admin_addBookTypeShow.action" target="MainFrame">新增图书类别</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">图书信息管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listTbooks.action" target="MainFrame">图书查询</a></li>
					  <li><a href="Admin_addTbookShow.action" target="MainFrame">新增图书</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">书评信息管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listSblog2s.action" target="MainFrame">书评信息查询</a></li>
					</ul>
				  </div>
				  </c:if>
				  
					<script type="text/javascript">
						var contents = document.getElementsByClassName('content');
						var toggles = document.getElementsByClassName('type');
					
						var myAccordion = new fx.Accordion(
							toggles, contents, {opacity: true, duration: 400}
						);
						myAccordion.showThisHideOpen(contents[0]);
					</script>
				</div>
				</td>
			  </tr>
			</table>       	
        </div>
    </td>
  </tr>
</table>
</body>
</html>
