<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/product.css">
<link rel="stylesheet" type="text/css" href="css/message.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript">
 $(document).ready(function(){
	 
	 //点击预定
	 var user_id = "${userFront.user_id}";
	 var user_kind = "${userFront.user_kind}";
	 var num = /^\d+$/;
	 $("#readBook").bind('click',function(){
		 if(user_id==''){
			 alert('请先登录');
			 return;
		 }
		 $("#info").submit();
	 });
	 
	 $("#pfBook").bind('click',function(){
		if(user_id==''){
			 alert('请先登录');
			 return;
		}
		if(user_kind!='2'){
			 alert('只有会员才能评分');
			 return;
		}
		var aQuery = $("#info").serialize();  
		$.post('page_addEvaluate.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('评分成功！');
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	 });
	 
	 $("#collectBook").live("click",function(){
		if(user_id==''){
			 alert('请先登录');
			 return;
		}
		if(user_kind!='2'){
			 alert('只有会员才能收藏');
			 return;
		}
     	if($("#hbzj").css("display")=="none"){
         	$("#hbzj").css("top",(parseInt($(this).offset().top)+40)+"px").css("left",$(this).offset().left).show();
     	}else{
     		$("#hbzj").hide();
     	}
     });
	 
	 $("a[id^='addCollect']").bind('click',function(){
		    var bookshelf_id = $(this).attr("id").split("_")[1];
		    $("#bookshelf_id").val(bookshelf_id);
			var aQuery = $("#info").serialize();  
			$.post('page_addCollect.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('收藏成功！');
							 $("#hbzj").hide();
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
 #hbzj{
	position:absolute;
	zIndex:999;
	width:110px;
	height:100px;
	top:70px;
	left:200px;
	border:5px solid #489b0d;
	background-color:white; 
	font-size:12px!important;
	display:none;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middleBg">
	<!--  产品检索介绍 -->
	 <div id="product_info">
			<div class="productShow">
					<div class="title">${tbook.tbook_name}</div>
					<div class="typehr"></div>
					<div class="pictext">
							<div class="pic"><img src="images/tbooks/${tbook.tbook_pic}" width="250px" height="250px"/></div>
							<div class="text">
									<form name="info" id="info" action="page_listInfos.action" method="post">
									<input type="hidden" name="tbook_id" id="tbook_id" value="${tbook.tbook_id}"/>
									<input type="hidden" name="tbook_id" value="${tbook.tbook_id}"/>
									<input type="hidden" name="user_id" value="${userFront.user_id}"/>
									<input type="hidden" name="tbook_id" value="${tbook.tbook_id}"/>
									<input type="hidden" name="user_id" value="${userFront.user_id}"/>
									<input type="hidden" name="bookshelf_id" id="bookshelf_id" value="0"/>
									<div class="textTop" style="height:210px;line-height:30px;">
											图书类型：<span style="color:black">${tbook.book_type_name}</span>
											　　图书作者：<span style="color:black">${tbook.tbook_author}</span>
											<br/>上架时间：<span style="color:black">${fn:substring(tbook.tbook_date,0,10)}</span>
											<br/>累计字数：<span style="color:black">${tbook.tbook_words}万</span>
											<br/>点击量：<span style="color:black">${tbook.tbook_click}次</span>
											<br/>图书状态：<span style="color:black">${tbook.tbook_flagDesc}</span>
											<br/>读者评分：<span style="color:black">${tbook.evaluate_score}</span>
											<br/>我来评分：
											<span style="color:black">
											<input type="radio" name="evaluate_score" value="1"/>1分&nbsp;
             								<input type="radio" name="evaluate_score" value="2"/>2分&nbsp;
             								<input type="radio" name="evaluate_score" value="3"/>3分&nbsp;
             								<input type="radio" name="evaluate_score" value="4"/>4分&nbsp;
             								<input type="radio" name="evaluate_score" value="5" checked="checked"/>5分
											</span>
									</div>
									<div class="textBtn" style="height:50px;line-height:50px">
										<img id="readBook" src="images/readBook.png" style="border:none;cursor:pointer;vertical-align:middle" />&nbsp;&nbsp;
										<img id="pfBook" src="images/pfBook.png" style="border:none;cursor:pointer;vertical-align:middle" />&nbsp;&nbsp;
										<img id="collectBook" src="images/collectBook.png" style="border:none;cursor:pointer;vertical-align:middle" />
									</div>
									</form>
							</div>
					</div>
					<div class="typehr"></div>
					<div class="title">图书详情介绍</div>
					<div class="typehr"></div>
					<div class="intro">
						${tbook.tbook_descShow}
					</div>
					
					<div class="typehr"></div>
					<div class="title">用户书评信息</div>
					
					 <!-- 信息开始 -->
					<c:if test="${sblog2s!=null &&  fn:length(sblog2s)>0}">
					 <div style="width:100%;max-height:350px;overflow:auto">
   					<c:forEach items="${sblog2s}" var="sblog" varStatus="status">
					 <div class="messages2" style="width:900px;margin:0px auto;margin-top:5px">
						 <div class="messages_left" style="width:165px">
							<div class="nickName">${sblog.nick_name}</div>
							<div class="headphoto"><img class="circle"  src="images/head/${sblog.sblog_pic}"/></div>
						</div>
				
						<div class="messages_right" style="min-height:100px;width:720px;">
							<div class="time">
								<c:if test="${sblog.nick_name2!=null}">
								回复：${sblog.nick_name2}&nbsp;&nbsp;
								回复时间：${fn:substring(sblog.sblog_date,0,19)}　
								</c:if>
								<c:if test="${sblog.nick_name2==null}">
								评论时间：${fn:substring(sblog.sblog_date,0,19)}　
								</c:if>
								&nbsp;<img src="images/quote.gif" vlign="middle" style="vertical-align:middle"/>
								<a href="#link" id="reply_${sblog.nick_name}">回复</a>
								
							</div>
				
							<div class="sblog_title">
								 ${sblog.sblog_content}
							</div>
							
						</div>
					 </div>
					 </c:forEach>
					 </div>
					 </c:if>
					<!-- 信息结束 -->
					
					<!-- 发布留言 -->
					 <div id="addSblogReply" style="display:block">
					 <form name="info2" id="info2" action="page_addSblog2.action" method="post">
					 <input type="hidden" name="user_id" id="paramsSblog2.user_id" value="${userFront.user_id}"/>
					 <input type="hidden" name="nick_name" id="paramsSblog2.nick_name" value="${userFront.nick_name}"/>
					 <input type="hidden" name="nick_name2" id="paramsSblog2.nick_name2" value=""/>
					 <input type="hidden" name="sblog_pic" id="paramsSblog2.sblog_pic" value="${userFront.user_photo}"/>
					 <input type="hidden" name="tbook_id" id="paramsSblog2.tbook_id" value="${tbook.tbook_id}"/>
					 <table class="reply_add">
						<tr>
							<td class="theme" colspan="2">书评内容：</td>
						</tr>
						<tr>
							<td id="replyUser" align="left" colspan="2" style="padding-left:10px">发表书评</td>
						</tr>
						<tr>
							<td align="left" colspan="2" style="padding-left:10px">
								<textarea name="sblog_content" id="noticeContent" style="width:600px;height:100px" class="inputstyle"></textarea>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2" style="padding-left:10px">
								<img src="Random.jsp" width="50" valign="middle" style="cursor:pointer;vertical-align:middle" title="换一张" id="safecode" border="0" onClick="reloadcode()"/>
								<input type="text" id="random" name="random" style="width:80px;" class="inputstyle"/>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" id="addBtn" class="btnstyle" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" id="resetBtn" class="btnstyle" value="清空"/>
							</td>
						</tr>
					 </table>
					 </form>
					 </div>
					 <a name="link"></a>
			</div>

			 
			
	 </div>
	 <!--  产品检索 -->
	 
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script type="text/javascript">
var tbook_id = "${tbook.tbook_id}";
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  window.location.href="page_queryTbook.action?tbook_id="+tbook_id;
}
function ChangePage(pagenum)
{
	window.location.href="page_queryTbook.action?tbook_id="+tbook_id;
}

var user_id = "${userFront.user_id}";
var user_kind = "${userFront.user_kind}";
$(document).ready(function(){
	$("a[id^='reply']").bind("click",function(){
		var nick_name2 = $(this).attr("id").split("_")[1];
		$("#replyUser").html("回复："+nick_name2);
		$("#paramsSblog2\\.nick_name2").val(nick_name2);
	});
	
	$("#resetBtn").bind("click",function(){
		$("#info2")[0].reset();
		$("#replyUser").html("发表书评");
	});
	
	$("#addBtn").bind("click",function(){
		if(user_id==''){
			alert('请先登录后在进行发表书评！')
			return;
		}
		if(user_kind!='2'){
			 alert('只有会员才能发表书评');
			 return;
		}
		if($("#noticeContent").val()==''){
			alert('书评内容不能为空！')
			return;
		}
		if($("#random").val()==''){
			alert('验证码不能为空！')
			return;
		}
		
		var aQuery = $("#info2").serialize();  
		$.post('page_addSblog2.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('评论成功！');
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('评论失败：'+responseObj.err.msg);
					}else{
						 alert('评论失败：服务器异常！');
					}	
		},'json');
	});
	
});
</script>
<div id="hbzj">
<div style="width:100;height:100px;overflow:scroll;">
<table id="hbzj_con" width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <c:forEach items="${bookshelfs}" var="bookshelf" varStatus="status">
   <tr class="list0"> 
     <td width="" align="center">
     	<a href="javascript:void(0)" id="addCollect_${bookshelf.bookshelf_id}" style="font-size:12px">${bookshelf.bookshelf_name}</a>
     </td>
   </tr> 
  </c:forEach>
</table>
</div>
</div>
</body>
</html>