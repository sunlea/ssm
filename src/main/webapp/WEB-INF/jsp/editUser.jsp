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
    
    <title>编辑用户</title>
    
	<script type="text/javascript">
	function updateUser(){
		var form = document.forms[0];
		form.action = "<%=basePath%>user/updateUser";
		form.method="post";
		form.submit();
	}
</script>

  </head>
  
  <body>
    <h1>添加用户</h1>
	<form action="" name="userForm">
		<input type="hidden" name="id" value="${user.id }"/>
		<table>
			<tbody>
				<tr>
					<td>姓名：</td><td><input type="text" name="userName" value="${user.userName }"/></td>
				</tr>
				<tr>
					<td>年龄：</td><td><input type="text" name="age" value="${user.age }"/></td>
				</tr>
				<tr>
					<td>昵称：</td><td><input type="text" name="nickname" value="${user.nickname }"/></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td>
						<label for="s1"><input type="radio" id="s1" name="sex" value="男" <c:if test="${user.sex == '男'}">checked="checked"</c:if> />男</label>
						<label for="s0"><input type="radio" id="s0" name="sex" value="女" <c:if test="${user.sex == '女'}">checked="checked"</c:if>/>女</label>
					</td>
				</tr>
				<tr>
					<td>头像：</td>
					<td>
						<img src="<%=basePath%>upload/${user.picture }">
						<input type="hidden" name="picture" value="${user.picture }">
						<input type="file" name="image" />
					</td>
				</tr>
			</tbody>
		</table>
		<input type="button" value="编辑" onclick="updateUser()"/>
	</form>
  </body>
  
</html>
