<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>阅读网站</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	var swf_width=673;
	var swf_height=250;
	//文字颜色|文字位置|文字背景颜色|文字背景透明度|按键文字颜色|按键默认颜色|按键当前颜色|自动播放时间|图片过渡效果|是否显示按钮|打开方式
	var configtg='0x489b0d|1|0x86cdfb|6|0xffffff|0xC5DDBC|0x000033|4|3|1|_blank';
	var files = '';   
	var links = '';
	var texts = '';   
	files='images/hdtp/11.jpg|images/hdtp/12.jpg|images/hdtp/13.jpg|images/hdtp/14.jpg';
	links='|||';
	function playit(){
		document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ swf_width +'" height="'+ swf_height +'">');
		document.write('<param name="movie" value="images/hdtp/bcastr3.swf"><param name="quality" value="high">');
		document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
		document.write('<param name="FlashVars" value="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'&bcastr_config='+configtg+'">');
		document.write('<embed src="images/hdtp/bcastr3.swf" wmode="opaque" FlashVars="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'&bcastr_config='+configtg+'&menu="false" quality="high" width="'+ swf_width +'" height="'+ swf_height +'" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />'); document.write('</object>');
	}
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 #infoField,#loginField{
 	line-height:35px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	<div id="middle_login">
		<div class="titleBg">用户登录</div>
		<div class="login_con">
			<div id="infoField"  <c:if test="${userFront==null}">style="display:none;"</c:if>>
				${userFront.nick_name}，欢迎您登录！
				<br/><input type="button" id="loginOutBtn" class="btnstyle" value="退出"/>&nbsp;&nbsp;
				<input type="button" id="selfCenterBtn" class="btnstyle" value="个人中心"/>
			</div>
			<div id="loginField" <c:if test="${userFront!=null}">style="display:none;"</c:if>>
				用户名：<input type="text" id="user_name" class="inputstyle" name="user_name"  style="width:100px"/>
				<br/>密　码：<input type="password" id="user_pass" class="inputstyle" name="user_pass"  style="width:100px"/>
				<br/>　　　<input type="button" id="loginInBtn" class="btnstyle" value="登录"/>&nbsp;&nbsp;
				<input type="button" id="regBtn" class="btnstyle" value="注册"/>
			</div>
		</div>
	</div>
	<div id="picnews">
<img src="images/bg.png" style ="height: 290px;" align="middle" />
</div>
<div id="middle">	
	<div id="middle_left">
		<div id="info">
			<div class="titleBg">最新上架图书</div>
			<div class="info_con">
				<ul>
   				  <c:forEach items="${tbooks}" var="tbook" varStatus="status">
				  <li>
					<div>
						<div class="info_text">
							<a href="page_queryTbook.action?tbook_id=${tbook.tbook_id}" target="_blank" title="${tbook.tbook_name}">
								${fn:length(tbook.tbook_name)>25 ? fn:substring(tbook.tbook_name,0,24):tbook.tbook_name}
							</a>
						</div>
						<div class="info_time">[${fn:substring(tbook.tbook_date,0,10)}]</div>
					</div>
				  </li>
				  </c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	<div id="middle_right">
		<div id="info">
			<div class="titleBg">热门图书排行</div>
			<div class="info_con">
				<ul>
				  <c:forEach items="${tbooks2}" var="tbook" varStatus="status">
				  <li>
					<div>
						<div class="info_text">
							<a href="page_queryTbook.action?tbook_id=${tbook.tbook_id}" target="_blank" title="${tbook.tbook_name}">
								${fn:length(tbook.tbook_name)>25 ? fn:substring(tbook.tbook_name,0,24):tbook.tbook_name}
							</a>
						</div>
						<div class="info_time">[${fn:substring(tbook.tbook_date,0,10)}]</div>
					</div>
				  </li>
				  </c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script type="text/javascript" src="js/login1.js"></script>
</body>
</html>