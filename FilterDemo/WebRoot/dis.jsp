<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>评论页面</title>
	<script type="text/javascript" src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
	<style type="text/css" src="${pageContext.request.contextPath }/sample/sample.css"></style>
</head>
<body>
	内容：${requestScope.content }
	<form action="/FilterDemo/DisServlet" method="post">
		评论：<textarea class="ckeditor" name="discuss" id="" cols="30" rows="10"></textarea>
		<input type="submit" value="Post提交">
	</form>
</body>
</html>