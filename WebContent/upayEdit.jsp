<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>购买会员卡</title>
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
			<div class="title">个人中心  &gt;&gt;  购买会员卡</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_saveBookshelf.action" method="post" style="width:100%;height:100%">    
				 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id}"/>  
				 <input type="hidden" name="upay_price" id="upay_price" value="20"/>
				 <table class="ptable" style="margin-bottom:5px;">
				 	<tr>
			          <td width="15%" align="right" style="padding-right:5px">会员月卡：</td>
			          <td width="*">
			          	￥ 20元 / 月
			          </td>
			        </tr> 
			        <tr>
						<td colspan="2" align="left" style="padding-left:50px;font-weight:bold">
							选择银行：
						</td>
				    </tr>
				     <tr>
						<td colspan="2" align="left">
							<div style="width:680px;margin:0 auto;height:150px;line-height:37px;background-image:url('images/bank.jpg')">
								<input type="radio" name="bank" checked="checked" />　　　　　　　　　
								<input type="radio" name="bank" />　　　　　　　　　　
								<input type="radio" name="bank" />　　　　　　　　
								<input type="radio" name="bank" />　　　　　　　　　
								<input type="radio" name="bank" />　　
								<br/>
								<input type="radio" name="bank" />　　　　　　　　　
								<input type="radio" name="bank" />　　　　　　　　　　
								<input type="radio" name="bank" />　　　　　　　　
								<input type="radio" name="bank" />　　　　　　　　　
								<input type="radio" name="bank" />　　
								<br/>
								<input type="radio" name="bank" />　　　　　　　　　
								<input type="radio" name="bank" />　　　　　　　　　　
								<input type="radio" name="bank" />　　　　　　　　
								<input type="radio" name="bank" />　　　　　　　　　
								<input type="radio" name="bank" />　　
								<br/>
								<input type="radio" name="bank" />　　　　　　　　　
								<input type="radio" name="bank" />　　
							</div>
						</td>
				    </tr>
			        <tr class="">
			          <td align="center" height="30" colspan="2">
			          	<input type="button" id="addBtn" Class="btnstyle" value="确定购买" />
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
			var aQuery = $("#info").serialize();  
			$.post('page_addUpay.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('购买成功！');
							 window.location.href="page_listMyUpays.action";
						}else  if(responseObj.err.msg){
							 alert('购买失败：'+responseObj.err.msg);
						}else{
							 alert('购买失败：服务器异常！');
						}	
			},'json');
		 });
		
	});	 
	
</script>
</body>
</html>