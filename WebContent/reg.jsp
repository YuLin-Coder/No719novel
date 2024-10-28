<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/reg.css">
<link rel="stylesheet" type="text/css" href="css/info.css">
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
	<div id="list">
		 <div class="nav">当前位置: 主页 > 用户注册 </div>
		 <div class="list_info">
		 	 <form name="info" id="info" style="width:100%;height:100%" action="LoginRegSystem.action" method="post">
			 <table class="regTable">
				<tr>
					<td class="theme" colspan="2">新用户注册</td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 用户名：</td>
					<td align="left" width="80%"><input type="text" name="user_name" id="params.user_name" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 密　码：</td>
					<td align="left" width="80%"><input type="password" name="user_pass" id="params.user_pass" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 确认密码：</td>
					<td align="left" width="80%"><input type="password" name="user_rpass" id="user_rpass" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 姓　名：</td>
					<td align="left" width="80%"><input type="text" name="real_name" id="params.real_name" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 昵　称：</td>
					<td align="left" width="80%"><input type="text" name="nick_name" id="params.nick_name" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 联系电话：</td>
					<td align="left" width="80%"><input type="text" name="user_phone" id="params.user_phone" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%">联系地址：</td>
					<td align="left" width="80%"><input type="text" name="user_address" id="params.user_address" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%">联系邮箱：</td>
					<td align="left" width="80%"><input type="text" name="user_mail" id="params.user_mail" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 验证码：</td>
					<td align="left" width="80%"> 
						<input type="text" id="params.random" name="random" style="width:80px;" class="inputstyle"/>
						&nbsp;<img src="Random.jsp" width="50" valign="middle" style="cursor:pointer;vertical-align:middle" title="换一张" id="safecode" border="0" onClick="reloadcode()"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="button" id="addBtn" class="btnstyle" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset"  class="btnstyle" value="清空"/>
					</td>
				</tr>
		 	 </table>
		 	 </form>
		 </div>
		 
	</div>
	<div id="Picture"></div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
//实现验证码点击刷新
function reloadcode(){
	var verify=document.getElementById('safecode');
	verify.setAttribute('src','Random.jsp?'+Math.random());
}
$(document).ready(function(){
	var addBtn = $("#addBtn");
	var user_type = $("#params\\.user_type");
	var user_name = $("#params\\.user_name");
	var user_pass = $("#params\\.user_pass");
	var user_rpass = $("#user_rpass");
	var real_name = $("#params\\.real_name");
	var nick_name = $("#params\\.nick_name");
	var user_phone = $("#params\\.user_phone");
	var user_mail = $("#params\\.user_mail");
	var user_question = $("#params\\.user_question");
	var user_answer = $("#params\\.user_answer");
	var random = $("#params\\.random");
	
	var name=/^[0-9]{8}$/;
    var pass=/^[a-zA-Z0-9]{3,15}$/;
    var num= /^\d+$/;
    var real_nameYz = /^[\u0391-\uFFE5]{2,4}$/;
    var user_phoneYz= /^\d{6,11}$/;
    var user_mailYz=/^[a-zA-Z0-9_]+[@][a-zA-Z0-9_]+[.][a-zA-Z0-9_]+$/;
	addBtn.bind("click",function(){
		
		if(user_name.val()==''||user_pass.val()==''){
			alert("用户名或密码不能为空");
			return;
		}
		/**
		if(user_type.val()==1 && !name.exec(user_name.val())){
			window.alert("学号必须为8位数字！");
			return false;
		}
		if(!pass.exec(user_pass.val())){
			window.alert("密码必须为字母数字4-16位！");
			return false;
		}**/
		if(user_pass.val()!=user_rpass.val()){
			alert("两次输入密码不一致");
			return;
		}
		if(user_pass.val().length<6){
			alert("密码至少为6位以上");
			return;
		}
		if(real_name.val()==''){
			alert("姓名不能为空");
			return;
		}
		/*
		if(!real_nameYz.exec(real_name.val())){
			alert("姓名必须为2-4位中文");
			return;
		}*/
		if(nick_name.val()==''){
			alert("昵称不能为空");
			return;
		}
		if(user_phone.val()==''){
			alert("联系电话不能为空");
			return;
		}
		if(user_mail.val()!="" && !user_mailYz.exec(user_mail.val())){
			alert("邮箱格式不正确");
			return;
		}
		if(!user_phoneYz.exec(user_phone.val())){
			alert("联系电话的长度必须为6位到11位数字");
			return;
		}
		if(random.val()==''){
			alert("验证码不能为空");
			return;
		}
		var aQuery = $("#info").serialize();  
		$.post('LoginRegSystem.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('注册成功！');
						 window.location.href="page_index.action";
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
});
</script>
</body>
</html>