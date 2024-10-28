<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付记录信息</title>
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
   document.info.action="Admin_listUpays.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的用户！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delUpays.action?ids="+ids;
       document.info.submit();
    }
    
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
  document.info.action="Admin_listUpays.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listUpays.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">支付记录管理&gt;&gt;支付记录查询</span>
</div>
<form name="info" id="info" action="Admin_listUpays.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">支付记录列表</td>
    <td width="" align="right">
            订单号：
      <input type="text" id="paramsUpay.upay_no" name="upay_no" value="${paramsUpay.upay_no}" class="inputstyle"/>&nbsp;
           会员姓名：
      <input type="text" id="paramsUpay.real_name" name="real_name" value="${paramsUpay.real_name}" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">订单号</td>
     <td width="" align="center">会员姓名</td>
     <td width="" align="center">购买时间</td>
     <td width="" align="center">购买价钱</td>
   </tr>
   <c:if test="${upays!=null &&  fn:length(upays)>0}">
   <c:forEach items="${upays}" var="upay" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center">${status.index+1+paramsUpay.start}</td>
     <td width="" align="center">${upay.upay_no}</td>
     <td width="" align="center">${upay.real_name}</td>
     <td width="" align="center">${upay.upay_date}&nbsp;</td>
     <td width="" align="center">${upay.upay_price}&nbsp;</td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${upays==null ||  fn:length(upays)==0}">
   <tr>
     <td height="60" colspan="19" align="center"><b>&lt;不存在支付记录信息&gt;</b></td>
   </tr>
  </c:if>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>