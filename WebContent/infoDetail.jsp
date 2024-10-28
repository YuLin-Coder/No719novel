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
		 <div class="article_time">发布时间：${fn:substring(info.info_date,0,19)}　章节字数：${info.info_words}个　<a style="text-decoration:underline;" href="page_queryInfo.action?info_id=${info.pre_id}">${info.pre_id!=0?'上一章':''}</a>　<a  style="text-decoration:underline;" href="page_queryInfo.action?info_id=${info.next_id}">${info.next_id!=0?'下一章':''}</a></div>
		 <div class="article_con">${info.info_contentShow}</div>
	</div>
	<div id="Picture"></div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
 
</body>
</html>