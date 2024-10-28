<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的书评信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
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
	<div id="product_menu">
	 	 <jsp:include page="leftMenu.jsp"></jsp:include>
	 </div>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  我的书评信息</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_listMySblog2s.action" method="post" style="width:100%;height:100%">
				 <input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>    
				 <table class="ptable" style="margin-bottom:5px;">
				 	<tr>
						<td colspan="7" align="right">
							<input type="button" id="delBtn" value="删除" class="btnstyle"/>&nbsp;
						</td>
					</tr>
					<tr class="head_text">
						 <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
					     <td width="" align="center">图书名称</td>
					     <td width="200" align="center">书评内容</td>
					     <td width="" align="center">书评时间</td>
					</tr>
					<c:if test="${sblog2s!=null &&  fn:length(sblog2s)>0}">
   					<c:forEach items="${sblog2s}" var="sblog" varStatus="status">
					<tr>
						 <td width="" align="center"><input type="checkbox" name="chkid" value="${sblog.sblog_id}" style="vertical-align:text-bottom;"/></td>
					     <td width="" align="center">${sblog.tbook_name}</td>
					     <td width="" align="center">${sblog.sblog_content}</td>
					     <td width="" align="center">${fn:substring(sblog.sblog_date,0,19)}</td>  
					</tr>
					</c:forEach>
				    </c:if>
				    <c:if test="${sblog2s==null || fn:length(sblog2s)==0}">
				    <tr>
				      <td height="60" colspan="7" align="center"><b>&lt;不存在书评信息&gt;</b></td>
				    </tr>
				    </c:if>
				 </table>
				 </form>
			</div>
			<div class="pages">
				<jsp:include page="page.jsp"></jsp:include>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function serch()
	{
	   document.info.action="page_listMySblog2s.action";
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
	  document.info.action="page_listMySblog2s.action";
	  document.info.submit();
	}
	function ChangePage(pagenum)
	{
		document.getElementById("pageNo").value=pagenum;
		document.info.action="page_listMySblog2s.action";
		document.info.submit();
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
	$(document).ready(function(){
		$("#delBtn").bind('click',function(){
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
		       alert("请至少选择一个要删除的书评！");
		       return false;
		    }
		    if(confirm('确认删除吗!?'))
		    {
		    	var aQuery = {
						'ids':ids
				};  
				$.post('page_delSblog2s.action',aQuery,
					function(responseObj) {
							if(responseObj.success) {	
								 alert('删除成功！');
								 window.location.href="page_listMySblog2s.action";
							}else  if(responseObj.err.msg){
								 alert('删除失败：'+responseObj.err.msg);
							}else{
								 alert('删除失败：服务器异常！');
							}	
				},'json');
		    }
		 });
		
	});
</script>
</body>
</html>