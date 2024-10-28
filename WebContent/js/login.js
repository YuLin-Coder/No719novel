$(document).ready(function(){
	var postStr = {
		'user_name':'',
		'user_pass':''
	};
	var selfCenterBtn = $("#selfCenterBtn");
	var loginOutBtn = $("#loginOutBtn");
	var loginInBtn = $("#loginInBtn");
	var regBtn = $("#regBtn");
	var infoField = $("#infoField");
	var loginField = $("#loginField");
	var user_name = $("#user_name");
	var user_pass = $("#user_pass");
	
	loginOutBtn.bind('click',function(){
		$.post('LoginOutSystem.action',null,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('退出成功！');
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('退出异常：'+responseObj.err.msg);
					}else{
						 alert('退出异常：服务器异常！');
					}	
		 },'json');
	});
	
	var infos = "，欢迎您登录！<br/> <input type=\"button\" id=\"loginOutBtn\" class=\"btnstyle\" value=\"退出\"/>";
	loginInBtn.bind('click',function(){
		if(user_name.val()==''||user_pass.val()==''){
			alert("用户名和密码不能为空！")
			return;
		}
		postStr['user_name'] = user_name.val();
		postStr['user_pass'] = user_pass.val();
		
		$.post('LoginInSystem.action',postStr,
			function(responseObj) {
					if(responseObj.success) {
						 var left_days = Number(responseObj.data.left_days);
						 alert('登录成功！');
						 if(left_days>0 && left_days<=3){
							 alert('您的会员身份还有'+left_days+'天就要到期了，请及时续费！');
						 }
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('登录异常：'+responseObj.err.msg);
					}else{
						 alert('登录异常：服务器异常！');
					}	
		 },'json');
	});
	
	regBtn.bind('click',function(){
		 window.location.href="page_reg.action";
	});
	
	selfCenterBtn.bind('click',function(){
		 window.location.href="page_myInfo.action";
	});
});