<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>找回密码</title>
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
		 <div class="nav">当前位置: 主页 > 找回密码</div>
		 <div class="list_info">
		 	 <form name="info" id="info" style="width:100%;height:100%" action="LoginRegSystem.action" method="post">
			 <table class="regTable">
				<tr>
					<td class="theme" colspan="2">用户忘记密码重置</td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 学号/职工号：</td>
					<td align="left" width="80%"><input type="text" name="user_name" id="user_name" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 密保问题：</td>
					<td align="left" width="80%"><input type="text" name="user_question" id="user_question" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 密保答案：</td>
					<td align="left" width="80%"><input type="text" name="user_answer" id="user_answer" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 新的密码：</td>
					<td align="left" width="80%"><input type="password" name="user_pass" id="user_pass" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 确认密码：</td>
					<td align="left" width="80%"><input type="password" name="user_rpass" id="user_rpass" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="button" id="addBtn" class="btnstyle" value="重置密码"/>
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
	var user_name = $("#user_name");
	var user_pass = $("#user_pass");
	var user_rpass = $("#user_rpass");
	var user_question = $("#user_question");
	var user_answer = $("#user_answer");
	
	var name=/^[0-9]{8}$/;
    var pass=/^[a-zA-Z0-9]{3,15}$/;
    var num= /^\d+$/;
	addBtn.bind("click",function(){
		
		if(user_name.val()==''){
			alert("学号/职工号不能为空");
			return;
		}
		if(user_question.val()==''){
			alert("密保问题不能为空");
			return;
		}
		if(user_answer.val()==''){
			alert("密保答案不能为空");
			return;
		}
		if(user_pass.val()==''){
			alert("新的密码不能为空");
			return;
		}
		if(user_pass.val()!=user_rpass.val()){
			alert("两次输入密码不一致");
			return;
		}
		var aQuery = $("#info").serialize();  
		$.post('page_resetPwd.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('重置成功！');
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