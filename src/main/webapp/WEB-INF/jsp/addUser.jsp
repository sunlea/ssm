<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加用户</title>
    
	<script type="text/javascript">
	function addUser(){
		var form = document.forms[0];
		form.action = "<%=basePath%>user/addUser";
		form.method="post";
		form.submit();
	}
</script>

  </head>
  
  <body>
    <h1>添加用户</h1>
	<form action="" name="userForm">
		<table>
			<tr>
				<td>登录名：</td>
				<td><input type="text" name="userName"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="text" name="password"></td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>昵称：</td>
				<td><input type="text" name="nickname"></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td><input type="text" name="sex"></td>
			</tr>
			<tr>
				<td>头像：</td>
				<td><input type="text" name="picture"></td>
			</tr>
			<tr>
				<td>创建时间：</td>
				<td><input type="text" name="createtime"></td>
			</tr>
			<tr>
				<td>最后登录时间：</td>
				<td><input type="text" name="lastlogintime"></td>
			</tr>
			<tr>
				<td>文件路径：</td>
				<td><input type="text" name="tilepath"></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" name="age"></td>
			</tr>
		</table>
		
		<input type="button" value="添加" onclick="addUser()">
	</form>
  </body>
</html>
