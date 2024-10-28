<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息</title>
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
    debugger
    console.log("${admin.real_name}")
   <%--document.getElementById("paramsTbook.user_id").value="${admin.real_name}"--%>
   document.info.action="Admin_listTbooks.action";
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
       alert("请至少选择一个要删除的图书！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delTbooks.action?ids="+ids;
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
  document.info.action="Admin_listTbooks.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listTbooks.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">图书管理&gt;&gt;图书查询</span>
</div>
<form name="info" id="info" action="Admin_listTbooks.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">图书列表</td>
    <td width="" align="right">
            图书名称：
      <input type="text" id="paramsTbook.tbook_name" name="tbook_name" value="${paramsTbook.tbook_name}" class="inputstyle" Style="width:100px;"/>&nbsp;

        <%--<input  type="hidden" id="paramsTbook.user_id" name="user_id" value="${paramsTbook.user_id}"/>&nbsp;--%>
            图书类型：
      <select name="book_type_id" id="book_type_id" class="selectstyle" style="width:100px">
      	  <option value="0">请选择</option> 
	      <c:forEach items="${bookTypes}" var="bookType">
	      <option ${bookType.book_type_id==paramsTbook.book_type_id?'selected':''} value="${bookType.book_type_id}">${bookType.book_type_name}</option> 
	      </c:forEach>
	  </select>&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">图书名称</td>
     <td width="" align="center">图书类型</td>
     <td width="" align="center">图书作者</td>
     <td width="" align="center">上架时间</td>
     <td width="" align="center">点击量</td>
     <td width="" align="center">图书状态</td>
     <td width="" align="center">读者评分</td>
     <td width="" align="center">操作</td>
   </tr>
   <c:if test="${tbooks!=null &&  fn:length(tbooks)>0}">
   <c:forEach items="${tbooks}" var="tbook" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><input type="checkbox" name="chkid" value="${tbook.tbook_id}" style="vertical-align:text-bottom;"/></td>
     <td width="" align="center">${status.index+1+paramsTbook.start}</td>
     <td width="" align="center">${tbook.tbook_name}</td>
     <td width="" align="center">${tbook.book_type_name}</td>
     <td width="" align="center">${tbook.tbook_author}&nbsp;</td>
     <td width="" align="center">${fn:substring(tbook.tbook_date,0,10)}</td>
     <td width="" align="center">${tbook.tbook_click}</td>
     <td width="" align="center">${tbook.tbook_flagDesc}</td>
     <td width="" align="center">${tbook.evaluate_score}</td>
     <td width="" align="center">
       <a href="Admin_queryTbook.action?tbook_id=${tbook.tbook_id}">查看</a>&nbsp;
       <a href="Admin_editTbook.action?tbook_id=${tbook.tbook_id}">编辑</a>
       <br/><a href="Admin_listInfos.action?tbook_id=${tbook.tbook_id}">章节管理</a>
     </td>
   </tr> 
   </c:forEach>
   </c:if>
    <c:if test="${tbooks==null || fn:length(tbooks)==0}">
   <tr>
     <td height="60" colspan="14" align="center"><b>&lt;不存在图书信息&gt;</b></td>
   </tr>
   </c:if>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>