<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listUsers3.action";
   document.info.submit();
}
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listUsers3.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listUsers3.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">会员用户管理&gt;&gt;会员余额查询</span>
</div>
<form name="info" id="info" action="Admin_listUsers3.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">会员余额查询列表</td>
    <td width="" align="right">
            用户名：
      <input type="text" id="paramsUser.user_name" name="user_name" value="${paramsUser.user_name}" class="inputstyle"/>&nbsp;
            姓名：
      <input type="text" id="paramsUser.real_name" name="real_name" value="${paramsUser.real_name}" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">用户名</td>
     <td width="" align="center">姓名</td>
     <td width="" align="center">昵称</td>
     <td width="" align="center">邮箱</td>
     <td width="" align="center">电话</td>
     <td width="" align="center">剩余会员日期</td>
     <td width="" align="center">会员到期时间</td>
   </tr>
   <c:if test="${users!=null &&  fn:length(users)>0}">
   <c:forEach items="${users}" var="user" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center">${status.index+1+paramsUser.start}</td>
     <td width="" align="center">${user.user_name}</td>
     <td width="" align="center">${user.real_name}</td>
     <td width="" align="center">${user.nick_name}&nbsp;</td>
     <td width="" align="center">${user.user_mail}&nbsp;</td>
     <td width="" align="center">${user.user_phone}&nbsp;</td>
     <td width="" align="center">${user.leftDays}&nbsp;</td>
     <td width="" align="center">${user.invalid_date}&nbsp;</td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${users==null ||  fn:length(users)==0}">
   <tr>
     <td height="60" colspan="19" align="center"><b>&lt;不存在会员信息&gt;</b></td>
   </tr>
  </c:if>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>