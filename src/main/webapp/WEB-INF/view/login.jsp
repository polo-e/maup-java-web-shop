<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<%@ page isELIgnored = "false"%>

<div id="content">
	<div style="text-align: center;">
		<label>${sessionScope.result}</label>
	</div>

	<c:choose>
		<c:when test="${empty sessionScope.user}">
			<form action='./login' method="post">
				<div style="text-align: center;">
					<table border='0'>
						<tr>
							<td>логін:</td>
							<td><input type='text' name='login'></td>
						</tr>
						<tr>
							<td>пароль:</td>
							<td><input type='password' name='password'></td>
						</tr>
						<tr>
							<td></td>
							<td align='right'><input type='submit' value='Send'></td>
						</tr>
					</table>
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<form action="./logout">
				<div style="text-align: center;">
					<h1>Привіт, ${user.name}</h1>
					<input type="submit" value="logout" />
				</div>
			</form>
		</c:otherwise>
	</c:choose>
</div>
<%@ include file="includes/footer.jsp"%>
