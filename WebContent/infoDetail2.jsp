<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书章节内容</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/info.css">
<link rel="stylesheet" type="text/css" href="css/message.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	var height = $("#list").height();
	$("#Picture2 .info_con").height(height-10-60-2);
	$("#paramsUnote\\.unote_content").height(height-10-60-2-10);
	
	$("#paramsUnote\\.unote_content").bind('input propertychange',function(){
		var aQuery = $("#info2").serialize();  
		$.post('page_addUnote.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	 });
});
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 #list .title{
	line-height:50px;
	width:100%;
	margin-left:10px;
	color:#A88D5A;
	font-size:16px;
	font-weight:bold;
	overflow:hidden;
}

#list .typehr{
    height:5px;
	width:100%;
	margin-left:10px;
	background:url('images/yun_hr.gif') repeat-x left center; 
	overflow:hidden;
}
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	<div id="list">
		 <div class="nav">当前位置: 主页 > 【${tbook.tbook_name}】-章节详情 >  </div>
		 <div class="article_title">${info.info_title}</div>
		 <div class="article_time">发布时间：${fn:substring(info.info_date,0,19)}　章节字数：${info.info_words}个</div>
		 <div class="article_con">${info.info_contentShow}</div>
	</div>
	<div id="Picture2">
		<div class="titleBg" style="padding-left:60px;">我的阅读笔记</div>
		<div class="info_con" style="min-height:400px;max-height:none">
			<form id="info2" name="info2" action="" method="post" style="width:100%;height:100%">
			<input type="hidden" name="unote_id" value="${unote.unote_id}"/>
			<input type="hidden" name="user_id" value="${unote.user_id}"/>
			<input type="hidden" name="info_id" value="${unote.info_id}"/>
			<textarea id="paramsUnote.unote_content" name="unote_content" style="width:245px;height:390px;color:red;border-width:0px">${unote.unote_content}</textarea>
			</form>
		</div>
	</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
 
</body>
</html>