<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Online Maintainance Datastore - Cibil login Process..</title>
</head>
<body>
	<form:form action="loginSubmit.htm" commandName="LoginBean">
		<jsp:include page="security.jsp" />
		<div align="center"><span style="color: #c00000; font-size: 18px;font-weight: bold;">Welcome to</span><img src="images/header_title.jpg" /></div>
		<table width="280" border="0" cellspacing="0" cellpadding="0"
			class="loginbox" align="center">
			<tr>
				<td align="center"><br />
				<br />
				<table width="78%" border="0" cellspacing="0" cellpadding="4"
						class="login">
						<tr>
							<td width="30%" valign="top"></td>
							<td width="70%" valign="bottom" class="error"><spring:message
									text="${LoginBean.loginStatus}" />
								<form:errors path="user"></form:errors>
								<form:errors path="password" class="error"></form:errors> 
						<%
							if("true".equals(request.getAttribute("logoutMessage"))){
							out.print("You have successfully logged out!");
							}
						%></td>
						</tr>
						<tr>
							<td width="" valign="top"><spring:message
									code="login.field.userName" /> <span class="red"> *</span></td>
							<td width=""><form:input path="user" class="widthlogin"
									id="loginUserName" autofocus="autofocus" /></td>
						</tr>
						<tr>
							<td width="" valign="top"></td>
							<td width="" valign="bottom"></td>
						</tr>
						<tr>
							<td><spring:message code="login.field.password" /> <span
								class="red"> *</span></td>
							<td valign="top"><form:password path="password"
									class="widthlogin" /></td>
						</tr>
						<tr>
							<td align="center">&nbsp;</td>
							<td align="left" class="padt10"><input type="submit"
								id="button" value="Login" class="button"
								onclick="formSubmit(this.form);" /></td>
						</tr>
					</table></td>
			</tr>
		</table>
		
	</form:form>
</body>
<script type="text/javascript" language="javascript">
function setFocus(){
	txtBox=document.getElementById("loginUserName");
	txtBox.focus();	
}
setFocus();
</script>
</html>