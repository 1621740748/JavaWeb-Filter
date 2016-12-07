<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<center>
	·	<h1>权限修改</h1>
		<br><br>
		<form action="AuthorityServlet?method=getAuthorities" method="post">
			name: <input type="text" name="username"/>
			<input type="submit" value="Submit"/>
		</form>
		<br><br>
		<c:if test="${requestScope.user !=null }">
			${requestScope.user.username }的权限是：
			<br><br>
			<form action="AuthorityServlet?method=updateAuthority" method="post">
				<input type="hidden" name="username" value="${requestScope.user.username }"/>
				
				<c:forEach items="${authorities }" var="auth" >
					<c:set var="flag" value="false"></c:set>
					<c:forEach items="${user.authorities }" var="ua">
						<!-- 若用户的权限包含这部分，他的url会在所有的authorities中找到相对应的url -->
						<c:if test="${ua.url==auth.url }">
							<c:set var="flag" value="true"></c:set>
						</c:if>
					</c:forEach>
					<c:if test="${flag == true }">
						<input type="checkbox" name="authority" 
							value="${auth.url }" checked="checked"/>${auth.displayName }
					</c:if>
					<c:if test="${flag == false }">
						<input type="checkbox" name="authority" 
							value="${auth.url }" />${auth.displayName }
					</c:if>
					
					<br><br>
					
				</c:forEach>
				<input type="submit" value="提交"/>
			</form>
		</c:if>
	
	</center>

</body>
</html>