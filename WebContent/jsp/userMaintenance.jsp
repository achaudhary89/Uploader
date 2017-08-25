<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Online Maintenance Datastore - CIBIL</title>
</head>
<body>
	<form:form action="editUser.htm" commandName="UserBean" method="POST">
		<jsp:include page="security.jsp" />
		<form:hidden path="userID" id="userID" />
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td align="center"><span class="error"
					style="font-size: 17px;">&nbsp; <c:if
							test="${UserBean.message=='user.added'}">
							<spring:message code="user.added" />
						</c:if> <c:if test="${UserBean.message=='user.notexisted'}">
							<spring:message code="user.notexisted" />
						</c:if> <c:if test="${UserBean.message=='user.updated'}">
							<spring:message code="user.updated" />
						</c:if></span></td>
			</tr>
		</table>
		<table border="0" cellspacing="0" cellpadding="0" class=""
			width="100%">
			<tr>
				<td align="center"><table width="100%" border="0"
						cellspacing="0" cellpadding="0">
						<tr>
							<td align="center" class="contentbg">&nbsp;</td>
						</tr>
						<tr>
							<td align="center" class="contentbg">
								<table width="98%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><%@ include file="userMaintenanceSubMenu.jsp"%>

										</td>
									</tr>
									<tr>
										<td>
											<% 	String pageSize = (String) request.getAttribute("pageSize");
 											  	int selectedPageSize = Integer.valueOf(pageSize).intValue(); 
 												String tokenValue = (String) request.getAttribute("ctoken");
 												String url="viewUser.htm?ctoken="+tokenValue;
 											%>

											<div align="center"
												style="text-align: center; font-size: 15px;">
												<display:table name="userBeanList"
													pagesize="<%= selectedPageSize%>" id="user"
													class="appyborderColor" requestURI="<%=url%>"
													excludedParams="*">
													<display:column property="empName" title="Employee Name"
														headerClass="head" />
													<display:column property="userName"
														title="User Name(From AD)" headerClass="head" />
													<display:column property="systemRole" title="System Role"
														headerClass="head" />
													<display:column property="status" title="Status"
														headerClass="head" />
													<display:column title="Edit" headerClass="head">
														<input type="button" id="button" value="Edit"
															class="buttonedit"
															onclick="editUser(this.form,'<c:out value="${user.userID}"/>')" />
													</display:column>
												</display:table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="center" class="contentbg">&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="19">&nbsp;</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
