<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${tbook!=null && tbook.tbook_id!=0?'编辑':'添加'}图书信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	 var num = /^\d+(\.\d+)?$/;
	 var num2 = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		editor.sync();
		if($("#tbook_name").val()==''){
			alert('图书名称不能为空');
			return;
		}
		if($("#book_type_id").val()=='0'){
			alert('图书类型不能为空');
			return;
		}
		if($("#tbook_pic").val()==''){
			alert('图书图片不能为空');
			return;
		}
		if(!num.exec($("#tbook_words").val())){
			alert('累计字数必须为数字');
			return;
		}
		if($("#noticeContent").val()==''){
			alert('图书介绍不能为空');
			return;
		}
		$("#tbook_id").val(0);
		$("#info").attr('action','Admin_addTbook.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		editor.sync();
		if($("#tbook_name").val()==''){
			alert('图书名称不能为空');
			return;
		}
		if($("#book_type_id").val()=='0'){
			alert('图书类型不能为空');
			return;
		}
		if($("#tbook_pic").val()==''){
			alert('图书图片不能为空');
			return;
		}
		if(!num.exec($("#tbook_words").val())){
			alert('累计字数必须为数字');
			return;
		}
		if($("#noticeContent").val()==''){
			alert('图书介绍不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveTbook.action').submit();
		 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">图书管理&gt;&gt;${tbook!=null && tbook.tbook_id!=0?'编辑':'添加'}图书</span>
</div>
<form id="info" name="info" action="Admin_addTbook.action" method="post">   
<input type="hidden" id="tbook_id" name="tbook_id" value="${tbook.tbook_id}" /> 
<input type="hidden" id="user_id" name="user_id" value="${admin.user_id}" /> 
<input type="hidden" name="tbook_pic" id="tbook_pic" value='${tbook.tbook_pic}'/>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">${tbook!=null && tbook.tbook_id!=0?'编辑':'添加'}图书</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="150" align="right" style="padding-right:5px"><font color="red">*</font> 图书名称：</td>
          <td width="*">
          	<input type="text" name="tbook_name" id="tbook_name" value="${tbook.tbook_name}"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 图书类型：</td>
          <td>
          	 <select name="book_type_id" id="book_type_id" style="width:155px">
		      	  <option value="0">请选择</option> 
			      <c:forEach items="${bookTypes}" var="bookType">
			      <option ${bookType.book_type_id==tbook.book_type_id?'selected':''} value="${bookType.book_type_id}">${bookType.book_type_name}</option> 
			      </c:forEach>
			  </select>
          </td>
        </tr> 
        <tr>
		  <td align="right" style="padding-right:5px">图书图片：</td>
		  <td align="left">
		    <img id="userImg" src="../images/tbooks/${tbook.tbook_pic}" width="70" height="80" style="border:0px;"/>
	        &nbsp;<span id="op" style="display:none"><img src="images/loading004.gif"  height="80" /></span>
	      </td>
	    </tr>
	     <tr>
		  <td align="right" style="padding-right:5px"></td>
	      <td align="left">
	          <iframe name="uploadPage" src="uploadImg3.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
        <tr>
          <td align="right" style="padding-right:5px">图书作者：</td>
          <td>
          	<input type="text" name="tbook_author" id="tbook_author" value="${tbook.tbook_author}"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 累计字数（万）：</td>
          <td>
          	<input type="text" name="tbook_words" id="tbook_words" value="${tbook.tbook_words}"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 图书状态：</td>
          <td>
          	<select id="tbook_flag" name="tbook_flag" value="${tbook.tbook_flag}" style="width:155px;">
	      		<option value="1" ${1==tbook.tbook_flag?'selected':''}>连载中</option>
	      		<option value="2" ${2==tbook.tbook_flag?'selected':''}>已完本</option>
	        </select>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 图书介绍：</td>
          <td>
          	<textarea style="width:500px;height:300px" name="tbook_desc" id="noticeContent">${tbook.tbook_desc}</textarea>
          </td>
        </tr>
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<c:if test="${tbook!=null && tbook.tbook_id!=0}">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</c:if>
          	<c:if test="${tbook==null || tbook.tbook_id==0}">
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</c:if>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
<script>        
KindEditor.ready(function(K) {
	window.editor = K.create('#noticeContent',{
		width : '95%',
		items:[
			'fullscreen','|','justifyleft', 'justifycenter', 'justifyright','justifyfull',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline','anchor', 'link', 'unlink'
		],
		uploadJson : 'editor2/jsp/upload_json.jsp',
        fileManagerJson : 'editor2/jsp/file_manager_json.jsp',
        allowFileManager : true
	});
});
	   
</script>
</body>
</html>