<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增留言</title>
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
			<div class="title">个人中心  &gt;&gt;  新增留言</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_addSblog.action" method="post">    
				 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id }"/>
				 <input type="hidden" name="sblog_id" id="sblog_id" value="0"/>
				 <table class="ptable" style="margin-bottom:5px;">
			        <tr>
			          <td width="150"  align="right" style="padding-right:5px">留言内容：</td>
			          <td width="*">
			          	<textarea style="width:500px;height:150px" name="sblog_content" id="noticeContent"></textarea> 
			          </td>
			        </tr> 
			        <tr class="">
			          <td align="center" height="30" colspan="4">
			            <input type="button" id="addBtn" Class="btnstyle" value="发 布" />
			          </td>
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
		$("#addBtn").bind('click',function(){
			if($("#noticeContent").val()==''){
				alert('留言内容不能为空');
				return;
			}
			$("#sblog_id").val(0);
			var aQuery = $("#info").serialize();  
			$.post('page_addSblog.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('发布成功！');
							 window.location.href="page_listMySblogs.action";
						}else  if(responseObj.err.msg){
							 alert('发布失败：'+responseObj.err.msg);
						}else{
							 alert('发布失败：服务器异常！');
						}	
			},'json');
		 });
		
	});	 
	
</script>
</body>
</html>