<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${info!=null && info.info_id!=0?'编辑':'添加'}图书章节</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script charset="utf-8" src="editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	function trimStr(str)     
	{     
	    if ((typeof(str) != "string") || !str)   
	    {    
	        return "";    
	    }   
	    return str.replace(/(^\s*)|(\s*$)/g, "");    
	}
	
	var num2 = /^\d+$/; 
	$("#addBtn").bind('click',function(){
		editor.sync();
		if($("#info_title").val()==''){
			alert("章节名称不能为空！");
			return;
		}
		if(!num2.exec($("#info_words").val())){
			alert('章节字数必须为数字');
			return;
		}
		if($("#noticeContent").val()==''){
			alert("内容不能为空！");
			return;
		}
		$("#info_id").val(0);
		$("#info").attr('action','Admin_addInfo.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		editor.sync();
	    if($("#info_title").val()==''){
			alert("章节名称不能为空！");
			return;
		}
	    if(!num2.exec($("#info_words").val())){
			alert('章节字数必须为数字');
			return;
		}
		if($("#noticeContent").val()==''){
			alert("内容不能为空！");
			return;
		}
		$("#info").attr('action','Admin_saveInfo.action').submit();
			 
	});
	
});

 
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">图书章节管理&gt;&gt;${info!=null && info.info_id!=0?'编辑':'添加'}图书章节</span>
</div>
<form id="info" name="info" action="Admin_saveInfo.action" method="post">    
<input type="hidden" id="info_id" name="info_id" value="${info.info_id}" /> 
<input type="hidden" id="tbook_id" name="tbook_id" value="${tbook_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">${info!=null && info.info_id!=0?'编辑':'添加'}图书章节</TD>
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
          <td width="150" align="right" style="padding-right:5px"><font color="red">*</font> 章节名称：</td>
          <td width="*">
            <input type="text" style="width:345px;" name="info_title" id="info_title" value="${info.info_title}"/> 
          </td>  
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 章节字数（个）：</td>
          <td>
          	<input type="text" name="info_words" id="info_words" value="${info.info_words}"/> 
          </td>  
        </tr> 
        <tr> 
          <td align="right" style="padding-right:5px"><font color="red">*</font> 章节内容：</td>
          <td>
            <textarea style="width:500px;height:300px" name="info_content"  id="noticeContent">${info.info_content}</textarea>
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
            <c:if test="${info!=null && info.info_id!=0}">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</c:if>
          	<c:if test="${info==null || info.info_id==0}">
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
			'fullscreen','|','undo', 'redo', '|', 'code', 'cut', 'copy', 'paste',
			'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			'superscript', 'quickformat', 'selectall', '/',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
			 'table', 'hr', 'emoticons', 'pagebreak','anchor', 'link', 'unlink'
		],
		/*
		items:[
			'fullscreen','|','justifyleft', 'justifycenter', 'justifyright','justifyfull',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline','anchor', 'link', 'unlink'
		],*/
		uploadJson : 'editor2/jsp/upload_json.jsp',
        fileManagerJson : 'editor2/jsp/file_manager_json.jsp',
        allowFileManager : true

	});
});
	   
</script>
</body>
</html>