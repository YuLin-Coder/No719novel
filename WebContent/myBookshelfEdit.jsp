<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${bookshelf!=null && bookshelf.bookshelf_id!=0?'编辑':'添加'}书架</title>
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
			<div class="title">个人中心  &gt;&gt;  ${bookshelf!=null && bookshelf.bookshelf_id!=0?'编辑':'添加'}书架</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_saveBookshelf.action" method="post" style="width:100%;height:100%">    
				 <input type="hidden" name="bookshelf_id" id="bookshelf_id" value="${bookshelf.bookshelf_id}"/>
				 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id}"/>
				 <table class="ptable" style="margin-bottom:5px;">
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">书架名称：</td>
			          <td width="*">
			          	<input type="text" style="width:450px;" name="bookshelf_name" id="bookshelf_name" value="${bookshelf.bookshelf_name}"/> 
			          </td>
			        </tr> 
			        <tr class="">
			          <td align="center" height="30" colspan="2">
			            <input type="button"  Class="btnstyle" value="返 回" onclick="window.history.back();"/>&nbsp;
			            <c:if test="${bookshelf!=null && bookshelf.bookshelf_id!=0}">
			          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
			          	</c:if>
			          	<c:if test="${bookshelf==null || bookshelf.bookshelf_id==0}">
			          	<input type="button" id="addBtn" Class="btnstyle" value="新 增" />
			          	</c:if>
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
		var num=/^\d+(\.\d+)?$/;
		var num2=/^\d+$/;
		$("#addBtn").bind('click',function(){
			if($("#bookshelf_name").val()==''){
				alert('书架名称不能为空');
				return;
			}
			$("#bookshelf_id").val(0);
			var aQuery = $("#info").serialize();  
			$.post('page_addBookshelf.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('新增成功！');
							 window.location.href="page_listMyBookshelfs.action";
						}else  if(responseObj.err.msg){
							 alert('新增失败：'+responseObj.err.msg);
						}else{
							 alert('新增失败：服务器异常！');
						}	
			},'json');
		 });
		
		 $("#editBtn").bind('click',function(){
			if($("#bookshelf_name").val()==''){
				alert('书架名称不能为空');
				return;
			}
			var aQuery = $("#info").serialize();  
			$.post('page_saveBookshelf.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('编辑成功！');
							 window.location.href="page_listMyBookshelfs.action";
						}else  if(responseObj.err.msg){
							 alert('编辑失败：'+responseObj.err.msg);
						}else{
							 alert('编辑失败：服务器异常！');
						}	
			},'json');
		 });
		
	});	 
	
</script>
</body>
</html>