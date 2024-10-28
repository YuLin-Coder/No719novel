<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>会员余额查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	var user_id="${userFront.user_id}";
	if(user_id==null || user_id==''){
		alert("请先登录！");
		window.location.href="page_login.action";
	}
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	 <div id="product_menu">
	 	 <jsp:include page="leftMenu.jsp"></jsp:include>
	 </div>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  会员余额查询</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_saveUserFront.action" method="post">    
				 <input type="hidden" name="user_id" value="${userFront.user_id}"/>
				 <input type="hidden" name="user_photo" id="user_photo" value='${userFront.user_photo}'/>
				 <table class="ptable" style="margin-bottom:5px;">
					<tr>
			          <td width="200" align="right" style="padding-right:5px">剩余会员日期：</td>
			          <td width="*">${userFront.leftDays}</td>
			        </tr> 
			        <tr>
			          <td align="right" style="padding-right:5px">会员到期时间：</td>
			          <td>${userFront.invalid_date}</td>
			        </tr> 
				 </table>
				 </form>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	$(document).ready(function(){
		
	});	 
</script>
</body>
</html>